package com.example.flashcardapp.repository

import com.example.flashcardapp.data.local.dao.StudySessionDao
import com.example.flashcardapp.data.local.entity.StudiedWord
import com.example.flashcardapp.data.local.entity.StudySession
import kotlinx.coroutines.flow.Flow

class SessionRepository(private val dao: StudySessionDao) {

    val allSessions: Flow<List<StudySession>> = dao.getAllSessions()
    val totalStudyTime: Flow<Int> = dao.getTotalStudyTime()

    suspend fun saveSession(session: StudySession) {
        dao.insertSession(session)
    }

    suspend fun saveStudiedWords(words: List<StudiedWord>) {
        dao.insertStudiedWords(words)
    }

    fun getTodayStudyCount(startOfDay: Long): Flow<Int?> {
        return dao.getTodayStudyCount(startOfDay)
    }

    fun getTodayUniqueStudyCount(startOfDay: Long): Flow<Int?> {
        return dao.getTodayUniqueStudyCount(startOfDay)
    }

    fun getStudiedWordsSince(sinceDate: Long): Flow<List<StudiedWord>> {
        return dao.getStudiedWordsSince(sinceDate)
    }
}
