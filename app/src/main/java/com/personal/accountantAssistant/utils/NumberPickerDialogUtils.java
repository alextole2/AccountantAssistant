package com.personal.accountantAssistant.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.personal.accountantAssistant.R;

public class NumberPickerDialogUtils {

    static final int MIN_VALUE = 1;
    static final int MAX_VALUE = 100;

    public static void initializeFrom(final Context context,
                                      final EditText editText,
                                      final int defaultValue) {
        setText(editText, defaultValue);
        editText.setOnClickListener(view -> showNumberPickerDialogFrom(context, editText, defaultValue));
        editText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                showNumberPickerDialogFrom(context, editText, defaultValue);
            }
        });
    }

    static void setText(final EditText editText,
                        final int texValue) {
        editText.setText(String.valueOf((texValue == 0) ?
                NumberPickerDialogUtils.MIN_VALUE :
                texValue));
    }

    static void showNumberPickerDialogFrom(final Context context,
                                           final EditText editText,
                                           final int defaultValue) {
        final NumberPicker numberPicker = new NumberPicker(context);
        numberPicker.setMinValue(NumberPickerDialogUtils.MIN_VALUE);
        numberPicker.setMaxValue(NumberPickerDialogUtils.MAX_VALUE);
        numberPicker.setValue((defaultValue == 0) ? NumberPickerDialogUtils.MIN_VALUE : defaultValue);
        numberPicker.setOnValueChangedListener((selector, oldValue, newValue) -> NumberPickerDialogUtils.setText(editText, newValue));
        new AlertDialog.Builder(context)
                .setView(numberPicker)
                .setTitle(R.string.select_quantity)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                })
                .show();
    }
}
