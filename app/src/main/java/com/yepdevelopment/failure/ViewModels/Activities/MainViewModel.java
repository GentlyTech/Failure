package com.yepdevelopment.failure.ViewModels.Activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.Database.Entities.Submittable;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Course> selectedCourse = new MutableLiveData<>();
    private final MutableLiveData<Submittable> selectedSubmittable = new MutableLiveData<>();

    public LiveData<Course> getSelectedCourse() {
        return this.selectedCourse;
    }

    public void setSelectedCourse(Course newCourse) {
        this.selectedCourse.setValue(newCourse);
    }

    public LiveData<Submittable> getSelectedSubmittable() {
        return this.selectedSubmittable;
    }

    public void setSelectedSubmittable(Submittable newSubmittable) {
        this.selectedSubmittable.setValue(newSubmittable);
    }
}
