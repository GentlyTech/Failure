package com.yepdevelopment.failure.ViewHolders;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.databinding.ComponentCourseCardBinding;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final ComponentCourseCardBinding binding;

    public CourseViewHolder(ComponentCourseCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ComponentCourseCardBinding getBinding() {
        return binding;
    }
}
