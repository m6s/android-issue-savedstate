package com.example;

import androidx.annotation.NonNull;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;

/**
 * @author Matthias Schmitt
 */
public class NestedGraphViewModel extends ViewModel {
    public final MutableLiveData<String> textData;

    public NestedGraphViewModel(SavedStateHandle handle) {
        textData = handle.getLiveData("textData", "Test");
    }

    public static NestedGraphViewModel of(SavedStateRegistryOwner registryOwner,
                                          ViewModelStoreOwner storeOwner) {
        NestedGraphViewModel viewModel = new ViewModelProvider(storeOwner,
                new AbstractSavedStateViewModelFactory(registryOwner, null) {
                    @NonNull
                    @Override
                    protected <M extends ViewModel> M create(@NonNull String key,
                                                             @NonNull Class<M> modelClass,
                                                             @NonNull SavedStateHandle handle) {
                        return (M) new NestedGraphViewModel(handle);
                    }
                }).get(NestedGraphViewModel.class);
        return viewModel;
    }
}
