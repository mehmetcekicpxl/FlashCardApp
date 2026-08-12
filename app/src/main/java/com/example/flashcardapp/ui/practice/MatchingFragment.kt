package com.example.flashcardapp.ui.practice

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.graphics.Color
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
import com.example.flashcardapp.databinding.FragmentMatchingBinding
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.logic.SrsLogic
import com.example.flashcardapp.logic.TextToSpeechManager
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.UserViewModel
import com.example.flashcardapp.viewmodel.WordViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null
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

    private var allWords: List<Word> = emptyList()
    private var roundWords: List<Word> = emptyList()
    private var selectedWordBtn: MaterialButton? = null
    private var selectedWordIndex: Int = -1
    private var matchedCount = 0
    private var correctCount = 0
    private var wrongCount = 0
    private var roundIndex = 0
    private var sessionStartMs = 0L
    private var userName = ""
    private var isProcessing = false
    private val studiedWordIds = mutableSetOf<Int>()
    private var ttsManager: TextToSpeechManager? = null

    private lateinit var wordButtons: List<MaterialButton>
    private lateinit var meaningButtons: List<MaterialButton>
    private val matchedWordIndices = mutableSetOf<Int>()
    private val matchedMeaningIndices = mutableSetOf<Int>()

    // map: shuffled meaning button index -> original word index
    private var meaningToWordMap: Map<Int, Int> = emptyMap()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionStartMs = SystemClock.elapsedRealtime()
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        wordButtons = listOf(binding.btnWord1, binding.btnWord2, binding.btnWord3, binding.btnWord4)
        meaningButtons = listOf(binding.btnMeaning1, binding.btnMeaning2, binding.btnMeaning3, binding.btnMeaning4)

        // Fetch user name for personalized messages
        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.user.collectLatest { user ->
                userName = user?.name ?: ""
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val words = wordViewModel.loadAllWords()
            if (words.size < 4) {
                showEmptyState()
            } else {
                allWords = words.shuffled()
                roundIndex = 0
                loadRound()
            }
        }

        binding.btnNextRound.setOnClickListener {
            roundIndex++
            loadRound()
        }

        ttsManager = TextToSpeechManager(requireContext())
    }

    private fun loadRound() {
        val start = roundIndex * 4
        if (start >= allWords.size) {
            if (isProcessing) return
            isProcessing = true
            saveSessionAndShowSummary()
            return
        }

        val end = minOf(start + 4, allWords.size)
        if (end - start < 4) {
            if (isProcessing) return
            isProcessing = true
            saveSessionAndShowSummary()
            return
        }

        roundWords = allWords.subList(start, end)
        matchedCount = 0
        selectedWordBtn = null
        selectedWordIndex = -1
        matchedWordIndices.clear()
        matchedMeaningIndices.clear()

        binding.btnNextRound.visibility = View.GONE
        binding.tvFeedback.visibility = View.GONE
        if (_binding == null) return
        // Set word buttons (left column) in order
        for (i in 0 until 4) {
            wordButtons[i].text = roundWords[i].originalWord
            wordButtons[i].isEnabled = true
            wordButtons[i].alpha = 1f
            resetButtonStyle(wordButtons[i])
        }

        // Shuffle meanings for right column
        val shuffledMeaningIndices = (0 until 4).shuffled()
        meaningToWordMap = shuffledMeaningIndices.mapIndexed { btnIndex, wordIndex -> btnIndex to wordIndex }.toMap()

        for (i in 0 until 4) {
            val wordIndex = meaningToWordMap[i]!!
            meaningButtons[i].text = roundWords[wordIndex].meaning
            meaningButtons[i].isEnabled = true
            meaningButtons[i].alpha = 1f
            resetButtonStyle(meaningButtons[i])
        }

        setupListeners()
    }

    private fun setupListeners() {
        for (i in 0 until 4) {
            wordButtons[i].setOnClickListener {
                if (i in matchedWordIndices) return@setOnClickListener
                
                // Deselect previous
                selectedWordBtn?.let { resetButtonStyle(it) }
                
                // Select this
                selectedWordBtn = wordButtons[i]
                selectedWordIndex = i
                
                // Visual feedback for selection: light blue background & primary stroke
                wordButtons[i].strokeColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary))
                wordButtons[i].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E3F2FD"))

                // TTS Pronunciation
                ttsManager?.speak(roundWords[i].originalWord, roundWords[i].languageCode)
            }

            meaningButtons[i].setOnClickListener {
                if (i in matchedMeaningIndices) return@setOnClickListener
                if (selectedWordIndex == -1) {
                    binding.tvFeedback.visibility = View.VISIBLE
                    binding.tvFeedback.text = getString(R.string.matching_instruction)
                    binding.tvFeedback.setTextColor(Color.GRAY)
                    return@setOnClickListener
                }

                val currentMeaningBtn = meaningButtons[i]
                val correctWordIndex = meaningToWordMap[i]!!

                if (correctWordIndex == selectedWordIndex) {
                    // Correct match!
                    correctCount++
                    matchedCount++
                    matchedWordIndices.add(selectedWordIndex)
                    matchedMeaningIndices.add(i)

                    // Track word ID
                    studiedWordIds.add(roundWords[selectedWordIndex].id)

                    // Update SRS state
                    val updatedWord = SrsLogic.updateWithBinaryResult(roundWords[selectedWordIndex], true)
                    viewLifecycleOwner.lifecycleScope.launch {
                        wordViewModel.updateWord(updatedWord)
                    }

                    // Visual feedback: green stroke
                    val greenColor = ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
                    wordButtons[selectedWordIndex].strokeColor = ColorStateList.valueOf(greenColor)
                    currentMeaningBtn.strokeColor = ColorStateList.valueOf(greenColor)
                    
                    // Fade out matched cards
                    wordButtons[selectedWordIndex].animate().alpha(0f).setDuration(600).start()
                    currentMeaningBtn.animate().alpha(0f).setDuration(600).start()
                    
                    wordButtons[selectedWordIndex].isEnabled = false
                    currentMeaningBtn.isEnabled = false

                    binding.tvFeedback.setTextColor(greenColor)
                    binding.tvFeedback.visibility = View.VISIBLE
                    showMotivation("✨ ${getString(R.string.matched)} ✅")

                    selectedWordBtn = null
                    selectedWordIndex = -1

                    if (matchedCount == 4) {
                        binding.tvFeedback.text = "🎉 ${getString(R.string.all_matched)}"
                        val hasMoreRounds = (roundIndex + 1) * 4 + 4 <= allWords.size
                        if (hasMoreRounds) {
                            binding.btnNextRound.visibility = View.VISIBLE
                        } else {
                            if (isProcessing) return@setOnClickListener
                            isProcessing = true
                            saveSessionAndShowSummary()
                        }
                    }
                } else {
                    // Wrong match
                    wrongCount++
                    val redColor = ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
                    
                    // Flash red for both cards
                    selectedWordBtn?.strokeColor = ColorStateList.valueOf(redColor)
                    currentMeaningBtn.strokeColor = ColorStateList.valueOf(redColor)
                    
                    binding.tvFeedback.text = "❌ ${getString(R.string.not_a_match)}"
                    binding.tvFeedback.setTextColor(redColor)
                    binding.tvFeedback.visibility = View.VISIBLE

                    // Reset styling after a short delay
                    val wordBtnToReset = selectedWordBtn
                    currentMeaningBtn.postDelayed({
                        val binding = _binding ?: return@postDelayed
                        wordBtnToReset?.let { resetButtonStyle(it) }
                        resetButtonStyle(currentMeaningBtn)
                        binding.tvFeedback.visibility = View.INVISIBLE
                    }, 600)

                    selectedWordBtn = null
                    selectedWordIndex = -1
                }
            }
        }
    }

    private fun resetButtonStyle(btn: MaterialButton) {
        btn.strokeColor = ColorStateList.valueOf(Color.parseColor("#E0E0E0"))
        btn.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        btn.alpha = 1f
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
                val action = MatchingFragmentDirections.actionMatchingFragmentToPracticeSummaryFragment(
                    correctCount = correctCount,
                    wrongCount = wrongCount,
                    durationMinutes = minutes,
                    practiceMode = "matching"
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun showEmptyState() {
        binding.columnWords.visibility = View.GONE
        binding.columnMeanings.visibility = View.GONE
        binding.tvInstruction.visibility = View.GONE
        binding.tvEmptyState.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ttsManager?.shutdown()
        ttsManager = null
        _binding = null
    }
}
