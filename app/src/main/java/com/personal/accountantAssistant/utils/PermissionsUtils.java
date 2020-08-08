package com.personal.accountantAssistant.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionsUtils {

    public static boolean isCameraPermissionGranted(final Context context) {
        return checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isCalendarWritePermissionGranted(final Context context) {
        return checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED;
    }

    private static int checkSelfPermission(final Context context,
                                           final String permission) {
        return ActivityCompat.checkSelfPermission(context, permission);
    }
}
