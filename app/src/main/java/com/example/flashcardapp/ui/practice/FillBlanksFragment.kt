package com.example.flashcardapp.ui.practice

import android.app.AlertDialog
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcardapp.R
import com.example.flashcardapp.data.local.AppDatabase
import com.example.flashcardapp.data.local.entity.StudiedWord
import com.example.flashcardapp.data.local.entity.StudySession
import com.example.flashcardapp.data.local.entity.Word
import com.example.flashcardapp.databinding.FragmentFillBlanksBinding
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.UserViewModel
import com.example.flashcardapp.viewmodel.WordViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FillBlanksFragment : Fragment() {

    private var _binding: FragmentFillBlanksBinding? = null
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
    private var correctCount = 0
    private var wrongCount = 0
    private var sessionStartMs = 0L
    private var userName = ""
    private val studiedWordIds = mutableSetOf<Int>()
    private var isProcessing = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFillBlanksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionStartMs = SystemClock.elapsedRealtime()
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        // Fetch user name for personalized messages
        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.user.collectLatest { user ->
                userName = user?.name ?: ""
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val words = wordViewModel.loadAllWords()
            if (words.isEmpty()) {
                showEmptyState()
            } else {
                wordsList = words.shuffled()
                binding.pbProgress.max = wordsList.size
                showQuestion(0)
            }
        }

        binding.btnCheck.setOnClickListener { checkAnswer() }
        binding.btnNext.setOnClickListener { nextQuestion() }
    }

    private fun showQuestion(index: Int) {
        val binding = _binding ?: return
        currentIndex = index
        if (index >= wordsList.size) {
            if (isProcessing) return
            isProcessing = true
            saveSessionAndShowSummary()
            return
        }

        val word = wordsList[index]
        binding.tvQuestion.text = getString(R.string.what_means, word.originalWord)
        binding.etAnswer.text?.clear()
        binding.tvFeedback.visibility = View.GONE
        binding.btnCheck.visibility = View.VISIBLE
        binding.btnNext.visibility = View.GONE
        binding.tilAnswer.isEnabled = true
        binding.pbProgress.progress = index + 1
        binding.tvProgressCount.text = "${index + 1} / ${wordsList.size}"
    }

    private fun checkAnswer() {
        val answer = binding.etAnswer.text.toString().trim().lowercase()
        val originalMeaning = wordsList[currentIndex].meaning
        
        // Split meaning by common delimiters and trim
        val possibleAnswers = originalMeaning.split(",", ";", "/").map { it.trim().lowercase() }

        binding.tvFeedback.visibility = View.VISIBLE
        binding.btnCheck.visibility = View.GONE
        binding.btnNext.visibility = View.VISIBLE
        binding.tilAnswer.isEnabled = false

        val currentWord = wordsList[currentIndex]
        studiedWordIds.add(currentWord.id)
        
        if (possibleAnswers.contains(answer)) {
            correctCount++
            showMotivation("✨ ${getString(R.string.motivation_correct_1, userName)} 🎉")
            binding.tvFeedback.text = getString(R.string.correct_answer)
            binding.tvFeedback.setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
        } else {
            wrongCount++
            binding.tvFeedback.text = getString(R.string.wrong_answer_format, originalMeaning)
            binding.tvFeedback.setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
        }
    }

    private fun showMotivation(message: String) {
        val binding = _binding ?: return
        
        // Cancel any pending hide action
        binding.cvMotivation.handler?.removeCallbacksAndMessages(null)

        // Show and animate
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
                }, 1000)
            }
            .start()
    }

    private fun nextQuestion() {
        showQuestion(currentIndex + 1)
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
                // Navigate to Summary Screen
                val action = FillBlanksFragmentDirections.actionFillBlanksFragmentToPracticeSummaryFragment(
                    correctCount = correctCount,
                    wrongCount = wrongCount,
                    durationMinutes = minutes,
                    practiceMode = "fill_blanks"
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun showEmptyState() {
        binding.cardQuestion.visibility = View.GONE
        binding.btnCheck.visibility = View.GONE
        binding.pbProgress.visibility = View.GONE
        binding.tvProgressCount.visibility = View.GONE
        binding.tvEmptyState.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
