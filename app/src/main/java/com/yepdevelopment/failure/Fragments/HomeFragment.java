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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.failure.Adapters.CourseAdapter;
import com.yepdevelopment.failure.Database.AppDatabase;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.JavaRX.Async;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    NavController navController;
    MainViewModel mainViewModel;
    AppDatabase database;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        database = AppDatabase.getInstance(requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.general_options, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.generalOptionAbout) {
                    navController.navigate(CourseOverviewFragmentDirections.actionCourseOverviewFragmentToHomeFragment());
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner()); // FIXME does not remove menu from toolbar when deleting a course

        binding.recyclerViewCourseList.setLayoutManager(new LinearLayoutManager(requireContext()));

        database.courseDao().getAll().observe(getViewLifecycleOwner(), (courses -> {
            if (courses != null) {
                setCourseListVisibility(!courses.isEmpty());
                binding.recyclerViewCourseList.setAdapter(new CourseAdapter(requireContext(), courses, (course) -> {
                    mainViewModel.setSelectedCourse(course);
                    Log.i(HomeFragment.class.getName(), String.format("Selected course set to: %s", course.getName()));
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToCourseOverviewFragment());
                }));
                return;
            }
            setCourseListVisibility(false);
        }));

        binding.floatingActionButtonAddCourse.setOnClickListener(ignored -> navController.navigate(HomeFragmentDirections.actionHomeFragmentToAddCourseFragment()));
    }

    public void setCourseListVisibility(boolean visible) {
        if (visible) {
            binding.groupCourseListEmptyText.setVisibility(View.GONE);
            binding.recyclerViewCourseList.setVisibility(View.VISIBLE);
            return;
        }

        binding.groupCourseListEmptyText.setVisibility(View.VISIBLE);
        binding.recyclerViewCourseList.setVisibility(View.GONE);
    }

}