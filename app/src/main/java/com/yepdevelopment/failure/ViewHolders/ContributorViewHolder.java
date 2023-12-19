package com.yepdevelopment.failure.ViewHolders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.databinding.ComponentContributorEntryBinding;

public class ContributorViewHolder extends RecyclerView.ViewHolder {
    private final ComponentContributorEntryBinding binding;

    public ContributorViewHolder(@NonNull ComponentContributorEntryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ComponentContributorEntryBinding getBinding() {
        return binding;
    }
}
