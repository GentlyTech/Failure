package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yepdevelopment.failure.Database.AppDatabase;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.ViewModels.Activities.MainViewModel;

public class HomeFragment extends Fragment {
    NavController navController;
    MainViewModel mainViewModel;
    AppDatabase database;

    Group groupCourseListEmptyText;
    RecyclerView recyclerViewCourseList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        database = AppDatabase.getInstance(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton floatingActionButtonAddCourse = view.findViewById(R.id.floatingActionButtonAddCourse);
        groupCourseListEmptyText = view.findViewById(R.id.groupCourseListEmptyText);
        recyclerViewCourseList = view.findViewById(R.id.recyclerViewCourseList);

        recyclerViewCourseList.setLayoutManager(new LinearLayoutManager(requireContext()));

        database.courseDao().getAll().observe(getViewLifecycleOwner(), (courses -> {
            if (courses != null) {
                setCourseListVisibility(!courses.isEmpty());
                //recyclerViewCourseList.setAdapter();
                return;
            }
            setCourseListVisibility(false);
        }));

        floatingActionButtonAddCourse.setOnClickListener(ignored -> navController.navigate(HomeFragmentDirections.actionHomeFragmentToAddCourseFragment()));
    }

    public void setCourseListVisibility(boolean visible) {
        if (visible) {
            groupCourseListEmptyText.setVisibility(View.GONE);
            recyclerViewCourseList.setVisibility(View.VISIBLE);
            return;
        }

        groupCourseListEmptyText.setVisibility(View.VISIBLE);
        recyclerViewCourseList.setVisibility(View.GONE);
    }

}