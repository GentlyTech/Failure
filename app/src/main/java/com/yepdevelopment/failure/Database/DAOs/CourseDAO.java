package com.yepdevelopment.failure.Database.DAOs;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yepdevelopment.failure.Database.Entities.Course;

import java.util.List;

public interface CourseDAO {
    @Query("SELECT * FROM course")
    List<Course> getAll();

    @Insert
    void insertAll(Course... course);

    @Delete
    void delete(Course course);
}
