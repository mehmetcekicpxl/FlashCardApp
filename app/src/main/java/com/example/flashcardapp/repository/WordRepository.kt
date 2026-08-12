package com.example.flashcardapp.repository

import com.example.flashcardapp.data.local.dao.WordDao
import com.example.flashcardapp.data.local.entity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAllWords()
    val totalWordsCount: Flow<Int> = wordDao.getTotalWordsCount()

    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

    suspend fun updateWord(word: Word) {
        wordDao.updateWord(word)
    }

    suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    suspend fun getWordsToReview(currentDate: Long): List<Word> {
        return wordDao.getWordsToReview(currentDate)
    }

    suspend fun getAllWordsList(): List<Word> {
        return wordDao.getAllWordsList()
    }

    fun getAllCategories(): Flow<List<String>> = wordDao.getAllCategories()

    fun getWordsByCategory(category: String): Flow<List<Word>> = wordDao.getWordsByCategory(category)

    suspend fun getWordsToReviewByCategory(category: String, currentDate: Long): List<Word> {
        return wordDao.getWordsToReviewByCategory(category, currentDate)
    }
}
