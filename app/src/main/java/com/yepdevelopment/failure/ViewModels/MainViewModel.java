package com.yepdevelopment.failure.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<String> toolbarTitle = new MutableLiveData<>();

    public LiveData<String> getToolbarTitle() {
        return this.toolbarTitle;
    }

    public void setToolbarTitle(String newTitle) {
        toolbarTitle.setValue(newTitle);
    }
}
