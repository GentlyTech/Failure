package com.yepdevelopment.failure.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.Database.Relationships.CourseWithSubmittables;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM course")
    LiveData<List<Course>> getCoursesLive();

    @Query("SELECT * FROM course")
    Single<List<Course>> getCourses();

    @Transaction
    @Query("SELECT * FROM course")
    LiveData<List<CourseWithSubmittables>> getCoursesWithSubmittablesLive();

    @Transaction
    @Query("SELECT * FROM course")
    Single<List<CourseWithSubmittables>> getCoursesWithSubmittables();

    @Transaction
    @Query("SELECT * FROM course WHERE id == :courseId")
    LiveData<List<CourseWithSubmittables>> getCourseWithSubmittablesLive(String courseId);

    @Transaction
    @Query("SELECT * FROM course WHERE id == :courseId")
    Single<List<CourseWithSubmittables>> getCourseWithSubmittables(String courseId);

    @Insert
    Completable insertAll(Course... course);

    @Update
    Completable update(Course oldCourse, Course updatedCourse);

    @Delete
    Completable delete(Course course);
}
