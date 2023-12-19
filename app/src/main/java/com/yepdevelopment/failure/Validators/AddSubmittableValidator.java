package com.yepdevelopment.failure.Validators;

public class AddSubmittableValidator {
    /**isEmpty
     * This class should not be instantiated.
     */
    private AddSubmittableValidator() {

    }

    public static boolean isSubmittableNameValid(String submittableName) {
        return submittableName != null && !submittableName.isEmpty();
    }

    public static boolean isWeightValid(float weight) {
        return weight >= 0.0f && weight <= 100.0f;
    }
}
