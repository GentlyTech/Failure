package com.yepdevelopment.failure.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.failure.Database.Entities.Contributor;
import com.yepdevelopment.failure.ViewHolders.GenericViewHolder;
import com.yepdevelopment.failure.databinding.ComponentContributorEntryBinding;

import java.util.ArrayList;
import java.util.List;

public class ContributorAdapter extends RecyclerView.Adapter<GenericViewHolder<ComponentContributorEntryBinding>> {
    Context context;
    List<Contributor> contributors;

    public ContributorAdapter(@NonNull Context context, List<Contributor> contributors) {
        this.context = context;
        if (contributors == null) {
            this.contributors = new ArrayList<>(0);
        } else {
            this.contributors = contributors;
        }
    }

    @NonNull
    @Override
    public GenericViewHolder<ComponentContributorEntryBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ComponentContributorEntryBinding binding = ComponentContributorEntryBinding.inflate(layoutInflater, parent, false);
        return new GenericViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentContributorEntryBinding> holder, int position) {
        Contributor contributor = contributors.get(position);

        ComponentContributorEntryBinding binding = holder.getBinding();
        binding.textContributorName.setText(contributor.getName());
        binding.textContributorRole.setText(contributor.getRole());

        if (!contributor.getImageUri().isEmpty()) {
            binding.imageContributorPhoto.setImageURI(Uri.parse(contributor.getImageUri()));
        }
    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }
}
