package com.yepdevelopment.failure.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;


@Entity
public class Submittable implements Cloneable {
    boolean complete;
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

    /**
     * Full constructor for Submittable.
     *
     * @param id
     * @param name
     * @param description
     * @param assignDate
     * @param dueDate
     * @param associatedCourseId
     * @param weight
     * @param maxGrade
     * @param achievedGrade
     * @param complete
     */
    public Submittable(@NonNull String id, String name, String description, String assignDate, String dueDate, String associatedCourseId, float weight, float maxGrade, float achievedGrade, boolean complete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.associatedCourseId = associatedCourseId;
        this.weight = com.yepdevelopment.failure.Utils.General.Math.clamp(weight, 0.0f, 100.0f, 10.0f);
        this.maxGrade = com.yepdevelopment.failure.Utils.General.Math.clamp(maxGrade, 0.0f, 100.0f, 100.0f);
        this.achievedGrade = com.yepdevelopment.failure.Utils.General.Math.clamp(achievedGrade, 0.0f, 100.0f, 0.0f);
        this.complete = complete;
    }

    /**
     * Optional constructor for Submittable.
     *
     * @param name
     * @param description
     * @param assignDate
     * @param dueDate
     * @param associatedCourseId
     * @param weight
     * @param maxGrade
     */
    @Ignore
    public Submittable(String name, String description, String assignDate, String dueDate, String associatedCourseId, float weight, float maxGrade) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.associatedCourseId = associatedCourseId;
        this.weight = com.yepdevelopment.failure.Utils.General.Math.clamp(weight, 0.0f, 100.0f, 10.0f);
        this.maxGrade = com.yepdevelopment.failure.Utils.General.Math.clamp(maxGrade, 0.0f, 100.0f, 100.0f);
        this.achievedGrade = 0.0f;
        this.complete = false;
    }

    @Ignore
    public Submittable() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.assignDate = "";
        this.dueDate = "";
        this.associatedCourseId = "";
        this.weight = 10.0f;
        this.maxGrade = 100.0f;
        this.achievedGrade = 0.0f;
        this.complete = false;
    }

    @NonNull
    @Override
    public Submittable clone() {
        try {
            Submittable clone = (Submittable) super.clone();
            return new Submittable(clone.getId(), clone.name, clone.description, clone.assignDate, clone.dueDate, clone.getAssociatedCourseId(), clone.weight, clone.maxGrade, clone.achievedGrade, clone.complete);
        } catch (CloneNotSupportedException ignored) {
            return new Submittable();
        }
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    public void setId(@NonNull String id) {
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

    public void setCompletionState(boolean newState) {
        this.complete = newState;
    }

    public boolean isComplete() {
        return this.complete;
    }

    /**
     * Calculates the minimum grade that this submittable needs to contribute to be able to pass the course/reach the desired grade.
     * <p/>
     * Each submittable assumes that the sum of all admissible submittables add up to the minimum/desired grade. This means that the weights of all submittables must also add up to 100.
     *
     * @param minimumGrade the minimum/desired grade in the course to base the calculation off of. Assumed to be a number between 0.0f and 100.0f rather than 0.0f to 1.0f.
     * @return the grade (not a percentage) that you need to get on this submittable to play its part in passing the course/reaching the desired grade.
     */
    public float calculateMinimumGrade(float minimumGrade) {
        float targetWeight = (weight * minimumGrade) / 100;
        return (targetWeight * maxGrade) / weight;
    }

    public float calculateFinalGrade() {
        return (achievedGrade * weight) / maxGrade;
    }

}
