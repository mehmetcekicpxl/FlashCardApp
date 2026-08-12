package com.example.flashcardapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcardapp.R
import com.example.flashcardapp.data.local.AppDatabase
import com.example.flashcardapp.databinding.FragmentOnboardingBinding
import com.example.flashcardapp.repository.UserRepository
import com.example.flashcardapp.repository.WordRepository
import com.example.flashcardapp.repository.SessionRepository
import com.example.flashcardapp.viewmodel.MainViewModelFactory
import com.example.flashcardapp.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        val userRepository = UserRepository(database.userDao())
        val wordRepository = WordRepository(database.wordDao())
        val sessionRepository = com.example.flashcardapp.repository.SessionRepository(database.studySessionDao())
        MainViewModelFactory(userRepository, wordRepository, sessionRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check if user already exists
        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.user.collectLatest { user ->
                if (user != null) {
                    navigateToDashboard()
                }
            }
        }

        binding.btnContinue.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            if (name.isNotEmpty()) {
                userViewModel.saveUserName(name)
                // The collectLatest above will automatically catch the state change and navigate
            } else {
                binding.tilName.error = "Please enter your name"
            }
        }
    }

    private fun navigateToDashboard() {
        findNavController().navigate(R.id.action_onboardingFragment_to_dashboardFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
