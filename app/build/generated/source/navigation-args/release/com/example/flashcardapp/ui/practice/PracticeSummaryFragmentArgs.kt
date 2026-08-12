package com.example.flashcardapp.ui.practice

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class PracticeSummaryFragmentArgs(
  public val correctCount: Int,
  public val wrongCount: Int,
  public val durationMinutes: Int,
  public val practiceMode: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("correctCount", this.correctCount)
    result.putInt("wrongCount", this.wrongCount)
    result.putInt("durationMinutes", this.durationMinutes)
    result.putString("practiceMode", this.practiceMode)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("correctCount", this.correctCount)
    result.set("wrongCount", this.wrongCount)
    result.set("durationMinutes", this.durationMinutes)
    result.set("practiceMode", this.practiceMode)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): PracticeSummaryFragmentArgs {
      bundle.setClassLoader(PracticeSummaryFragmentArgs::class.java.classLoader)
      val __correctCount : Int
      if (bundle.containsKey("correctCount")) {
        __correctCount = bundle.getInt("correctCount")
      } else {
        throw IllegalArgumentException("Required argument \"correctCount\" is missing and does not have an android:defaultValue")
      }
      val __wrongCount : Int
      if (bundle.containsKey("wrongCount")) {
        __wrongCount = bundle.getInt("wrongCount")
      } else {
        throw IllegalArgumentException("Required argument \"wrongCount\" is missing and does not have an android:defaultValue")
      }
      val __durationMinutes : Int
      if (bundle.containsKey("durationMinutes")) {
        __durationMinutes = bundle.getInt("durationMinutes")
      } else {
        throw IllegalArgumentException("Required argument \"durationMinutes\" is missing and does not have an android:defaultValue")
      }
      val __practiceMode : String?
      if (bundle.containsKey("practiceMode")) {
        __practiceMode = bundle.getString("practiceMode")
        if (__practiceMode == null) {
          throw IllegalArgumentException("Argument \"practiceMode\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"practiceMode\" is missing and does not have an android:defaultValue")
      }
      return PracticeSummaryFragmentArgs(__correctCount, __wrongCount, __durationMinutes,
          __practiceMode)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        PracticeSummaryFragmentArgs {
      val __correctCount : Int?
      if (savedStateHandle.contains("correctCount")) {
        __correctCount = savedStateHandle["correctCount"]
        if (__correctCount == null) {
          throw IllegalArgumentException("Argument \"correctCount\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"correctCount\" is missing and does not have an android:defaultValue")
      }
      val __wrongCount : Int?
      if (savedStateHandle.contains("wrongCount")) {
        __wrongCount = savedStateHandle["wrongCount"]
        if (__wrongCount == null) {
          throw IllegalArgumentException("Argument \"wrongCount\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"wrongCount\" is missing and does not have an android:defaultValue")
      }
      val __durationMinutes : Int?
      if (savedStateHandle.contains("durationMinutes")) {
        __durationMinutes = savedStateHandle["durationMinutes"]
        if (__durationMinutes == null) {
          throw IllegalArgumentException("Argument \"durationMinutes\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"durationMinutes\" is missing and does not have an android:defaultValue")
      }
      val __practiceMode : String?
      if (savedStateHandle.contains("practiceMode")) {
        __practiceMode = savedStateHandle["practiceMode"]
        if (__practiceMode == null) {
          throw IllegalArgumentException("Argument \"practiceMode\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"practiceMode\" is missing and does not have an android:defaultValue")
      }
      return PracticeSummaryFragmentArgs(__correctCount, __wrongCount, __durationMinutes,
          __practiceMode)
    }
  }
}
