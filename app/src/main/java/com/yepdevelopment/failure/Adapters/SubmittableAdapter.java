package com.yepdevelopment.failure.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.Database.Entities.Submittable;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.ViewHolders.GenericViewHolder;
import com.yepdevelopment.failure.databinding.ComponentSubmittableCardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SubmittableAdapter extends RecyclerView.Adapter<GenericViewHolder<ComponentSubmittableCardBinding>> {
    Context context;
    List<Submittable> submittables;
    Consumer<Submittable> onClickHandler;

    public SubmittableAdapter(@NonNull Context context, List<Submittable> submittables, Consumer<Submittable> onClickHandler) {
        this.context = context;

        if (submittables == null) {
            this.submittables = new ArrayList<>(0);
        } else {
            this.submittables = submittables;
        }

        if (onClickHandler == null) {
            this.onClickHandler = (ignored) -> Log.w(SubmittableAdapter.class.getName(), "No onClickHandler was provided");
        } else {
            this.onClickHandler = onClickHandler;
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
        binding.submittableCard.setOnClickListener((ignored) -> this.onClickHandler.accept(submittable)); // FIXME this is probably wrong
        binding.textSubmittableCardName.setText(submittable.getName());
        binding.textSubmittableCardDate.setText(context.getString(R.string.dueDate, submittable.getDueDate()));
        binding.textSubmittableCardMinimumGrade.setText(String.format("%s%%", submittable.calculateGrade()));

        if (submittable.isComplete()) {
            binding.imageSubmittableCardComplete.setVisibility(View.VISIBLE);
        } else {
            binding.imageSubmittableCardComplete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return submittables.size();
    }
}
