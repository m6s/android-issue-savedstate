package com.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;
    private NestedGraphViewModel viewModel;
//    private FixedNestedGraphViewModel viewModel;

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding = FragmentSecondBinding.bind(view);
        binding.nextButton.setOnClickListener(this::onNextClick);
        NavController navController = Navigation.findNavController(binding.getRoot());
        ViewModelStoreOwner storeOwner = navController.getViewModelStoreOwner(R.id.nested_graph);
        viewModel = NestedGraphViewModel.of(requireParentFragment(), storeOwner);
//        viewModel = FixedNestedGraphViewModel.of(requireParentFragment(), storeOwner);
        binding.nextButton.setOnClickListener(this::onNextClick);
        binding.textView.setText(viewModel.textData.getValue());
    }

    private void onNextClick(View view) {
        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.navigate(R.id.action_secondFragment_to_thirdFragment);
    }
}
