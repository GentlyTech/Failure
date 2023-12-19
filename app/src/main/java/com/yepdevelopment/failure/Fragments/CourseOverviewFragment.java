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

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentCourseOverviewBinding;

public class CourseOverviewFragment extends Fragment {
    MainViewModel mainViewModel;
    NavController navController;
    Course course;
    private FragmentCourseOverviewBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = NavHostFragment.findNavController(this);

        course = mainViewModel.getSelectedCourse().getValue();
        if (course == null) navController.popBackStack();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCourseOverviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.includeCourseCardInOverview.textCourseCardCourseName.setText(course.getName());
        binding.includeCourseCardInOverview.textCourseCardCourseSubject.setText(course.getSubject());
        binding.includeCourseCardInOverview.textCourseCardCourseGrade.setText(String.format("%s%%", course.calculateGrade()));
        binding.includeCourseCardInOverview.imageCourseCardArrow.setVisibility(View.GONE);
    }
}