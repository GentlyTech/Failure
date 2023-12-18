package com.yepdevelopment.failure.Database.Entities;

import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Course {
    private String id;
    private String name;
    private String subject;
    private String startDate;
    private String endDate;
    private float minimumGrade;

    /**
     * The submittables this class contains.
     */
    private List<Submittable> submittable;

    public Course() {

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


    public List<Submittable> getSubmittable() {
        if (this.submittable == null) {
            this.submittable = new ArrayList<Submittable>();
        }
        return this.submittable;
    }


    public void setSubmittables(List<Submittable> submittable) {
        this.submittable = submittable;
    }


    //                          Operations                                  


    public float calculateGrade() {
        // TODO implement calculateGrade()
        return -1.0f;
    }


}
