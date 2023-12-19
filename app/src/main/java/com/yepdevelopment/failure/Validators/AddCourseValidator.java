package com.yepdevelopment.failure.Validators;

import static com.yepdevelopment.failure.Globals.DATE_FORMAT;

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
}
