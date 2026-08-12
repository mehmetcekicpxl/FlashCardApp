package com.example.flashcardapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val dailyGoal: Int = 15,
    val currentStreak: Int = 0,
    val lastStudyDate: Long = 0
)
