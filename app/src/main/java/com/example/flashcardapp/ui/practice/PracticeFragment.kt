package com.example.flashcardapp.ui.practice

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcardapp.R
import com.example.flashcardapp.data.local.AppDatabase
import com.example.flashcardapp.data.local.entity.StudiedWord
import com.example.flashcardapp.data.local.entity.StudySession
import com.example.flashcardapp.data.local.entity.Word
import com.example.flashcardapp.databinding.FragmentPracticeBinding
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.bumptech.glide.Glide
import com.example.flashcardapp.logic.SrsLogic
import com.example.flashcardapp.logic.TextToSpeechManager
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.UserViewModel
import com.example.flashcardapp.viewmodel.WordViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PracticeFragment : Fragment() {

    private var _binding: FragmentPracticeBinding? = null
    private val binding get() = _binding!!

    private val wordViewModel: WordViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        MainViewModelFactory(
            UserRepository(database.userDao()),
            WordRepository(database.wordDao()),
            SessionRepository(database.studySessionDao())
        )
    }

    private val userViewModel: UserViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        MainViewModelFactory(
            UserRepository(database.userDao()),
            WordRepository(database.wordDao()),
            SessionRepository(database.studySessionDao())
        )
    }

    private var wordsList: List<Word> = emptyList()
    private var currentIndex = 0
    private var isCardFlipped = false
    private var correctCount = 0
    private var wrongCount = 0
    private var sessionStartMs = 0L
    private var userName = ""
    private var isProcessing = false
    private val studiedWordIds = mutableSetOf<Int>()
    private var ttsManager: TextToSpeechManager? = null

    private val motivationCorrectIds = intArrayOf(
        R.string.motivation_correct_1,
        R.string.motivation_correct_2,
        R.string.motivation_correct_3,
        R.string.motivation_correct_4,
        R.string.motivation_correct_5
    )
    private val motivationWrongIds = intArrayOf(
        R.string.motivation_wrong_1,
        R.string.motivation_wrong_2,
        R.string.motivation_wrong_3
    )

    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPracticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionStartMs = SystemClock.elapsedRealtime()
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        setupAnimations()

        viewLifecycleOwner.lifecycleScope.launch {
            val user = userViewModel.user.first()
            userName = user?.name ?: ""
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val words = wordViewModel.loadAllWords()
            if (words.isEmpty()) {
                showEmptyState()
            } else {
                wordsList = words.shuffled()
                binding.pbFlashcardProgress.max = wordsList.size
                showWord(0)
            }
        }

        // KART ÇEVİRME
        binding.frontCardContent.setOnClickListener { flipCard() }
        binding.backCardContent.setOnClickListener { flipCard() }

        binding.btnKnew.setOnClickListener { handleAnswer(true) }
        binding.btnForgot.setOnClickListener { handleAnswer(false) }

        ttsManager = TextToSpeechManager(requireContext())
        
        // YENİ SABİT SES BUTONU
        binding.btnSpeakGlobal.setOnClickListener {
            if (currentIndex < wordsList.size) {
                ttsManager?.speak(wordsList[currentIndex].originalWord, wordsList[currentIndex].languageCode)
            }
        }
    }

    private fun setupAnimations() {
        val scale = requireContext().resources.displayMetrics.density
        binding.cardFront.cameraDistance = 8000 * scale
        binding.cardBack.cameraDistance = 8000 * scale

        frontAnim = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(requireContext(), R.animator.back_animator) as AnimatorSet
    }

    private fun flipCard() {
        val binding = _binding ?: return
        isCardFlipped = !isCardFlipped
        if (isCardFlipped) {
            frontAnim.setTarget(binding.cardFront)
            backAnim.setTarget(binding.cardBack)
            frontAnim.start()
            backAnim.start()
        } else {
            frontAnim.setTarget(binding.cardBack)
            backAnim.setTarget(binding.cardFront)
            backAnim.start()
            frontAnim.start()
        }
    }

    private fun resetCard() {
        val binding = _binding ?: return
        if (isCardFlipped) {
            frontAnim.setTarget(binding.cardBack)
            backAnim.setTarget(binding.cardFront)
            backAnim.start()
            frontAnim.start()
            isCardFlipped = false
        }
    }

    private fun showWord(index: Int) {
        val binding = _binding ?: return
        currentIndex = index
        if (index < wordsList.size) {
            val word = wordsList[index]
            binding.tvFrontWord.text = word.originalWord
            binding.tvBackWord.text = word.originalWord
            binding.tvBackMeaning.text = word.meaning
            binding.tvBackExample.text =
                if (word.exampleSentence.isNotEmpty()) "\"${word.exampleSentence}\"" else ""

            binding.flashcardContainer.visibility = View.VISIBLE
            binding.btnContainer.visibility = View.VISIBLE
            binding.tvEmptyState.visibility = View.GONE
            binding.pbFlashcardProgress.progress = index + 1
            binding.tvProgressCount.text = "${index + 1} / ${wordsList.size}"

            context?.let {
                if (word.imageUrl != null) {
                    binding.ivWordImageFront.visibility = View.VISIBLE
                    Glide.with(it)
                        .load(word.imageUrl)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.ivWordImageFront)
                } else {
                    binding.ivWordImageFront.visibility = View.GONE
                }
            }

            resetCard()
        }
    }

    private fun handleAnswer(knewIt: Boolean) {
        if (isProcessing) return
        val binding = _binding ?: return
        
        studiedWordIds.add(wordsList[currentIndex].id)
        
        if (knewIt) {
            correctCount++
            binding.flashcardContainer.animate()
                .scaleX(1.1f).scaleY(1.1f).alpha(0f).setDuration(250)
                .withEndAction { 
                    _binding?.flashcardContainer?.scaleX = 1.0f
                    _binding?.flashcardContainer?.scaleY = 1.0f
                    _binding?.flashcardContainer?.alpha = 1.0f
                }
                .start()
            view?.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
            showMotivation(true)
        } else {
            wrongCount++
            binding.flashcardContainer.animate()
                .translationX(20f).setDuration(50)
                .withEndAction {
                    _binding?.flashcardContainer?.animate()?.translationX(-20f)?.setDuration(100)
                        ?.withEndAction {
                            _binding?.flashcardContainer?.animate()?.translationX(0f)?.setDuration(50)?.start()
                        }?.start()
                }
                .start()
            view?.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS)
            showMotivation(false)
        }

        val updatedWord = SrsLogic.updateWithBinaryResult(wordsList[currentIndex], knewIt)
        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.updateWord(updatedWord)
        }

        val nextIndex = currentIndex + 1
        if (nextIndex < wordsList.size) {
            showWord(nextIndex)
        } else {
            isProcessing = true
            saveSessionAndShowSummary()
        }
    }

    private fun showMotivation(correct: Boolean) {
        val binding = _binding ?: return
        val ids = if (correct) motivationCorrectIds else motivationWrongIds
        val resId = ids.random()
        val message = getString(resId, userName)

        binding.cvMotivation.handler?.removeCallbacksAndMessages(null)

        binding.tvMotivation.text = message
        binding.cvMotivation.visibility = View.VISIBLE
        binding.cvMotivation.alpha = 0f
        binding.cvMotivation.scaleX = 0.8f
        binding.cvMotivation.scaleY = 0.8f

        binding.cvMotivation.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(150)
            .withEndAction {
                binding.cvMotivation.postDelayed({
                    _binding?.cvMotivation?.animate()
                        ?.alpha(0f)
                        ?.scaleX(0.8f)
                        ?.scaleY(0.8f)
                        ?.setDuration(200)
                        ?.withEndAction { _binding?.cvMotivation?.visibility = View.INVISIBLE }
                        ?.start()
                }, 800)
            }
            .start()
    }

    private fun saveSessionAndShowSummary() {
        val currentContext = context ?: return
        val elapsedMs = SystemClock.elapsedRealtime() - sessionStartMs
        val minutes = (elapsedMs / 60000).toInt().coerceAtLeast(1)
        val total = correctCount + wrongCount

        viewLifecycleOwner.lifecycleScope.launch {
            val database = AppDatabase.getDatabase(currentContext)
            val repo = SessionRepository(database.studySessionDao())
            repo.saveSession(
                StudySession(
                    date = System.currentTimeMillis(),
                    durationMinutes = minutes,
                    questionsAnswered = total,
                    correctAnswers = correctCount
                )
            )

            val studiedWords = studiedWordIds.map { id ->
                StudiedWord(wordId = id, date = System.currentTimeMillis())
            }
            repo.saveStudiedWords(studiedWords)

            if (isAdded && isResumed) {
                showCompletionSummary(correctCount, wrongCount, minutes)
            }
        }
    }

    private fun showCompletionSummary(correct: Int, wrong: Int, minutes: Int) {
        val action = PracticeFragmentDirections.actionPracticeFragmentToPracticeSummaryFragment(
            correctCount = correct,
            wrongCount = wrong,
            durationMinutes = minutes,
            practiceMode = "flashcards"
        )
        findNavController().navigate(action)
    }

    private fun showEmptyState() {
        binding.flashcardContainer.visibility = View.GONE
        binding.btnContainer.visibility = View.GONE
        binding.pbFlashcardProgress.visibility = View.GONE
        binding.tvProgressCount.visibility = View.GONE
        binding.tvEmptyState.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ttsManager?.shutdown()
        ttsManager = null
        _binding = null
    }
}