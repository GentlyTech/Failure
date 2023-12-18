package com.yepdevelopment.failure.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.ViewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        toolbar = findViewById(R.id.toolbarMainActivity);

        mainViewModel.getToolbarTitle().observe(this, newTitle -> {
            if (newTitle != null) toolbar.setTitle(newTitle);
        });
    }

}