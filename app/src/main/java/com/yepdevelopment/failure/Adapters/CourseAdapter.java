package com.yepdevelopment.failure.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.ViewHolders.CourseViewHolder;
import com.yepdevelopment.failure.databinding.ComponentCourseCardBinding;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {
    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
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
        binding.textCourseCardCourseName.setText(course.getName());
        binding.textCourseCardCourseSubject.setText(course.getSubject());
        binding.textCourseCardCourseGrade.setText(String.format("%s%%", course.calculateGrade())); // TODO perhaps come up with a better way to format this string
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
