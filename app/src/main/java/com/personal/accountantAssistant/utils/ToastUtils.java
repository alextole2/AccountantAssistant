package com.personal.accountantAssistant.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void showLongText(final Context context,
                                    final String text) {
        Toast.makeText(context,
                text,
                Toast.LENGTH_LONG)
                .show();
    }

    public static void showLongText(final Context context,
                                    final int resId) {
        Toast.makeText(context,
                resId,
                Toast.LENGTH_LONG)
                .show();
    }

    public static void showShortText(final Context context,
                                    final String text) {
        Toast.makeText(context,
                text,
                Toast.LENGTH_SHORT)
                .show();
    }

    public static void showShortText(final Context context,
                                    final int resId) {
        Toast.makeText(context,
                resId,
                Toast.LENGTH_SHORT)
                .show();
    }
}
