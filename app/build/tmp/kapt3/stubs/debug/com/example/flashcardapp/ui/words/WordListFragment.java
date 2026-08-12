package com.example.flashcardapp.ui.words;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0016H\u0016J\u001a\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0016\u0010#\u001a\u00020\u00162\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\b\u0010%\u001a\u00020\u0016H\u0002J\b\u0010&\u001a\u00020\u0016H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\'"}, d2 = {"Lcom/example/flashcardapp/ui/words/WordListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/flashcardapp/databinding/FragmentWordListBinding;", "allWords", "", "Lcom/example/flashcardapp/data/local/entity/Word;", "binding", "getBinding", "()Lcom/example/flashcardapp/databinding/FragmentWordListBinding;", "selectedCategory", "", "wordAdapter", "Lcom/example/flashcardapp/ui/words/WordAdapter;", "wordViewModel", "Lcom/example/flashcardapp/viewmodel/WordViewModel;", "getWordViewModel", "()Lcom/example/flashcardapp/viewmodel/WordViewModel;", "wordViewModel$delegate", "Lkotlin/Lazy;", "filterWords", "", "query", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupCategoryChips", "words", "setupRecyclerView", "setupSearchView", "app_debug"})
public final class WordListFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.flashcardapp.databinding.FragmentWordListBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy wordViewModel$delegate = null;
    private com.example.flashcardapp.ui.words.WordAdapter wordAdapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.flashcardapp.data.local.entity.Word> allWords;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String selectedCategory = "All";
    
    public WordListFragment() {
        super();
    }
    
    private final com.example.flashcardapp.databinding.FragmentWordListBinding getBinding() {
        return null;
    }
    
    private final com.example.flashcardapp.viewmodel.WordViewModel getWordViewModel() {
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
    
    private final void setupRecyclerView() {
    }
    
    private final void setupCategoryChips(java.util.List<com.example.flashcardapp.data.local.entity.Word> words) {
    }
    
    private final void setupSearchView() {
    }
    
    private final void filterWords(java.lang.String query) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}