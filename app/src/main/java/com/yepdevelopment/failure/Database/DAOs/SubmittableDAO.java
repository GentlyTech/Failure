package com.yepdevelopment.failure.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yepdevelopment.failure.Database.Entities.Submittable;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface SubmittableDAO {
    @Query("SELECT * FROM submittable")
    LiveData<List<Submittable>> getAll();

    @Query("SELECT * FROM submittable WHERE associatedCourseId == :courseId")
    LiveData<List<Submittable>> getAllFromCourse(String courseId);

    @Insert
    Completable insertAll(Submittable... submittables);

    @Update
    Completable update(Submittable oldSubmittable, Submittable updatedSubmittable);

    @Delete
    Completable delete(Submittable submittable); // FIXME figure out why deletion won't work
}
