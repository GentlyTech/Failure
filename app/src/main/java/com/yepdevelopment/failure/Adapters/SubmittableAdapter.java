package com.yepdevelopment.failure.Adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.Database.Entities.Submittable;
import com.yepdevelopment.failure.ViewHolders.GenericViewHolder;
import com.yepdevelopment.failure.databinding.ComponentCourseCardBinding;
import com.yepdevelopment.failure.databinding.ComponentSubmittableCardBinding;

import java.util.ArrayList;
import java.util.List;

public class SubmittableAdapter extends RecyclerView.Adapter<GenericViewHolder<ComponentSubmittableCardBinding>> {
    Context context;
    List<Submittable> submittables;

    public SubmittableAdapter(@NonNull Context context, List<Submittable> submittables) {
        this.context = context;

        if (submittables == null) {
            this.submittables = new ArrayList<>(0);
        }
        else {
            this.submittables = submittables;
        }
    }

    @NonNull
    @Override
    public GenericViewHolder<ComponentSubmittableCardBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentSubmittableCardBinding> holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
