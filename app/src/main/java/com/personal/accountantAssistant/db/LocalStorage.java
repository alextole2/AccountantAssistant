package com.personal.accountantAssistant.db;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.personal.accountantAssistant.MainActivity;
import com.personal.accountantAssistant.dto.CalendarPeriod;
import com.personal.accountantAssistant.utils.Constants;
import com.personal.accountantAssistant.utils.DateUtils;
import com.personal.accountantAssistant.utils.ParserUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LocalStorage {

    private final static String AVAILABLE_MONEY = "AVAILABLE_MONEY";
    private final static String FIRST_STR_DATE = "FIRST_STR_DATE";
    private final static String LAST_STR_DATE = "LAST_STR_DATE";

    public static SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(MainActivity.getContext());
    }

    public static void setAvailableMoney(final float availableMoneyValue) {
        getDefaultSharedPreferences().edit().putFloat(AVAILABLE_MONEY, availableMoneyValue).apply();
    }

    public static float getAvailableMoney() {
        return getDefaultSharedPreferences().getFloat(AVAILABLE_MONEY, Float.parseFloat(Constants.STR_DEFAULT_MONETARY_VALUE));
    }

    private static void setFirstStrDate(final Date firstDate) {
        getDefaultSharedPreferences().edit().putString(FIRST_STR_DATE, DateUtils.toString(firstDate)).apply();
    }

    public static Date getFirstDate() {
        final String dateStr = getDefaultSharedPreferences().getString(FIRST_STR_DATE, Constants.EMPTY_STR);
        if (ParserUtils.isNotNullAndNotEmpty(dateStr)) {
            return DateUtils.toDate(dateStr);
        } else {
            return new Date();
        }
    }

    private static void setLastStrDate(final Date lastDate) {
        getDefaultSharedPreferences().edit().putString(LAST_STR_DATE, DateUtils.toString(lastDate)).apply();
    }

    public static Date getLastDate() {
        final String dateStr = getDefaultSharedPreferences().getString(LAST_STR_DATE, Constants.EMPTY_STR);
        if (ParserUtils.isNotNullAndNotEmpty(dateStr)) {
            return DateUtils.toDate(dateStr);
        } else {
            final Calendar nextMonth = Calendar.getInstance();
            nextMonth.add(Calendar.MONTH, 1);
            return nextMonth.getTime();
        }
    }

    public static void setPeriodDates(final Date firstDate,
                                      final Date lastDate) {
        setFirstStrDate(firstDate);
        setLastStrDate(lastDate);
    }

    public static List<Date> getPeriodDates() {
        final List<Date> dates = new ArrayList<>();
        dates.add(getFirstDate());
        dates.add(getLastDate());
        return dates;
    }
}
