package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;
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
        binding.submittableInfoDash.entityDashTitle.setText(submittable.getName());
        binding.submittableInfoDash.entityDashDate.setText(getString(R.string.dateInterval, submittable.getAssignDate(), submittable.getDueDate()));
        binding.submittableInfoDash.entityDashBigNumber.setText(String.format("%s%%", submittable.calculateGrade()));
        binding.submittableInfoDash.entityDashBigNumberCaption.setText(getString(R.string.textSubmittableInfoWeight_text, String.valueOf(submittable.getWeight())));

        String description = submittable.getDescription();
        if (description.isEmpty()) {
            binding.submittableInfoDash.entityDashBody.setVisibility(View.GONE);
        } else {
            binding.submittableInfoDash.entityDashBody.setVisibility(View.VISIBLE);
            binding.submittableInfoDash.entityDashBody.setText(description);
        }

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.submittable_options, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.submittableOptionDelete) {
                    database.submittableDao().delete(submittable);
                    mainViewModel.setSelectedSubmittable(null);
                    navController.popBackStack();
                    return true;
                }
                else if (menuItem.getItemId() == R.id.submittableOptionEdit) {
                    // navController.navigate(SubmittableInfoFragmentDirections.); // TODO implement submittable editing
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
}