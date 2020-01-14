package com.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {
    private FragmentThirdBinding binding;
    private NestedGraphViewModel viewModel;
//    private FixedNestedGraphViewModel viewModel;

    public ThirdFragment() {
        super(R.layout.fragment_third);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding = FragmentThirdBinding.bind(view);
        binding.saveButton.setOnClickListener(this::onSaveClick);
        NavController navController = Navigation.findNavController(binding.getRoot());
        ViewModelStoreOwner storeOwner = navController.getViewModelStoreOwner(R.id.nested_graph);
        viewModel = NestedGraphViewModel.of(requireParentFragment(), storeOwner);
//        viewModel = FixedNestedGraphViewModel.of(requireParentFragment(), storeOwner);
        binding.saveButton.setOnClickListener(this::onSaveClick);
        binding.editText.setText(viewModel.textData.getValue());
    }

    private void onSaveClick(View view) {
        viewModel.textData.setValue(binding.editText.getText().toString());
        Navigation.findNavController(binding.getRoot()).popBackStack();
    }
}
