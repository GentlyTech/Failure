package com.yepdevelopment.failure.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yepdevelopment.failure.Database.Entities.Course;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAll();

    @Insert
    Completable insertAll(Course... course);

    @Delete
    Completable delete(Course course);
}
