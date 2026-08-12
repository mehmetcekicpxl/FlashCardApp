package com.example.flashcardapp.ui.onboarding

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.flashcardapp.NavGraphDirections
import com.example.flashcardapp.R

public class OnboardingFragmentDirections private constructor() {
  public companion object {
    public fun actionOnboardingFragmentToDashboardFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_onboardingFragment_to_dashboardFragment)

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
