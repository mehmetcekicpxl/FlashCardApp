package com.example.flashcardapp.ui.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flashcardapp.R
import com.example.flashcardapp.data.local.AppDatabase
import com.example.flashcardapp.data.local.entity.Word
import com.example.flashcardapp.databinding.FragmentWordListBinding
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.WordViewModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WordListFragment : Fragment() {

    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!

    private val wordViewModel: WordViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        val userRepository = UserRepository(database.userDao())
        val wordRepository = WordRepository(database.wordDao())
        val sessionRepository = SessionRepository(database.studySessionDao())
        MainViewModelFactory(userRepository, wordRepository, sessionRepository)
    }

    private lateinit var wordAdapter: WordAdapter
    private var allWords: List<Word> = emptyList()
    private var selectedCategory: String = "All"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()

        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.allWords.collectLatest { words ->
                allWords = words
                setupCategoryChips(words)
                filterWords(binding.searchView.query.toString())
            }
        }
    }

    private fun setupRecyclerView() {
        wordAdapter = WordAdapter { word ->
            wordViewModel.deleteWord(word)
        }
        binding.rvFullWordList.adapter = wordAdapter
    }

    private fun setupCategoryChips(words: List<Word>) {
        val categories = listOf("All") + words.map { it.category }.distinct().sorted()
        binding.chipGroupCategories.removeAllViews()
        
        categories.forEach { category ->
            val chip = Chip(requireContext()).apply {
                text = category
                isCheckable = true
                isChecked = (category == selectedCategory)
                
                setOnClickListener {
                    selectedCategory = category
                    filterWords(binding.searchView.query.toString())
                }
            }
            binding.chipGroupCategories.addView(chip)
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterWords(newText)
                return true
            }
        })
    }

    private fun filterWords(query: String?) {
        var filteredList = allWords
        
        // Kategoriye göre filtrele
        if (selectedCategory != "All") {
            filteredList = filteredList.filter { it.category == selectedCategory }
        }
        
        // Aramaya göre filtrele
        if (!query.isNullOrBlank()) {
            filteredList = filteredList.filter {
                it.originalWord.contains(query, ignoreCase = true) ||
                        it.meaning.contains(query, ignoreCase = true)
            }
        }

        wordAdapter.submitList(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}