package com.example.flashcardapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardapp.data.local.entity.Word
import com.example.flashcardapp.repository.WordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val allWords: StateFlow<List<Word>> = repository.allWords.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val totalWordsCount: StateFlow<Int> = repository.totalWordsCount.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    fun saveWord(originalWord: String, meaning: String, exampleSentence: String, category: String = "General", imageUrl: String? = null, languageCode: String = "en") {
        viewModelScope.launch {
            val newWord = Word(
                originalWord = originalWord,
                meaning = meaning,
                exampleSentence = exampleSentence,
                category = category,
                imageUrl = imageUrl,
                languageCode = languageCode
            )
            repository.insertWord(newWord)
        }
    }

    fun deleteWord(word: Word) {
        viewModelScope.launch {
            repository.deleteWord(word)
        }
    }

    suspend fun loadAllWords(): List<Word> {
        return repository.getAllWordsList()
    }

    fun updateWord(word: Word) {
        viewModelScope.launch {
            repository.updateWord(word)
        }
    }
}
