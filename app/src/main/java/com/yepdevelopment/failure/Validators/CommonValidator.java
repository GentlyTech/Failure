package com.yepdevelopment.failure.Validators;

import static com.yepdevelopment.failure.Globals.DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CommonValidator {
    /**
     * This class should not be instantiated.
     */
    private CommonValidator() {

    }

    public static boolean isDateValid(String date) {
        if (date == null) return false;

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.CANADA);
        dateFormatter.setLenient(false);

        try {
            dateFormatter.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
