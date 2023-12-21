package com.yepdevelopment.failure.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.Android.ResourceManipulator;
import com.yepdevelopment.failure.ViewHolders.GenericViewHolder;
import com.yepdevelopment.failure.databinding.ComponentCourseCardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CourseAdapter extends RecyclerView.Adapter<GenericViewHolder<ComponentCourseCardBinding>> {
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
    public GenericViewHolder<ComponentCourseCardBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ComponentCourseCardBinding binding = ComponentCourseCardBinding.inflate(layoutInflater, parent, false);
        return new GenericViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentCourseCardBinding> holder, int position) {
        Course course = courses.get(position);
        if (course == null) return;

        ComponentCourseCardBinding binding = holder.getBinding();
        binding.courseCard.setOnClickListener((ignored) -> this.onClickHandler.accept(course)); // FIXME this is probably wrong
        binding.textCourseCardCourseName.setText(course.getName());
        binding.textCourseCardCourseSubject.setText(course.getSubject());

        float calculatedGrade = course.calculateGrade();
        binding.textCourseCardCourseGrade.setText(String.format("%s%%", calculatedGrade)); // TODO perhaps come up with a better way to format this string
        if (calculatedGrade < course.getMinimumGrade()) {
            binding.textCourseCardCourseGrade.setTextColor(context.getColor(R.color.niceRed));
        }
        else {
            binding.textCourseCardCourseGrade.setTextColor(context.getColor(R.color.niceGreen));
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
