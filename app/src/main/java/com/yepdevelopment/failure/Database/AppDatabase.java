package com.yepdevelopment.failure.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.yepdevelopment.failure.Database.Converters.CourseConverter;
import com.yepdevelopment.failure.Database.DAOs.CourseDAO;
import com.yepdevelopment.failure.Database.DAOs.SubmittableDAO;
import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.Database.Entities.Submittable;

@Database(entities = { Course.class, Submittable.class }, version = 1)
@TypeConverters({ CourseConverter.class })
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract CourseDAO courseDao();
    public abstract SubmittableDAO submittableDao();

    public static AppDatabase getInstance(Context applicationContext) {
        if (instance == null) {
            instance = Room.databaseBuilder(applicationContext, AppDatabase.class, "FailureDB").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
