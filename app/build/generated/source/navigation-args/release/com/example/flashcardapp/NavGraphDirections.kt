package com.example.flashcardapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class NavGraphDirections private constructor() {
  public companion object {
    public fun actionGlobalDashboardFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_dashboardFragment)

    public fun actionGlobalPracticeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_practiceFragment)

    public fun actionGlobalFillBlanksFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_fillBlanksFragment)

    public fun actionGlobalMatchingFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_matchingFragment)
  }
}
