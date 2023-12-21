package com.yepdevelopment.failure.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.failure.Database.Entities.Course;
import com.yepdevelopment.failure.Database.Entities.Submittable;

import java.util.List;

public class CourseWithSubmittables {
    @Embedded
    public Course course;

    @Relation(
            parentColumn = "id",
            entityColumn = "associatedCourseId"
    )
    public List<Submittable> submittables;
}
