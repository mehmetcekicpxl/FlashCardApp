package com.example.flashcardapp.ui.dashboard

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.flashcardapp.NavGraphDirections
import com.example.flashcardapp.R

public class DashboardFragmentDirections private constructor() {
  public companion object {
    public fun actionDashboardFragmentToAddWordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardFragment_to_addWordFragment)

    public fun actionDashboardFragmentToPracticeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardFragment_to_practiceFragment)

    public fun actionDashboardFragmentToPracticeSelectorFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardFragment_to_practiceSelectorFragment)

    public fun actionDashboardFragmentToWordListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardFragment_to_wordListFragment)

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
