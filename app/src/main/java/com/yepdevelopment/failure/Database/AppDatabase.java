package com.yepdevelopment.failure.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yepdevelopment.failure.Database.DAOs.CourseDAO;
import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.Database.Entities.Submittable;

@Database(entities = { Course.class, Submittable.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDAO courseDao();
}
