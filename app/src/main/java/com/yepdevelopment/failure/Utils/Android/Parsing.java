package com.yepdevelopment.failure.Utils.Android;

import android.text.Editable;

public class Parsing {
    /**
     * This class should not be instantiated.
     */
    private Parsing() {

    }

    /**
     * Returns the String equivalent of the given Editable, or a blank String if the Editable is null.
     *
     * @param editable the Editable to convert to a String
     * @return the String equivalent of the given Editable, or a blank String if the Editable is null
     */
    public static String editableToString(Editable editable) {
        if (editable == null) return "";
        return editable.toString();
    }

    /**
     * Returns the float equivalent of the given Editable, or -1.0f if the Editable is null or the Editable is not a float.
     *
     * @param editable the Editable to convert to a float
     * @return the float equivalent of the given Editable, or -1.0f if the Editable is null or not a float
     */
    public static float editableToFloat(Editable editable) {
        if (editable == null) return -1.0f;
        try {
            return Float.parseFloat(editable.toString());
        } catch (NumberFormatException ignored) {
            return -1.0f;
        }
    }
}
