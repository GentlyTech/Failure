package com.yepdevelopment.failure.Utils.Android;

import android.content.Context;
import android.util.TypedValue;

public class Conversions {
    /**
     * This class should not be instantiated.
     */
    private Conversions() {

    }

    /**
     * Converts a given integer to its DP (Device Independent Pixel) equivalent.
     *
     * @param context an application context so that the display metrics can be retrieved
     * @param value   the value to convert
     * @return the converted value in DP
     */
    public static int toDp(Context context, int value) {
        if (context == null) return -1;
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
