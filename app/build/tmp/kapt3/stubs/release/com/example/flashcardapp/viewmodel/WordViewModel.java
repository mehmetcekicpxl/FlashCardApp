package com.example.flashcardapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0012J>\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00152\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u0015J\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/example/flashcardapp/viewmodel/WordViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/flashcardapp/repository/WordRepository;", "(Lcom/example/flashcardapp/repository/WordRepository;)V", "allWords", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/flashcardapp/data/local/entity/Word;", "getAllWords", "()Lkotlinx/coroutines/flow/StateFlow;", "totalWordsCount", "", "getTotalWordsCount", "deleteWord", "", "word", "loadAllWords", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveWord", "originalWord", "", "meaning", "exampleSentence", "category", "imageUrl", "languageCode", "updateWord", "app_release"})
public final class WordViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.flashcardapp.repository.WordRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.flashcardapp.data.local.entity.Word>> allWords = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalWordsCount = null;
    
    public WordViewModel(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.repository.WordRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.flashcardapp.data.local.entity.Word>> getAllWords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTotalWordsCount() {
        return null;
    }
    
    public final void saveWord(@org.jetbrains.annotations.NotNull()
    java.lang.String originalWord, @org.jetbrains.annotations.NotNull()
    java.lang.String meaning, @org.jetbrains.annotations.NotNull()
    java.lang.String exampleSentence, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String languageCode) {
    }
    
    public final void deleteWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadAllWords(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.flashcardapp.data.local.entity.Word>> $completion) {
        return null;
    }
    
    public final void updateWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word) {
    }
}