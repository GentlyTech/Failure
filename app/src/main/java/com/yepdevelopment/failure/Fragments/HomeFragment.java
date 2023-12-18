package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.ViewModels.MainViewModel;

public class HomeFragment extends Fragment {
    NavController navController;
    MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton floatingActionButtonAddCourse = view.findViewById(R.id.floatingActionButtonAddCourse);

        floatingActionButtonAddCourse.setOnClickListener(ign -> navController.navigate(HomeFragmentDirections.actionHomeFragmentToAddCourseFragment()));
    }
}