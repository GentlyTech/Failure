package com.yepdevelopment.failure.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Course {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String subject;
    private String startDate;
    private String endDate;
    private float minimumGrade;

    /**
     * The submittables this class contains.
     */
    private List<Submittable> submittables;

    public Course(@NonNull String id) {
        this.id = id;
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


    public String getSubject() {
        return this.subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getStartDate() {
        return this.startDate;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getEndDate() {
        return this.endDate;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public float getMinimumGrade() {
        return this.minimumGrade;
    }


    public void setMinimumGrade(float minimumGrade) {
        this.minimumGrade = minimumGrade;
    }


    public List<Submittable> getSubmittables() {
        if (this.submittables == null) {
            this.submittables = new ArrayList<Submittable>();
        }
        return this.submittables;
    }


    public void setSubmittables(List<Submittable> submittable) {
        this.submittables = submittable;
    }


    //                          Operations                                  


    public float calculateGrade() {
        // TODO implement calculateGrade()
        return -1.0f;
    }


}
