package com.example.flashcardapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studied_words")
data class StudiedWord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val wordId: Int,
    val date: Long
)
