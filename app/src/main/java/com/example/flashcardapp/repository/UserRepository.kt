package com.example.flashcardapp.repository

import com.example.flashcardapp.data.local.dao.UserDao
import com.example.flashcardapp.data.local.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val user: Flow<User?> = userDao.getUser()

    suspend fun saveUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}
