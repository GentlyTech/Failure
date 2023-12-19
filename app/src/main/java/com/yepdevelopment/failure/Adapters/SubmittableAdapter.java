package com.yepdevelopment.failure.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.Database.Entities.Submittable;
import com.yepdevelopment.failure.ViewHolders.GenericViewHolder;
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
        } else {
            this.submittables = submittables;
        }
    }

    @NonNull
    @Override
    public GenericViewHolder<ComponentSubmittableCardBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ComponentSubmittableCardBinding binding = ComponentSubmittableCardBinding.inflate(layoutInflater, parent, false);
        return new GenericViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentSubmittableCardBinding> holder, int position) {
        Submittable submittable = submittables.get(position);
        if (submittable == null) return;

        ComponentSubmittableCardBinding binding = holder.getBinding();
        binding.textSubmittableCardName.setText(submittable.getName());
        binding.textSubmittableCardDescription.setText(submittable.getDescription());
        binding.textSubmittableCardMinimumGrade.setText(String.format("%s%%", submittable.calculateGrade()));
    }

    @Override
    public int getItemCount() {
        return submittables.size();
    }
}
