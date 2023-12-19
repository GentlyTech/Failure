package com.yepdevelopment.failure.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.Database.Entities.Submittable;

import java.util.List;

@Dao
public interface SubmittableDAO {
    @Query("SELECT * FROM submittable")
    LiveData<List<Submittable>> getAll();

    @Query("SELECT * FROM submittable WHERE associatedCourseId == :courseId")
    LiveData<List<Submittable>> getAllFromCourse(String courseId);

    @Insert
    void insertAll(Submittable... submittables);

    @Delete
    void delete(Submittable submittable);
}
