package com.example.flashcardapp.data.local.dao

import androidx.room.*
import com.example.flashcardapp.data.local.entity.StudiedWord
import com.example.flashcardapp.data.local.entity.StudySession
import kotlinx.coroutines.flow.Flow

@Dao
interface StudySessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: StudySession)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudiedWords(words: List<StudiedWord>)

    @Query("SELECT * FROM study_sessions ORDER BY date DESC")
    fun getAllSessions(): Flow<List<StudySession>>

    @Query("SELECT SUM(durationMinutes) FROM study_sessions")
    fun getTotalStudyTime(): Flow<Int>

    @Query("SELECT SUM(questionsAnswered) FROM study_sessions WHERE date >= :startOfDay")
    fun getTodayStudyCount(startOfDay: Long): Flow<Int?>

    @Query("SELECT COUNT(DISTINCT wordId) FROM studied_words WHERE date >= :startOfDay")
    fun getTodayUniqueStudyCount(startOfDay: Long): Flow<Int?>

    @Query("SELECT * FROM studied_words WHERE date >= :sinceDate")
    fun getStudiedWordsSince(sinceDate: Long): Flow<List<StudiedWord>>
}
