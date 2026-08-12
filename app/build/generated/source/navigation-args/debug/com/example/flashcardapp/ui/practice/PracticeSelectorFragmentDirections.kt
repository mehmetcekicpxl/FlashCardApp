package com.example.flashcardapp.ui.practice

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.flashcardapp.NavGraphDirections
import com.example.flashcardapp.R

public class PracticeSelectorFragmentDirections private constructor() {
  public companion object {
    public fun actionPracticeSelectorFragmentToPracticeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_practiceSelectorFragment_to_practiceFragment)

    public fun actionPracticeSelectorFragmentToFillBlanksFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_practiceSelectorFragment_to_fillBlanksFragment)

    public fun actionPracticeSelectorFragmentToMatchingFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_practiceSelectorFragment_to_matchingFragment)

    public fun actionGlobalDashboardFragment(): NavDirections =
        NavGraphDirections.actionGlobalDashboardFragment()

    public fun actionGlobalPracticeFragment(): NavDirections =
        NavGraphDirections.actionGlobalPracticeFragment()

    public fun actionGlobalFillBlanksFragment(): NavDirections =
        NavGraphDirections.actionGlobalFillBlanksFragment()

    public fun actionGlobalMatchingFragment(): NavDirections =
        NavGraphDirections.actionGlobalMatchingFragment()
  }
}
