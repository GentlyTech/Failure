package com.yepdevelopment.failure.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
    @Ignore
    private List<Submittable> submittables;

    /**
     * Create a Course object, with the given ID.
     * <p/>
     * Use this if you are reconstructing an existing Course.
     */
    public Course(@NonNull String id, String name, String subject, String startDate, String endDate, float minimumGrade) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minimumGrade = minimumGrade;
    }

    /**
     * Create a Course object with an autogenerated ID.
     */
    @Ignore
    public Course(String name, String subject, String startDate, String endDate, float minimumGrade) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minimumGrade = minimumGrade;
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
