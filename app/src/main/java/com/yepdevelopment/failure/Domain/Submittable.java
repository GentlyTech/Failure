package com.yepdevelopment.failure.Domain;

/**
 * @generated
 */
public class Submittable {
    private String id;
    private String name;
    private String description;
    private String assignDate;
    private String dueDate;
    private float weight;
    private float maxGrade;
    private float achievedGrade;

    /**
     * The course that is associated with this Submittable.
     */
    private Course course;

    public Submittable() {

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


    public Course getCourse() {
        return this.course;
    }


    public void setCourse(Course course) {
        this.course = course;
    }


}
