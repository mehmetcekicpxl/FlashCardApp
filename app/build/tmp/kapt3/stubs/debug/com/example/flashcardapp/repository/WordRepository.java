package com.example.flashcardapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00070\u0006J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u0017\u001a\u00020\u0013J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0011R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/example/flashcardapp/repository/WordRepository;", "", "wordDao", "Lcom/example/flashcardapp/data/local/dao/WordDao;", "(Lcom/example/flashcardapp/data/local/dao/WordDao;)V", "allWords", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/flashcardapp/data/local/entity/Word;", "getAllWords", "()Lkotlinx/coroutines/flow/Flow;", "totalWordsCount", "", "getTotalWordsCount", "deleteWord", "", "word", "(Lcom/example/flashcardapp/data/local/entity/Word;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "", "getAllWordsList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordsByCategory", "category", "getWordsToReview", "currentDate", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordsToReviewByCategory", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWord", "updateWord", "app_debug"})
public final class WordRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.flashcardapp.data.local.dao.WordDao wordDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.Word>> allWords = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> totalWordsCount = null;
    
    public WordRepository(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.dao.WordDao wordDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.Word>> getAllWords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalWordsCount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWordsToReview(long currentDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.flashcardapp.data.local.entity.Word>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllWordsList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.flashcardapp.data.local.entity.Word>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.Word>> getWordsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWordsToReviewByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category, long currentDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.flashcardapp.data.local.entity.Word>> $completion) {
        return null;
    }
}