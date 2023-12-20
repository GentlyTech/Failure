package com.yepdevelopment.failure.Fragments;

import static com.yepdevelopment.failure.Globals.DATE_FORMAT;

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

import com.yepdevelopment.failure.Database.AppDatabase;
import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.Android.Parsing;
import com.yepdevelopment.failure.Utils.JavaRX.Async;
import com.yepdevelopment.failure.Validators.AddCourseValidator;
import com.yepdevelopment.failure.Validators.CommonValidator;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentEditCourseBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditCourseFragment extends Fragment {
    MainViewModel mainViewModel;
    NavController navController;
    AppDatabase database;
    Course course;
    private FragmentEditCourseBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = NavHostFragment.findNavController(this);
        database = AppDatabase.getInstance(requireContext());

        course = mainViewModel.getSelectedCourse().getValue();
        if (course == null) navController.popBackStack();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.editCourseForm.editTextCourseName.setText(course.getName());
        binding.editCourseForm.editTextCourseSubject.setText(course.getSubject());
        binding.editCourseForm.editTextCourseStartDate.setText(course.getStartDate());
        binding.editCourseForm.editTextCourseEndDate.setText(course.getEndDate());
        binding.editCourseForm.editTextCourseMinimumGrade.setText(String.valueOf(course.getMinimumGrade()));

        binding.editCourseButtonLayout.simpleButtonPositiveAction.setText(R.string.save);
        binding.editCourseButtonLayout.simpleButtonNegativeAction.setText(R.string.cancel);

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.CANADA); // TODO maybe make locale dynamic

        binding.editCourseForm.editTextLayoutCourseStartDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editCourseForm.editTextCourseStartDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        binding.editCourseForm.editTextLayoutCourseEndDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editCourseForm.editTextCourseEndDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        binding.editCourseButtonLayout.simpleButtonPositiveAction.setOnClickListener(this::editCourse);
        binding.editCourseButtonLayout.simpleButtonNegativeAction.setOnClickListener(button -> navController.popBackStack());
    }

    public void clearFieldErrors() {
        binding.editCourseForm.editTextLayoutCourseName.setError(null);
        binding.editCourseForm.editTextLayoutCourseStartDate.setError(null);
        binding.editCourseForm.editTextLayoutCourseEndDate.setError(null);
    }

    public void editCourse(View button) {
        clearFieldErrors();

        String courseName = Parsing.editableToString(binding.editCourseForm.editTextCourseName.getText());
        String courseSubject = Parsing.editableToString(binding.editCourseForm.editTextCourseSubject.getText());
        String courseStartDate = Parsing.editableToString(binding.editCourseForm.editTextCourseStartDate.getText());
        String courseEndDate = Parsing.editableToString(binding.editCourseForm.editTextCourseEndDate.getText());
        float courseMinimumGrade = Parsing.editableToFloat(binding.editCourseForm.editTextCourseMinimumGrade.getText());

        boolean hasError = false;

        if (!AddCourseValidator.isCourseNameValid(courseName)) {
            binding.editCourseForm.editTextLayoutCourseName.setError(getString(R.string.editTextCourseName_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(courseStartDate)) {
            binding.editCourseForm.editTextLayoutCourseStartDate.setError(getString(R.string.editTextCourseStartDate_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(courseEndDate)) {
            binding.editCourseForm.editTextLayoutCourseEndDate.setError(getString(R.string.editTextCourseEndDate_errorHint));
            hasError = true;
        }

        if (hasError) return;

        Course updatedCourse = new Course(course.getId(), courseName, courseSubject, courseStartDate, courseEndDate, courseMinimumGrade);

        Async.run(database.courseDao().update(course, updatedCourse));

        mainViewModel.setSelectedCourse(updatedCourse);

        navController.popBackStack();
    }

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().setTitle(getString(R.string.editCourseFragmentTitle, course.getName()));
    }
}