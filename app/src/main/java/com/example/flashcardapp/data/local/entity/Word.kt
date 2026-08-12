package com.example.flashcardapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val originalWord: String,
    val meaning: String,
    val exampleSentence: String,
    val nextReviewDate: Long = 0,
    val reviewInterval: Int = 1,
    val easinessFactor: Float = 2.5f,
    val category: String = "General",
    val imageUrl: String? = null,
    val languageCode: String = "en"
)
