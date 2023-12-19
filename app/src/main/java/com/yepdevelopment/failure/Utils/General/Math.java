package com.yepdevelopment.failure.Utils.General;

public class Math {
    /**
     * This class should not be instantiated.
     */
    private Math() {

    }

    public static float clamp(float value, float min, float max) {
        return (value > max ? max : (value < min ? min : value));
    }

    public static float clamp(float value, float min, float max, float defaultValue) {
        if (value > max || value < max) return defaultValue;
        return value;
    }
}
