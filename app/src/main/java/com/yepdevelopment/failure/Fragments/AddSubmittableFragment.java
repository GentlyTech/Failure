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
import com.yepdevelopment.failure.Database.Entities.Submittable;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.Android.Parsing;
import com.yepdevelopment.failure.Utils.JavaRX.Async;
import com.yepdevelopment.failure.Validators.AddSubmittableValidator;
import com.yepdevelopment.failure.Validators.CommonValidator;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentAddSubmittableBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSubmittableFragment extends Fragment {
    AppDatabase database;
    NavController navController;
    MainViewModel mainViewModel;
    Course course;
    private FragmentAddSubmittableBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getInstance(requireContext());
        navController = NavHostFragment.findNavController(this);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        course = mainViewModel.getSelectedCourse().getValue();
        if (course == null) navController.popBackStack();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddSubmittableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.addSubmittableButtonLayout.simpleButtonPositiveAction.setText(R.string.create);
        binding.addSubmittableButtonLayout.simpleButtonNegativeAction.setText(R.string.cancel);

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.CANADA);

        binding.editTextLayoutSubmittableAssignDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editTextSubmittableAssignDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        binding.editTextLayoutSubmittableDueDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editTextSubmittableDueDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        binding.addSubmittableButtonLayout.simpleButtonPositiveAction.setOnClickListener(this::createSubmittable);
        binding.addSubmittableButtonLayout.simpleButtonNegativeAction.setOnClickListener(button -> navController.popBackStack());
    }

    public void clearFieldErrors() {
        binding.editTextLayoutSubmittableName.setError(null);
        binding.editTextLayoutSubmittableAssignDate.setError(null);
        binding.editTextLayoutSubmittableDueDate.setError(null);
        binding.editTextLayoutSubmittableWeight.setError(null);
    }

    public void createSubmittable(View button) {
        clearFieldErrors();

        String submittableName = Parsing.editableToString(binding.editTextSubmittableName.getText());
        String submittableDescription = Parsing.editableToString(binding.editTextSubmittableDescription.getText());
        String submittableAssignDate = Parsing.editableToString(binding.editTextSubmittableAssignDate.getText());
        String submittableDueDate = Parsing.editableToString(binding.editTextSubmittableDueDate.getText());
        float submittableWeight = Parsing.editableToFloat(binding.editTextSubmittableWeight.getText());
        float submittableMaxGrade = Parsing.editableToFloat(binding.editTextSubmittableMaxGrade.getText());

        boolean hasError = false;

        if (!AddSubmittableValidator.isSubmittableNameValid(submittableName)) {
            binding.editTextLayoutSubmittableName.setError(getString(R.string.editTextSubmittableName_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(submittableAssignDate)) {
            binding.editTextLayoutSubmittableAssignDate.setError(getString(R.string.editTextSubmittableAssignDate_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(submittableDueDate)) {
            binding.editTextLayoutSubmittableDueDate.setError(getString(R.string.editTextSubmittableDueDate_errorHint));
            hasError = true;
        }
        if (!AddSubmittableValidator.isWeightValid(submittableWeight)) {
            binding.editTextLayoutSubmittableWeight.setError(getString(R.string.editTextSubmittableWeight_errorHint));
            hasError = true;
        }

        if (hasError) return;

        Submittable submittable = new Submittable(submittableName, submittableDescription, submittableAssignDate, submittableDueDate, course.getId(), submittableWeight, submittableMaxGrade);

        Async.run(database.submittableDao().insertAll(submittable));

        navController.popBackStack();
    }
}