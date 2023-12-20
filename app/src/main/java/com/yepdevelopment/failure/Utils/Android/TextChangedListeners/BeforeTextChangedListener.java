package com.yepdevelopment.failure.Utils.Android.TextChangedListeners;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.function.Consumer;

/**
 * A simplified implementation of a <code>TextWatcher</code> that accepts a callback that will only be called when <code>beforeTextChanged()</code> is triggered.
 */
public class BeforeTextChangedListener implements TextWatcher {
    Consumer<CharSequence> callback;

    public BeforeTextChangedListener(Consumer<CharSequence> callback) {
        this.callback = callback;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (this.callback != null) this.callback.accept(charSequence);

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // ignored
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // ignored
    }
}
