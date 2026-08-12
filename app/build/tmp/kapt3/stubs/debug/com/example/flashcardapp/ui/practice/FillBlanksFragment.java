package com.example.flashcardapp.ui.practice;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020#H\u0002J$\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u00020#H\u0016J\u001a\u0010.\u001a\u00020#2\u0006\u0010/\u001a\u00020&2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u00100\u001a\u00020#H\u0002J\b\u00101\u001a\u00020#H\u0002J\u0010\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020\u0012H\u0002J\u0010\u00104\u001a\u00020#2\u0006\u00105\u001a\u00020\tH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lcom/example/flashcardapp/ui/practice/FillBlanksFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/flashcardapp/databinding/FragmentFillBlanksBinding;", "binding", "getBinding", "()Lcom/example/flashcardapp/databinding/FragmentFillBlanksBinding;", "correctCount", "", "currentIndex", "isProcessing", "", "sessionStartMs", "", "studiedWordIds", "", "userName", "", "userViewModel", "Lcom/example/flashcardapp/viewmodel/UserViewModel;", "getUserViewModel", "()Lcom/example/flashcardapp/viewmodel/UserViewModel;", "userViewModel$delegate", "Lkotlin/Lazy;", "wordViewModel", "Lcom/example/flashcardapp/viewmodel/WordViewModel;", "getWordViewModel", "()Lcom/example/flashcardapp/viewmodel/WordViewModel;", "wordViewModel$delegate", "wordsList", "", "Lcom/example/flashcardapp/data/local/entity/Word;", "wrongCount", "checkAnswer", "", "nextQuestion", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "saveSessionAndShowSummary", "showEmptyState", "showMotivation", "message", "showQuestion", "index", "app_debug"})
public final class FillBlanksFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.flashcardapp.databinding.FragmentFillBlanksBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy wordViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy userViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.flashcardapp.data.local.entity.Word> wordsList;
    private int currentIndex = 0;
    private int correctCount = 0;
    private int wrongCount = 0;
    private long sessionStartMs = 0L;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userName = "";
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> studiedWordIds = null;
    private boolean isProcessing = false;
    
    public FillBlanksFragment() {
        super();
    }
    
    private final com.example.flashcardapp.databinding.FragmentFillBlanksBinding getBinding() {
        return null;
    }
    
    private final com.example.flashcardapp.viewmodel.WordViewModel getWordViewModel() {
        return null;
    }
    
    private final com.example.flashcardapp.viewmodel.UserViewModel getUserViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showQuestion(int index) {
    }
    
    private final void checkAnswer() {
    }
    
    private final void showMotivation(java.lang.String message) {
    }
    
    private final void nextQuestion() {
    }
    
    private final void saveSessionAndShowSummary() {
    }
    
    private final void showEmptyState() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}