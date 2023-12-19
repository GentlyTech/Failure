package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.failure.Adapters.ContributorAdapter;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.Utils.Android.ResourceManipulator;
import com.yepdevelopment.failure.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {
    private FragmentAboutBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerViewContributors.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewContributors.setAdapter(new ContributorAdapter(requireContext(), ResourceManipulator.getContributors(requireContext())));
    }

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().setTitle(getString(R.string.aboutFragmentTitle, getString(R.string.appName)));
    }
}