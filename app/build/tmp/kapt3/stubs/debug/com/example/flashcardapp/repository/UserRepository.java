package com.example.flashcardapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\fR\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/flashcardapp/repository/UserRepository;", "", "userDao", "Lcom/example/flashcardapp/data/local/dao/UserDao;", "(Lcom/example/flashcardapp/data/local/dao/UserDao;)V", "user", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/flashcardapp/data/local/entity/User;", "getUser", "()Lkotlinx/coroutines/flow/Flow;", "saveUser", "", "(Lcom/example/flashcardapp/data/local/entity/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUser", "app_debug"})
public final class UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.flashcardapp.data.local.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.example.flashcardapp.data.local.entity.User> user = null;
    
    public UserRepository(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.dao.UserDao userDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.flashcardapp.data.local.entity.User> getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUser(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUser(@org.jetbrains.annotations.NotNull()
    com.example.flashcardapp.data.local.entity.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}