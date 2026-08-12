package com.example.flashcardapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardapp.data.local.entity.User
import com.example.flashcardapp.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Calendar

class UserViewModel(
    private val repository: UserRepository,
    private val sessionRepository: com.example.flashcardapp.repository.SessionRepository
) : ViewModel() {

    val user: StateFlow<User?> = repository.user.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val todayStudyCount: StateFlow<Int> = sessionRepository.getTodayUniqueStudyCount(getStartOfDay())
        .map { it ?: 0 }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val weeklyStudyStats: StateFlow<Map<Int, Int>> = sessionRepository.getStudiedWordsSince(getDaysAgo(7))
        .map { words ->
            val stats = mutableMapOf<Int, Int>()
            val cal = Calendar.getInstance()
            words.forEach { word ->
                cal.timeInMillis = word.date
                val day = cal.get(Calendar.DAY_OF_WEEK)
                stats[day] = stats.getOrDefault(day, 0) + 1
            }
            stats
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyMap()
        )

    private fun getStartOfDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun getDaysAgo(days: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    fun saveUserName(name: String) {
        viewModelScope.launch {
            val newUser = User(name = name)
            repository.saveUser(newUser)
        }
    }

    fun updateDailyGoal(goal: Int) {
        val currentUser = user.value
        if (currentUser != null) {
            viewModelScope.launch {
                repository.updateUser(currentUser.copy(dailyGoal = goal))
            }
        }
    }
}
