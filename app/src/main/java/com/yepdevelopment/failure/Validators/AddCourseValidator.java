package com.yepdevelopment.failure.Validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddCourseValidator {
    /**
     * This class should not be instantiated.
     */
    private AddCourseValidator() {

    }

    public static boolean isCourseNameValid(String courseName) {
        return courseName != null && !courseName.isEmpty();
    }

    public static boolean isDateValid(String date) {
        if (date == null) return false;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd", Locale.CANADA);
        dateFormatter.setLenient(false);

        try {
            dateFormatter.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
