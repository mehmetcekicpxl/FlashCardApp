package com.example.flashcardapp.ui.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flashcardapp.R
import com.example.flashcardapp.databinding.FragmentPracticeSummaryBinding

class PracticeSummaryFragment : Fragment() {

    private var _binding: FragmentPracticeSummaryBinding? = null
    private val binding get() = _binding!!
    private val args: PracticeSummaryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPracticeSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val total = args.correctCount + args.wrongCount
        val percent = if (total > 0) (args.correctCount * 100 / total) else 0

        // Set text values
        binding.tvScorePercent.text = "$percent%"
        binding.tvCorrectCount.text = args.correctCount.toString()
        binding.tvWrongCount.text = args.wrongCount.toString()
        binding.tvDuration.text = "${args.durationMinutes} min"
        
        // Progress bar animation (removed legacy pbScore)

        // Colors based on performance
        val scoreColor = when {
            percent >= 80 -> "#2E7D32" // Green
            percent >= 50 -> "#F57C00" // Orange
            else -> "#C62828" // Red
        }
        binding.tvScorePercent.setTextColor(android.graphics.Color.parseColor(scoreColor))

        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboardFragment)
        }

        binding.btnRetry.setOnClickListener {
            // Restart based on the mode we came from
            when (args.practiceMode) {
                "flashcards" -> findNavController().navigate(R.id.action_global_practiceFragment)
                "fill_blanks" -> findNavController().navigate(R.id.action_global_fillBlanksFragment)
                "matching" -> findNavController().navigate(R.id.action_global_matchingFragment)
                else -> findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
