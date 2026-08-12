package com.example.flashcardapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.repository.SessionRepository

class MainViewModelFactory(
    private val userRepository: UserRepository,
    private val wordRepository: WordRepository,
    private val sessionRepository: SessionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository, sessionRepository) as T
        }
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(wordRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
