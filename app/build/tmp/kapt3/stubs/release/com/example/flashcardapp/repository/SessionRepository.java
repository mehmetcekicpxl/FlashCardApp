package com.example.flashcardapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00062\u0006\u0010\u0013\u001a\u00020\u0011J\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00062\u0006\u0010\u0013\u001a\u00020\u0011J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u0086@\u00a2\u0006\u0002\u0010\u001bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/example/flashcardapp/repository/SessionRepository;", "", "dao", "Lcom/example/flashcardapp/data/local/dao/StudySessionDao;", "(Lcom/example/flashcardapp/data/local/dao/StudySessionDao;)V", "allSessions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/flashcardapp/data/local/entity/StudySession;", "getAllSessions", "()Lkotlinx/coroutines/flow/Flow;", "totalStudyTime", "", "getTotalStudyTime", "getStudiedWordsSince", "Lcom/example/flashcardapp/data/local/entity/StudiedWord;", "sinceDate", "", "getTodayStudyCount", "startOfDay", "getTodayUniqueStudyCount", "saveSession", "", "session", "(Lcom/example/flashcardapp/data/local/entity/StudySession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveStudiedWords", "words", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class SessionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.flashcardapp.data.local.dao.StudySessionDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.StudySession>> allSessions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> totalStudyTime = null;
    
    public SessionRepository(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.dao.StudySessionDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.StudySession>> getAllSessions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalStudyTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveSession(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.StudySession session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveStudiedWords(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.flashcardapp.data.local.entity.StudiedWord> words, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTodayStudyCount(long startOfDay) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTodayUniqueStudyCount(long startOfDay) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.flashcardapp.data.local.entity.StudiedWord>> getStudiedWordsSince(long sinceDate) {
        return null;
    }
}