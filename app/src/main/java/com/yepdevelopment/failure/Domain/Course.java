package com.yepdevelopment.failure.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public class Course {

    /**
     * @generated
     */
    private String name;

    /**
     * @generated
     */
    private String subject;

    /**
     * @generated
     */
    private String startDate;

    /**
     * @generated
     */
    private String endDate;

    /**
     * @generated
     */
    private float minimumGrade;


    /**
     * @generated
     */
    private List<Submittable> submittable;


    /**
     * @generated
     */
    private String getName() {
        return this.name;
    }

    /**
     * @generated
     */
    private void setName(String name) {
        this.name = name;
    }


    /**
     * @generated
     */
    private String getSubject() {
        return this.subject;
    }

    /**
     * @generated
     */
    private void setSubject(String subject) {
        this.subject = subject;
    }


    /**
     * @generated
     */
    private String getStartDate() {
        return this.startDate;
    }

    /**
     * @generated
     */
    private void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    /**
     * @generated
     */
    private String getEndDate() {
        return this.endDate;
    }

    /**
     * @generated
     */
    private void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    /**
     * @generated
     */
    private float getMinimumGrade() {
        return this.minimumGrade;
    }

    /**
     * @generated
     */
    private void setMinimumGrade(float minimumGrade) {
        this.minimumGrade = minimumGrade;
    }


    /**
     * @generated
     */
    public List<Submittable> getSubmittable() {
        if (this.submittable == null) {
            this.submittable = new ArrayList<Submittable>();
        }
        return this.submittable;
    }

    /**
     * @generated
     */
    public void setSubmittables(List<Submittable> submittable) {
        this.submittable = submittable;
    }


    //                          Operations                                  

    /**
     * @generated
     */
    public float calculateGrade() {
        // TODO implement calculateGrade()
        return -1.0f;
    }


}
