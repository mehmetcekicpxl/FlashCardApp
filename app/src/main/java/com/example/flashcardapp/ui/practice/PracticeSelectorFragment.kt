package com.example.flashcardapp.ui.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcardapp.R
import com.example.flashcardapp.data.local.AppDatabase
import com.example.flashcardapp.databinding.FragmentPracticeSelectorBinding
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.WordViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PracticeSelectorFragment : Fragment() {

    private var _binding: FragmentPracticeSelectorBinding? = null
    private val binding get() = _binding!!

    private val wordViewModel: WordViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        MainViewModelFactory(
            UserRepository(database.userDao()),
            WordRepository(database.wordDao()),
            SessionRepository(database.studySessionDao())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPracticeSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        binding.cardFlashcards.setOnClickListener {
            findNavController().navigate(R.id.action_practiceSelectorFragment_to_practiceFragment)
        }

        binding.cardFillBlanks.setOnClickListener {
            findNavController().navigate(R.id.action_practiceSelectorFragment_to_fillBlanksFragment)
        }

        binding.cardMatching.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val words = wordViewModel.loadAllWords()
                if (words.size < 4) {
                    Toast.makeText(requireContext(), getString(R.string.need_more_words), Toast.LENGTH_LONG).show()
                } else {
                    findNavController().navigate(R.id.action_practiceSelectorFragment_to_matchingFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
