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
public class FixedNestedGraphViewModel extends ViewModel {
    public final MutableLiveData<String> textData;
    private SavedStateRegistryOwner registryOwner;

    public FixedNestedGraphViewModel(SavedStateHandle handle) {
        textData = handle.getLiveData("textData", "Test");
    }

    public static FixedNestedGraphViewModel of(SavedStateRegistryOwner registryOwner,
                                               ViewModelStoreOwner storeOwner) {
        FixedNestedGraphViewModel viewModel = new ViewModelProvider(storeOwner,
                new AbstractSavedStateViewModelFactory(registryOwner, null) {
                    @NonNull
                    @Override
                    protected <M extends ViewModel> M create(@NonNull String key,
                                                             @NonNull Class<M> modelClass,
                                                             @NonNull SavedStateHandle handle) {
                        return (M) new FixedNestedGraphViewModel(handle);
                    }
                }).get(generateKey(), FixedNestedGraphViewModel.class);
        viewModel.registryOwner = registryOwner;
        return viewModel;
    }

    private static String generateKey() {
        String canonicalName = FixedNestedGraphViewModel.class.getCanonicalName();
        return "androidx.lifecycle.ViewModelProvider.DefaultKey" + ":" + canonicalName;
    }

    @Override
    protected void onCleared() {
        registryOwner.getSavedStateRegistry().unregisterSavedStateProvider(generateKey());
    }
}
