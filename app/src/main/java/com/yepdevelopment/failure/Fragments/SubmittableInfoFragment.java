package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.yepdevelopment.failure.Database.AppDatabase;
import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.Database.Entities.Submittable;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.Android.TextChangedListeners.AfterTextChangedListener;
import com.yepdevelopment.failure.Utils.JavaRX.Async;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentSubmittableInfoBinding;

public class SubmittableInfoFragment extends Fragment {
    MainViewModel mainViewModel;
    Course course;
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
        course = mainViewModel.getSelectedCourse().getValue();
        if (submittable == null || course == null) navController.popBackStack();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubmittableInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainViewModel.getSelectedSubmittable().observe(getViewLifecycleOwner(), updatedSubmittable -> {
            if (updatedSubmittable == null) return;
            submittable = updatedSubmittable;
            setValues(false);
        });

        setValues(true);

        binding.editTextAchievedGrade.addTextChangedListener(new AfterTextChangedListener(newRawValue -> {
            try {
                float newValue = Float.parseFloat(newRawValue.toString());
                if (newValue == submittable.getAchievedGrade()) return;

                Submittable updatedSubmittable = submittable.clone();
                updatedSubmittable.setAchievedGrade(newValue);
                Async.run(database.submittableDao().update(submittable, updatedSubmittable));
                mainViewModel.setSelectedSubmittable(updatedSubmittable);
            } catch (NumberFormatException ex) {
                Log.w(SubmittableInfoFragment.class.getName(), "Unable to cast editTextAchievedGrade's value to float");
            }
        }));

        binding.checkBoxSubmittableComplete.setOnCheckedChangeListener((ignored, newState) -> {
            Submittable updatedSubmittable = submittable.clone();
            updatedSubmittable.setCompletionState(newState);
            Async.run(database.submittableDao().update(submittable, updatedSubmittable));
            mainViewModel.setSelectedSubmittable(updatedSubmittable);
        });

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.submittable_options, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.submittableOptionDelete) {
                    Async.run(database.submittableDao().delete(submittable));
                    mainViewModel.setSelectedSubmittable(null);
                    navController.popBackStack();
                    return true;
                } else if (menuItem.getItemId() == R.id.submittableOptionEdit) {
                    navController.navigate(SubmittableInfoFragmentDirections.actionSubmittableInfoFragmentToEditSubmittableFragment());
                }
                return false;
            }
        }, getViewLifecycleOwner());
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

    public void setValues(boolean updateFields) {
        binding.submittableInfoDash.entityDashTitle.setText(submittable.getName());
        binding.submittableInfoDash.entityDashDate.setText(getString(R.string.dateInterval, submittable.getAssignDate(), submittable.getDueDate()));
        binding.submittableInfoDash.entityDashBigNumber.setText(String.format("%s%%", submittable.calculateMinimumGrade(course.getMinimumGrade())));
        binding.submittableInfoDash.entityDashBigNumberCaption.setText(getString(R.string.textSubmittableInfoWeight_text, String.valueOf(submittable.getWeight())));
        binding.checkBoxSubmittableComplete.setChecked(submittable.isComplete());

        if (updateFields) {
            binding.editTextAchievedGrade.setText(String.valueOf(submittable.getAchievedGrade()));
        }

        String description = submittable.getDescription();
        if (description.isEmpty()) {
            binding.submittableInfoDash.entityDashBody.setVisibility(View.GONE);
        } else {
            binding.submittableInfoDash.entityDashBody.setVisibility(View.VISIBLE);
            binding.submittableInfoDash.entityDashBody.setText(description);
        }
    }
}