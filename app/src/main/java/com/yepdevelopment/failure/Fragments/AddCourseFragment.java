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

import com.yepdevelopment.failure.BuildConfig;
import com.yepdevelopment.failure.Database.AppDatabase;
import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.Android.Parsing;
import com.yepdevelopment.failure.Utils.JavaRX.Async;
import com.yepdevelopment.failure.Validators.AddCourseValidator;
import com.yepdevelopment.failure.Validators.CommonValidator;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentAddCourseBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddCourseFragment extends Fragment {
    MainViewModel mainViewModel;
    NavController navController;
    AppDatabase database;
    private FragmentAddCourseBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = NavHostFragment.findNavController(this);
        database = AppDatabase.getInstance(requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.addCourseFormButtonLayout.simpleButtonPositiveAction.setText(R.string.create);
        binding.addCourseFormButtonLayout.simpleButtonNegativeAction.setText(R.string.cancel);

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.CANADA); // TODO maybe make locale dynamic

        binding.editTextLayoutCourseStartDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editTextCourseStartDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        binding.editTextLayoutCourseEndDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editTextCourseEndDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        if (BuildConfig.DEBUG) {
            binding.groupAddCourseDebugItems.setVisibility(View.VISIBLE);
        } else {
            binding.groupAddCourseDebugItems.setVisibility(View.GONE);
        }

        binding.buttonDebugAddCourse.setOnClickListener(this::createSampleCourse);
        binding.addCourseFormButtonLayout.simpleButtonPositiveAction.setOnClickListener(this::createCourse);
        binding.addCourseFormButtonLayout.simpleButtonNegativeAction.setOnClickListener(button -> navController.popBackStack());
    }

    public void clearFieldErrors() {
        binding.editTextLayoutCourseName.setError(null);
        binding.editTextLayoutCourseStartDate.setError(null);
        binding.editTextLayoutCourseEndDate.setError(null);
    }

    public void createCourse(View button) {
        clearFieldErrors();

        String courseName = Parsing.editableToString(binding.editTextCourseName.getText());
        String courseSubject = Parsing.editableToString(binding.editTextCourseSubject.getText());
        String courseStartDate = Parsing.editableToString(binding.editTextCourseStartDate.getText());
        String courseEndDate = Parsing.editableToString(binding.editTextCourseEndDate.getText());
        float courseMinimumGrade = Parsing.editableToFloat(binding.editTextCourseMinimumGrade.getText());

        boolean hasError = false;

        if (!AddCourseValidator.isCourseNameValid(courseName)) {
            binding.editTextLayoutCourseName.setError(getString(R.string.editTextCourseName_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(courseStartDate)) {
            binding.editTextLayoutCourseStartDate.setError(getString(R.string.editTextCourseStartDate_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(courseEndDate)) {
            binding.editTextLayoutCourseEndDate.setError(getString(R.string.editTextCourseEndDate_errorHint));
            hasError = true;
        }

        if (hasError) return;

        Course course = new Course(courseName, courseSubject, courseStartDate, courseEndDate, courseMinimumGrade);

        Async.run(database.courseDao().insertAll(course));

        navController.popBackStack();
    }

    public void createSampleCourse(View button) {
        Course sampleCourse = Course.generateRandom();
        Async.run(database.courseDao().insertAll(sampleCourse));
        navController.popBackStack();
    }
}