package com.example.flashcardapp.ui.words

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcardapp.R
import com.example.flashcardapp.data.local.AppDatabase
import com.example.flashcardapp.databinding.FragmentAddWordBinding
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.WordViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AddWordFragment : Fragment() {

    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!

    private val wordViewModel: WordViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        val userRepository = UserRepository(database.userDao())
        val wordRepository = WordRepository(database.wordDao())
        val sessionRepository = com.example.flashcardapp.repository.SessionRepository(database.studySessionDao())
        MainViewModelFactory(userRepository, wordRepository, sessionRepository)
    }

    private var categories = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        setupCategoryDropdown()
        setupLanguageDropdown()

        // DOĞRU ATAMA: "+" butonuna (btnAddCategory) tıklanınca pencereyi aç
        binding.btnAddCategory.setOnClickListener {
            showAddCategoryDialog()
        }

        binding.btnSaveWord.setOnClickListener {
            val word = binding.etWord.text.toString().trim()
            val meaning = binding.etMeaning.text.toString().trim()
            val example = binding.etExample.text.toString().trim()
            val category = binding.actvCategory.text.toString().trim()
            val languageName = binding.actvLanguage.text.toString().trim()

            val languageCode = when (languageName) {
                getString(R.string.lang_turkish) -> "tr"
                getString(R.string.lang_dutch) -> "nl"
                getString(R.string.lang_spanish) -> "es"
                getString(R.string.lang_french) -> "fr"
                getString(R.string.lang_german) -> "de"
                else -> "en"
            }

            if (word.isEmpty()) {
                binding.tilWord.error = "Original word is required"
                return@setOnClickListener
            }

            if (meaning.isEmpty()) {
                binding.tilMeaning.error = "Meaning is required"
                return@setOnClickListener
            }
            
            val finalCategory = if (category.isEmpty()) getString(R.string.category_general) else category

            wordViewModel.saveWord(word, meaning, example, finalCategory, null, languageCode)
            Toast.makeText(requireContext(), "Word saved!", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun setupCategoryDropdown() {
        viewLifecycleOwner.lifecycleScope.launch {
            val existingCategories = wordViewModel.loadAllWords().map { it.category }.distinct()
            val defaultCategories = listOf(
                getString(R.string.category_general),
                getString(R.string.category_business),
                getString(R.string.category_travel),
                getString(R.string.category_other),
                "Market",
                "Daily Life",
                "News",
                "From Book"
            )
            categories = (defaultCategories + existingCategories).distinct().sorted().toMutableList()
            updateCategoryAdapter()
        }
    }

    private fun updateCategoryAdapter() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding.actvCategory.setAdapter(adapter)
        if (binding.actvCategory.text.isEmpty()) {
            binding.actvCategory.setText(getString(R.string.category_general), false)
        }
    }

    private fun showAddCategoryDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add New Category")
        
        val input = EditText(requireContext())
        input.hint = "Category Name"
        builder.setView(input)

        builder.setPositiveButton("Add") { dialog, _ ->
            val newCat = input.text.toString().trim()
            if (newCat.isNotEmpty() && !categories.contains(newCat)) {
                categories.add(newCat)
                categories.sort()
                updateCategoryAdapter()
                binding.actvCategory.setText(newCat, false)
                Toast.makeText(requireContext(), "Category added!", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun setupLanguageDropdown() {
        val languages = listOf(
            getString(R.string.lang_english),
            getString(R.string.lang_turkish),
            getString(R.string.lang_dutch),
            getString(R.string.lang_spanish),
            getString(R.string.lang_french),
            getString(R.string.lang_german)
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, languages)
        binding.actvLanguage.setAdapter(adapter)
        binding.actvLanguage.setText(getString(R.string.lang_english), false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}