package com.example.flashcardapp.logic

import com.example.flashcardapp.data.local.entity.Word
import java.util.Calendar
import java.util.concurrent.TimeUnit

/**
 * Implementation of the SM-2 Spaced Repetition Algorithm.
 */
object SrsLogic {

    /**
     * Calculates the next review state for a word based on the user's performance.
     * @param word The current word state.
     * @param quality Quality of response (0-5).
     *                5: perfect response
     *                4: correct response after a hesitation
     *                3: correct response recalled with serious difficulty
     *                2: incorrect response; where the correct one seemed easy to recall
     *                1: incorrect response; the correct one remembered
     *                0: complete blackout.
     * @return Updated Word entity with new SRS values.
     */
    fun calculateNextReview(word: Word, quality: Int): Word {
        var interval: Int
        var easinessFactor = word.easinessFactor
        var level: Int // Using reviewInterval as a proxy for level if level isn't explicit, 
                       // but SM-2 typically uses 'n' as number of consecutive correct reviews.
                       // Let's use internal logic to track n.
        
        // SM-2 logic:
        // n: number of repetitions
        // EF: easiness factor
        // I: interval
        
        // Since we don't have 'n' in the entity, let's treat interval = 0 as n = 0.
        // Or better, let's assume 'reviewInterval' is the current interval and we calculate the next.
        
        // Quality below 3 means the word was forgotten or poorly remembered.
        if (quality < 3) {
            interval = 1
            // We don't reset EF, but we restart the interval sequence.
        } else {
            // Quality >= 3
            // Calculate new EF
            easinessFactor += (0.1f - (5 - quality) * (0.08f + (5 - quality) * 0.02f))
            if (easinessFactor < 1.3f) easinessFactor = 1.3f
            
            // Calculate interval
            interval = when (word.reviewInterval) {
                0 -> 1
                1 -> 6
                else -> (word.reviewInterval * easinessFactor).toInt()
            }
        }
        
        // Calculate next review date
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, interval)
        
        return word.copy(
            nextReviewDate = calendar.timeInMillis,
            reviewInterval = interval,
            easinessFactor = easinessFactor
        )
    }

    /**
     * Simple mapping for "Knew It" (4) and "Forgot" (1)
     */
    fun updateWithBinaryResult(word: Word, knewIt: Boolean): Word {
        return calculateNextReview(word, if (knewIt) 4 else 1)
    }
}
