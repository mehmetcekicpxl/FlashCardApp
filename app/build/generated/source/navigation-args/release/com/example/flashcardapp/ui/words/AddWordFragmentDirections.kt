package com.example.flashcardapp.ui.words

import androidx.navigation.NavDirections
import com.example.flashcardapp.NavGraphDirections

public class AddWordFragmentDirections private constructor() {
  public companion object {
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
