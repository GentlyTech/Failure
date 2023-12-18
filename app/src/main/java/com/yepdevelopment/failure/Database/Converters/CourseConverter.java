package com.yepdevelopment.failure.Database.Converters;

import android.util.Log;

import androidx.room.TypeConverter;

import com.yepdevelopment.failure.Database.Entities.Course;

import org.json.JSONException;
import org.json.JSONObject;

public class CourseConverter {
    @TypeConverter
    public static JSONObject toJson(Course course) {
        return null;
    }

    @TypeConverter
    public static Course fromJson(JSONObject course) {
        return null;
    }

    @TypeConverter
    public static Course fromJson(String course) {
        try {
            return fromJson(new JSONObject(course));
        } catch (JSONException e) {
            Log.w(CourseConverter.class.getName(), e);
            return null;
        }
    }
}
