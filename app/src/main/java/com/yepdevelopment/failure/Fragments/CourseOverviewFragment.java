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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.failure.Adapters.SubmittableAdapter;
import com.yepdevelopment.failure.Database.AppDatabase;
import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.JavaRX.Async;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;
import com.yepdevelopment.failure.databinding.FragmentCourseOverviewBinding;

public class CourseOverviewFragment extends Fragment {
    MainViewModel mainViewModel;
    NavController navController;
    Course course;
    AppDatabase database;
    private FragmentCourseOverviewBinding binding;

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
        binding = FragmentCourseOverviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.includeCourseCardInOverview.textCourseCardCourseName.setText(course.getName());
        binding.includeCourseCardInOverview.textCourseCardCourseSubject.setText(course.getSubject());
        binding.includeCourseCardInOverview.imageCourseCardArrow.setVisibility(View.GONE);

        float calculatedGrade = course.calculateGrade();
        binding.includeCourseCardInOverview.textCourseCardCourseGrade.setText(String.format("%s%%", course.calculateGrade()));
        if (calculatedGrade < course.getMinimumGrade()) {
            binding.includeCourseCardInOverview.textCourseCardCourseGrade.setTextColor(requireContext().getColor(R.color.niceRed));
        }

        binding.floatingActionButtonAddSubmittable.setOnClickListener(ignored -> navController.navigate(CourseOverviewFragmentDirections.actionCourseOverviewFragmentToAddSubmittableFragment()));

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.course_options, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.courseOptionDelete) {
                    Async.run(database.courseDao().delete(course));
                    mainViewModel.setSelectedCourse(null);
                    navController.navigate(CourseOverviewFragmentDirections.actionCourseOverviewFragmentToHomeFragment());
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner()); // FIXME does not remove menu from toolbar when deleting a course

        binding.recyclerViewSubmittablesList.setLayoutManager(new LinearLayoutManager(requireContext()));
        database.submittableDao().getAllFromCourse(course.getId()).observe(getViewLifecycleOwner(), courses -> {
            binding.recyclerViewSubmittablesList.setAdapter(new SubmittableAdapter(requireContext(), courses));
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().setTitle(getString(R.string.courseOverviewFragmentTitle, course.getName()));
    }
}