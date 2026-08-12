package com.example.flashcardapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flashcardapp.R
import com.example.flashcardapp.data.local.AppDatabase
import com.example.flashcardapp.databinding.FragmentDashboardBinding
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.UserViewModel
import com.example.flashcardapp.viewmodel.WordViewModel
import com.example.flashcardapp.ui.words.WordAdapter
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        val userRepository = UserRepository(database.userDao())
        val wordRepository = WordRepository(database.wordDao())
        val sessionRepository = SessionRepository(database.studySessionDao())
        MainViewModelFactory(userRepository, wordRepository, sessionRepository)
    }

    private val wordViewModel: WordViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        val userRepository = UserRepository(database.userDao())
        val wordRepository = WordRepository(database.wordDao())
        val sessionRepository = SessionRepository(database.studySessionDao())
        MainViewModelFactory(userRepository, wordRepository, sessionRepository)
    }

    private lateinit var wordAdapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        
        // Observe user data from Room DB
        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.user.collectLatest { user ->
                if (user != null) {
                    binding.tvGreeting.text = getString(R.string.dashboard_hello, user.name)
                    binding.tvStreak.text = user.currentStreak.toString()
                    binding.pbDailyProgress.max = user.dailyGoal
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.todayStudyCount.collectLatest { studied ->
                val goal = userViewModel.user.value?.dailyGoal ?: 15
                binding.pbDailyProgress.progress = studied
                binding.tvDailyProgressText.text = getString(R.string.daily_progress_format, studied, goal)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.totalWordsCount.collectLatest { count ->
                binding.tvTotalWords.text = getString(R.string.total_words, count)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.allWords.collectLatest { words ->
                // Sadece son eklenen 5 kelimeyi göster (Dashboard'u temiz tutmak için)
                val recentWords = words.takeLast(5).reversed()
                wordAdapter.submitList(recentWords)
            }
        }
        
        binding.btnAddWord.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addWordFragment)
        }

        binding.btnExtraPractice.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_practiceSelectorFragment)
        }

        binding.btnSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_wordListFragment)
        }
    }

    private fun setupRecyclerView() {
        wordAdapter = WordAdapter { word ->
            wordViewModel.deleteWord(word)
        }
        binding.rvWords.adapter = wordAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
