package com.example.flashcardapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_sessions")
data class StudySession(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long,
    val durationMinutes: Int,
    val questionsAnswered: Int,
    val correctAnswers: Int
)
