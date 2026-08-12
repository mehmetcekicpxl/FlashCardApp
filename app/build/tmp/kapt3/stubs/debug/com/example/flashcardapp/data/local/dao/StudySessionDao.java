package com.example.flashcardapp.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\'J\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00032\u0006\u0010\f\u001a\u00020\tH\'J\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00032\u0006\u0010\f\u001a\u00020\tH\'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\'J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/flashcardapp/data/local/dao/StudySessionDao;", "", "getAllSessions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/flashcardapp/data/local/entity/StudySession;", "getStudiedWordsSince", "Lcom/example/flashcardapp/data/local/entity/StudiedWord;", "sinceDate", "", "getTodayStudyCount", "", "startOfDay", "getTodayUniqueStudyCount", "getTotalStudyTime", "insertSession", "", "session", "(Lcom/example/flashcardapp/data/local/entity/StudySession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertStudiedWords", "words", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface StudySessionDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSession(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.StudySession session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertStudiedWords(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.flashcardapp.data.local.entity.StudiedWord> words, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM study_sessions ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.StudySession>> getAllSessions();
    
    @androidx.room.Query(value = "SELECT SUM(durationMinutes) FROM study_sessions")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalStudyTime();
    
    @androidx.room.Query(value = "SELECT SUM(questionsAnswered) FROM study_sessions WHERE date >= :startOfDay")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getTodayStudyCount(long startOfDay);
    
    @androidx.room.Query(value = "SELECT COUNT(DISTINCT wordId) FROM studied_words WHERE date >= :startOfDay")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getTodayUniqueStudyCount(long startOfDay);
    
    @androidx.room.Query(value = "SELECT * FROM studied_words WHERE date >= :sinceDate")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.StudiedWord>> getStudiedWordsSince(long sinceDate);
}