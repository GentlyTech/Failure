package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.ViewModels.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentAddCourseBinding;

public class AddCourseFragment extends Fragment {
    MainViewModel mainViewModel;
    NavController navController;
    private FragmentAddCourseBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonCancelAddCourse = view.findViewById(R.id.buttonCancelAddCourse);
        Button buttonConfirmAddCourse = view.findViewById(R.id.buttonConfirmAddCourse);
        TextInputLayout editTextLayoutCourseStartDate = view.findViewById(R.id.editTextLayoutCourseStartDate);
        TextInputLayout editTextLayoutCourseEndDate = view.findViewById(R.id.editTextLayoutCourseEndDate);

        buttonCancelAddCourse.setOnClickListener(button -> navController.popBackStack());
    }
}