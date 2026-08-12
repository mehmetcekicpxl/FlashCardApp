package com.example.flashcardapp.logic;

/**
 * Manages Text-To-Speech functionality for the application.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0006\u0010\r\u001a\u00020\nJ\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/flashcardapp/logic/TextToSpeechManager;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isInitialized", "", "tts", "Landroid/speech/tts/TextToSpeech;", "onInit", "", "status", "", "shutdown", "speak", "text", "", "langCode", "app_release"})
public final class TextToSpeechManager implements android.speech.tts.TextToSpeech.OnInitListener {
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    private boolean isInitialized = false;
    
    public TextToSpeechManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    public void onInit(int status) {
    }
    
    /**
     * Speaks the given text in the specified language.
     * @param text The text to speak.
     * @param langCode Language code (e.g., "tr", "nl", "en").
     */
    public final void speak(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String langCode) {
    }
    
    public final void shutdown() {
    }
}