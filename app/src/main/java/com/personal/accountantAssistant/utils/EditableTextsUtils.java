package com.personal.accountantAssistant.utils;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.personal.accountantAssistant.MainActivity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.functions.Action;

public class EditableTextsUtils {

    static final String EMPTY_VALUE = "";
    static AtomicReference<String> editTextValue = new AtomicReference<>(EMPTY_VALUE);

    public static void initializeListeners(final Activity activity,
                                           final EditText editText,
                                           final Action actionWhenTextChange) {

        editTextValue = new AtomicReference<>(EMPTY_VALUE);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setEditTextValue(editText.getText().toString());
                ActionUtils.runAction(actionWhenTextChange);
            }

            @Override
            public void afterTextChanged(Editable s) {
                setEditTextValue(editText.getText().toString());
                ActionUtils.runAction(actionWhenTextChange);
            }
        });

        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT ||
                    actionId == EditorInfo.IME_ACTION_GO ||
                    actionId == EditorInfo.IME_ACTION_DONE) {
                hideSoftInputFromWindow(activity, editText);
            }
            return Boolean.TRUE;
        });

        editText.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                EditableTextsUtils.hideSoftInputFromWindow(view, editText);
            }
        });
    }

    public static void hideSoftInputFromWindow(final Activity activity,
                                               final EditText editText) {
        final View view = activity.getCurrentFocus();
        if (view != null) {
            hideSoftInputFromWindow(view, editText);
        }
    }

    public static void hideSoftInputFromWindow(final View view,
                                               final EditText editText) {
        if (view != null) {
            final InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            editText.clearFocus();
        }
    }

    private static void setEditTextValue(final String textValue) {
        editTextValue.set(textValue);
    }

    public static String getEditTextValue() {
        return editTextValue.get();
    }
}
