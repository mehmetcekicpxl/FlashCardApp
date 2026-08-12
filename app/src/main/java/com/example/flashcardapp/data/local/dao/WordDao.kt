package com.example.flashcardapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.flashcardapp.data.local.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE nextReviewDate <= :currentDate ORDER BY nextReviewDate ASC")
    suspend fun getWordsToReview(currentDate: Long): List<Word>
    
    @Query("SELECT COUNT(*) FROM words")
    fun getTotalWordsCount(): Flow<Int>

    @Query("SELECT * FROM words")
    suspend fun getAllWordsList(): List<Word>

    @Query("SELECT DISTINCT category FROM words")
    fun getAllCategories(): Flow<List<String>>

    @Query("SELECT * FROM words WHERE category = :category")
    fun getWordsByCategory(category: String): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE category = :category AND nextReviewDate <= :currentDate ORDER BY nextReviewDate ASC")
    suspend fun getWordsToReviewByCategory(category: String, currentDate: Long): List<Word>
}
