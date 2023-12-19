package com.yepdevelopment.failure.ViewModels.Activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yepdevelopment.failure.Database.Entities.Course;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Course>> courses = new MutableLiveData<>();

    public LiveData<List<Course>> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> newCourses) {
        this.courses.setValue(newCourses);
    }
}
