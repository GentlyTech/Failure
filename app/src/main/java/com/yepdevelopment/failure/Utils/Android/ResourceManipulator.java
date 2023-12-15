package com.yepdevelopment.failure.Utils.Android;

import android.content.Context;

/**
 * Util methods related to Android resources.
 */
public class ResourceManipulator {
    /**
     * This class should not be instantiated.
     */
    private ResourceManipulator() {

    }

    /**
     * Gets the current screen orientation of the device.
     *
     * @param context an Android Context object so that device configuration can be retrieved
     * @return one of ORIENTATION_LANDSCAPE, ORIENTATION_PORTRAIT, or -1 if context was null
     */
    public static int getOrientation(Context context) {
        if (context == null) return -1;
        return context.getResources().getConfiguration().orientation;
    }
}
