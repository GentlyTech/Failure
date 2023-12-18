package com.yepdevelopment.failure.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;


@Entity
public class Submittable {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String description;
    private String assignDate;
    private String dueDate;
    private String associatedCourseId;
    private float weight;
    private float maxGrade;
    private float achievedGrade;

    public Submittable(@NonNull String id, String name, String description, String assignDate, String dueDate, String associatedCourseId, float weight, float maxGrade, float achievedGrade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.associatedCourseId = associatedCourseId;
        this.weight = weight;
        this.maxGrade = maxGrade;
        this.achievedGrade = achievedGrade;
    }

    @Ignore
    public Submittable(String name, String description, String assignDate, String dueDate, float weight, float maxGrade, float achievedGrade) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.associatedCourseId = "";
        this.weight = weight;
        this.maxGrade = maxGrade;
        this.achievedGrade = achievedGrade;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getAssignDate() {
        return this.assignDate;
    }


    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }


    public String getDueDate() {
        return this.dueDate;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssociatedCourseId() {
        return this.associatedCourseId;
    }


    public void setAssociatedCourseId(String associatedCourseId) {
        this.associatedCourseId = associatedCourseId;
    }


    public float getWeight() {
        return this.weight;
    }


    public void setWeight(float weight) {
        this.weight = weight;
    }


    public float getMaxGrade() {
        return this.maxGrade;
    }


    public void setMaxGrade(float maxGrade) {
        this.maxGrade = maxGrade;
    }


    public float getAchievedGrade() {
        return this.achievedGrade;
    }


    public void setAchievedGrade(float achievedGrade) {
        this.achievedGrade = achievedGrade;
    }

}
