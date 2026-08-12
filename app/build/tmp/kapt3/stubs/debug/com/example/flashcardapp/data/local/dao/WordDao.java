package com.example.flashcardapp.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u0011\u001a\u00020\nH\'J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u001a"}, d2 = {"Lcom/example/flashcardapp/data/local/dao/WordDao;", "", "deleteWord", "", "word", "Lcom/example/flashcardapp/data/local/entity/Word;", "(Lcom/example/flashcardapp/data/local/entity/Word;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "", "getAllWords", "getAllWordsList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalWordsCount", "", "getWordsByCategory", "category", "getWordsToReview", "currentDate", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordsToReviewByCategory", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWord", "updateWord", "app_debug"})
@androidx.room.Dao()
public abstract interface WordDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.Word word, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM words")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.Word>> getAllWords();
    
    @androidx.room.Query(value = "SELECT * FROM words WHERE nextReviewDate <= :currentDate ORDER BY nextReviewDate ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWordsToReview(long currentDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.flashcardapp.data.local.entity.Word>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM words")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalWordsCount();
    
    @androidx.room.Query(value = "SELECT * FROM words")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllWordsList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.flashcardapp.data.local.entity.Word>> $completion);
    
    @androidx.room.Query(value = "SELECT DISTINCT category FROM words")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllCategories();
    
    @androidx.room.Query(value = "SELECT * FROM words WHERE category = :category")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.Word>> getWordsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM words WHERE category = :category AND nextReviewDate <= :currentDate ORDER BY nextReviewDate ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWordsToReviewByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category, long currentDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.flashcardapp.data.local.entity.Word>> $completion);
}