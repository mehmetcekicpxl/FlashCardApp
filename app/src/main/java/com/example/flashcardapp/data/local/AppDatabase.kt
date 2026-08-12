package com.example.flashcardapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashcardapp.data.local.dao.StudySessionDao
import com.example.flashcardapp.data.local.dao.UserDao
import com.example.flashcardapp.data.local.dao.WordDao
import com.example.flashcardapp.data.local.entity.StudiedWord
import com.example.flashcardapp.data.local.entity.StudySession
import com.example.flashcardapp.data.local.entity.User
import com.example.flashcardapp.data.local.entity.Word

@Database(entities = [User::class, Word::class, StudySession::class, StudiedWord::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun wordDao(): WordDao
    abstract fun studySessionDao(): StudySessionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "flashcard_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
