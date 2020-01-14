package com.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;

    public FirstFragment() {
        super(R.layout.fragment_first);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding = FragmentFirstBinding.bind(view);
        binding.nextButton.setOnClickListener(this::onNextClick);
    }

    private void onNextClick(View view) {
        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.navigate(R.id.action_firstFragment_to_nested_graph);
    }
}
