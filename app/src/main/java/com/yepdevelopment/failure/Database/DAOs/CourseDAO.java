package com.yepdevelopment.failure.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yepdevelopment.failure.Database.Entities.Course;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAll();

    @Insert
    Completable insertAll(Course... course);

    @Update
    Completable update(Course oldCourse, Course updatedCourse);

    @Delete
    Completable delete(Course course);
}
