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
import com.yepdevelopment.failure.Database.Entities.Submittable;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.Android.Parsing;
import com.yepdevelopment.failure.Utils.JavaRX.Async;
import com.yepdevelopment.failure.Validators.AddSubmittableValidator;
import com.yepdevelopment.failure.Validators.CommonValidator;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentEditSubmittableBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditSubmittableFragment extends Fragment {
    MainViewModel mainViewModel;
    NavController navController;
    AppDatabase database;
    Submittable submittable;
    private FragmentEditSubmittableBinding binding;

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
        binding = FragmentEditSubmittableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.editSubmittableForm.editTextSubmittableName.setText(submittable.getName());
        binding.editSubmittableForm.editTextSubmittableDescription.setText(submittable.getDescription());
        binding.editSubmittableForm.editTextSubmittableAssignDate.setText(submittable.getAssignDate());
        binding.editSubmittableForm.editTextSubmittableDueDate.setText(submittable.getDueDate());
        binding.editSubmittableForm.editTextSubmittableWeight.setText(String.valueOf(submittable.getWeight()));
        binding.editSubmittableForm.editTextSubmittableMaxGrade.setText(String.valueOf(submittable.getMaxGrade()));

        binding.editSubmittableButtonLayout.simpleButtonPositiveAction.setText(R.string.save);
        binding.editSubmittableButtonLayout.simpleButtonNegativeAction.setText(R.string.cancel);

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.CANADA);

        binding.editSubmittableForm.editTextLayoutSubmittableAssignDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editSubmittableForm.editTextSubmittableAssignDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        binding.editSubmittableForm.editTextLayoutSubmittableDueDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            binding.editSubmittableForm.editTextSubmittableDueDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        binding.editSubmittableButtonLayout.simpleButtonPositiveAction.setOnClickListener(this::editSubmittable);
        binding.editSubmittableButtonLayout.simpleButtonNegativeAction.setOnClickListener(button -> navController.popBackStack());
    }

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().setTitle(getString(R.string.editSubmittableFragmentTitle, submittable.getName()));
    }

    public void clearFieldErrors() {
        binding.editSubmittableForm.editTextLayoutSubmittableName.setError(null);
        binding.editSubmittableForm.editTextLayoutSubmittableAssignDate.setError(null);
        binding.editSubmittableForm.editTextLayoutSubmittableDueDate.setError(null);
        binding.editSubmittableForm.editTextLayoutSubmittableWeight.setError(null);
    }

    public void editSubmittable(View button) {
        clearFieldErrors();

        String submittableName = Parsing.editableToString(binding.editSubmittableForm.editTextSubmittableName.getText());
        String submittableDescription = Parsing.editableToString(binding.editSubmittableForm.editTextSubmittableDescription.getText());
        String submittableAssignDate = Parsing.editableToString(binding.editSubmittableForm.editTextSubmittableAssignDate.getText());
        String submittableDueDate = Parsing.editableToString(binding.editSubmittableForm.editTextSubmittableDueDate.getText());
        float submittableWeight = Parsing.editableToFloat(binding.editSubmittableForm.editTextSubmittableWeight.getText());
        float submittableMaxGrade = Parsing.editableToFloat(binding.editSubmittableForm.editTextSubmittableMaxGrade.getText());

        boolean hasError = false;

        if (!AddSubmittableValidator.isSubmittableNameValid(submittableName)) {
            binding.editSubmittableForm.editTextLayoutSubmittableName.setError(getString(R.string.editTextSubmittableName_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(submittableAssignDate)) {
            binding.editSubmittableForm.editTextLayoutSubmittableAssignDate.setError(getString(R.string.editTextSubmittableAssignDate_errorHint));
            hasError = true;
        }
        if (!CommonValidator.isDateValid(submittableDueDate)) {
            binding.editSubmittableForm.editTextLayoutSubmittableDueDate.setError(getString(R.string.editTextSubmittableDueDate_errorHint));
            hasError = true;
        }
        if (!AddSubmittableValidator.isWeightValid(submittableWeight)) {
            binding.editSubmittableForm.editTextLayoutSubmittableWeight.setError(getString(R.string.editTextSubmittableWeight_errorHint));
            hasError = true;
        }

        if (hasError) return;

        Submittable updatedSubmittable = new Submittable(submittable.getId(), submittableName, submittableDescription, submittableAssignDate, submittableDueDate, submittable.getAssociatedCourseId(), submittableWeight, submittableMaxGrade);

        Async.run(database.submittableDao().update(submittable, updatedSubmittable));

        mainViewModel.setSelectedSubmittable(updatedSubmittable);

        navController.popBackStack();
    }
}