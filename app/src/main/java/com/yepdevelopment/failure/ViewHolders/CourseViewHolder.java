package com.yepdevelopment.failure.ViewHolders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.databinding.ComponentCourseCardBinding;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final ComponentCourseCardBinding binding;

    public CourseViewHolder(@NonNull ComponentCourseCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ComponentCourseCardBinding getBinding() {
        return binding;
    }
}
