package com.example.flashcardapp.logic

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

/**
 * Manages Text-To-Speech functionality for the application.
 */
class TextToSpeechManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    /**
     * Speaks the given text in the specified language.
     * @param text The text to speak.
     * @param langCode Language code (e.g., "tr", "nl", "en").
     */
    fun speak(text: String, langCode: String = "en") {
        if (!isInitialized) return

        val locale = try {
            Locale(langCode)
        } catch (e: Exception) {
            Locale.ENGLISH
        }

        tts?.language = locale
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
