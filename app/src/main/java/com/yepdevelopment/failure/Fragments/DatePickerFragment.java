package com.yepdevelopment.failure.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import kotlin.jvm.functions.Function4;

/**
 * Sourced from <a href="https://developer.android.com/develop/ui/views/components/pickers#DatePicker">Android Developers</a>
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    Function4<DatePicker, Integer, Integer, Integer, Void> callback;

    public DatePickerFragment(Function4<DatePicker, Integer, Integer, Integer, Void> onDateSetHandler) {
        super();
        if (onDateSetHandler != null) {
            callback = onDateSetHandler;
        }
        else {
            callback = (ignored0, ignored1, ignored2, ignored3) -> {
                Log.w(this.getClass().getName(), "");
                return null;
            };
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        callback.invoke(view, year, month, day);
    }
}