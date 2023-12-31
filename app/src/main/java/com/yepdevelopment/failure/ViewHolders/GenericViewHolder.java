package com.yepdevelopment.failure.ViewHolders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class GenericViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {
    private final T binding;

    public GenericViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
