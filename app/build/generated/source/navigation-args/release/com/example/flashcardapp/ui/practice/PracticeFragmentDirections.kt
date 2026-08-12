package com.example.flashcardapp.ui.practice

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.flashcardapp.NavGraphDirections
import com.example.flashcardapp.R
import kotlin.Int
import kotlin.String

public class PracticeFragmentDirections private constructor() {
  private data class ActionPracticeFragmentToPracticeSummaryFragment(
    public val correctCount: Int,
    public val wrongCount: Int,
    public val durationMinutes: Int,
    public val practiceMode: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_practiceFragment_to_practiceSummaryFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("correctCount", this.correctCount)
        result.putInt("wrongCount", this.wrongCount)
        result.putInt("durationMinutes", this.durationMinutes)
        result.putString("practiceMode", this.practiceMode)
        return result
      }
  }

  public companion object {
    public fun actionPracticeFragmentToPracticeSummaryFragment(
      correctCount: Int,
      wrongCount: Int,
      durationMinutes: Int,
      practiceMode: String,
    ): NavDirections = ActionPracticeFragmentToPracticeSummaryFragment(correctCount, wrongCount,
        durationMinutes, practiceMode)

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
