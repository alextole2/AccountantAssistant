package com.personal.accountantAssistant.utils;

import android.app.Activity;
import android.content.Context;

import com.personal.accountantAssistant.R;
import com.personal.accountantAssistant.db.DatabaseManager;
import com.personal.accountantAssistant.ui.payments.entities.Payments;

public class DataBaseUtils {

    public static boolean isNotDefault(final long idOrRecord) {
        return idOrRecord > Constants.DEFAULT_UID;
    }

    public static void saveDataFrom(final Activity activity,
                                    final Object entity) {

        long recordSaved = Constants.DEFAULT_UID;
        final Payments payment = ParserUtils.toPayments(entity);
        final Context context = activity.getApplicationContext();
        final DatabaseManager databaseManager = new DatabaseManager(context);

        if (!ParserUtils.isNullObject(payment)) {
            recordSaved = databaseManager.insertOrUpdatePayment(payment);
        }

        if (isNotDefault(recordSaved)) {
            CalendarsUtils.createCalendarEventFrom(context, payment);
            ToastUtils.showLongText(context, R.string.record_successfully_save);
            activity.finish();
        } else {
            CalendarsUtils.deleteCalendarEventsFrom(context, payment);
            ToastUtils.showLongText(context, R.string.error_saving_your_data);
        }
    }

    static void deleteDataFrom(final Context context,
                               final Object entity) {

        long recordDeleted = Constants.DEFAULT_UID;
        final Payments payment = ParserUtils.toPayments(entity);
        final DatabaseManager databaseManager = new DatabaseManager(context);

        if (!ParserUtils.isNullObject(payment)) {
            recordDeleted = databaseManager.deletePaymentsRecordFrom(payment);
        }

        if (isNotDefault(recordDeleted)) {
            CalendarsUtils.deleteCalendarEventsFrom(context, payment);
            ToastUtils.showLongText(context, R.string.successfully_deleted_record);
            ActivityUtils.refreshBy(context);
        } else {
            ToastUtils.showLongText(context, R.string.error_deleting_records);
        }
    }

    public static void deleteRecord(final Context context,
                                    final Object entity) {
        DialogUtils.confirmationDialog(context,
                R.string.delete_record_title,
                R.string.delete_record_message,
                () -> deleteDataFrom(context, entity));
    }
}
