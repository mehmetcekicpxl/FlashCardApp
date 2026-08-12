package com.example.flashcardapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00100\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b\u00a8\u0006\u001c"}, d2 = {"Lcom/example/flashcardapp/viewmodel/UserViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/flashcardapp/repository/UserRepository;", "sessionRepository", "Lcom/example/flashcardapp/repository/SessionRepository;", "(Lcom/example/flashcardapp/repository/UserRepository;Lcom/example/flashcardapp/repository/SessionRepository;)V", "todayStudyCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "getTodayStudyCount", "()Lkotlinx/coroutines/flow/StateFlow;", "user", "Lcom/example/flashcardapp/data/local/entity/User;", "getUser", "weeklyStudyStats", "", "getWeeklyStudyStats", "getDaysAgo", "", "days", "getStartOfDay", "saveUserName", "", "name", "", "updateDailyGoal", "goal", "app_release"})
public final class UserViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.flashcardapp.repository.UserRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.flashcardapp.repository.SessionRepository sessionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.flashcardapp.data.local.entity.User> user = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> todayStudyCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.Integer, java.lang.Integer>> weeklyStudyStats = null;
    
    public UserViewModel(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.repository.UserRepository repository, @org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.repository.SessionRepository sessionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.flashcardapp.data.local.entity.User> getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTodayStudyCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.Integer, java.lang.Integer>> getWeeklyStudyStats() {
        return null;
    }
    
    private final long getStartOfDay() {
        return 0L;
    }
    
    private final long getDaysAgo(int days) {
        return 0L;
    }
    
    public final void saveUserName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void updateDailyGoal(int goal) {
    }
}