package com.personal.accountantAssistant.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.personal.accountantAssistant.R;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class BarcodeScanUtils {
    public static String scanFrom(final Context context) {
        String result = Constants.EMPTY_STR;
        final BarcodeDetector detector = new BarcodeDetector.Builder(context)
                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.CODABAR | Barcode.QR_CODE)
                .build();
        if (!detector.isOperational()) {
            ToastUtils.showShortText(context, R.string.fail_bar_code_detector);
        } else {
 /*           final SparseArray<Barcode> barCodes = detector.detect(new Frame());
            final Barcode thisCode = barCodes.valueAt(0);
            result = thisCode.rawValue;*/
        }
        return result;
    }
}
