package com.yepdevelopment.failure.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.ViewHolders.CourseViewHolder;
import com.yepdevelopment.failure.databinding.ComponentCourseCardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {
    Context context;
    List<Course> courses;
    Consumer<Course> onClickHandler;

    public CourseAdapter(@NonNull Context context, List<Course> courses, Consumer<Course> onClickHandler) {
        this.context = context;

        if (courses == null) {
            this.courses = new ArrayList<>(0);
        } else {
            this.courses = courses;
        }

        if (onClickHandler == null) {
            this.onClickHandler = (ignored) -> Log.w(CourseAdapter.class.getName(), "No onClickHandler was provided");
        } else {
            this.onClickHandler = onClickHandler;
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ComponentCourseCardBinding binding = ComponentCourseCardBinding.inflate(layoutInflater, parent, false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        if (course == null) return;

        ComponentCourseCardBinding binding = holder.getBinding();
        binding.courseCard.setOnClickListener((ignored) -> this.onClickHandler.accept(course)); // TODO this is probably wrong
        binding.textCourseCardCourseName.setText(course.getName());
        binding.textCourseCardCourseSubject.setText(course.getSubject());
        binding.textCourseCardCourseGrade.setText(String.format("%s%%", course.calculateGrade())); // TODO perhaps come up with a better way to format this string
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
