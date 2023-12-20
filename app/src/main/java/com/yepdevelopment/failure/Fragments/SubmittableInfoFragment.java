package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yepdevelopment.failure.Database.AppDatabase;
import com.yepdevelopment.failure.Database.Entities.Submittable;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentSubmittableInfoBinding;

public class SubmittableInfoFragment extends Fragment {
    MainViewModel mainViewModel;
    Submittable submittable;
    NavController navController;
    AppDatabase database;
    private FragmentSubmittableInfoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = NavHostFragment.findNavController(this);
        database = AppDatabase.getInstance(requireContext());

        submittable = mainViewModel.getSelectedSubmittable().getValue();
        if (submittable == null) navController.popBackStack();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubmittableInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().setTitle(getString(R.string.submittableInfoFragmentTitle, submittable.getName()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainViewModel.setSelectedSubmittable(null);
    }
}