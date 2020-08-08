package com.personal.accountantAssistant.utils;

import com.personal.accountantAssistant.dto.CalendarValues;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class DateUtils {

    private static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static Date toDate(final String strDate) {
        Date date = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY, Locale.getDefault());
        try {
            date = dateFormat.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String toString(final Date date) {
        String strDate = String.valueOf(date);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY, Locale.getDefault());
        try {
            strDate = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    public static String toCurrentDateStr() {
        final Calendar calendar = Calendar.getInstance();
        final CalendarValues calendarValues = new CalendarValues(calendar);
        return calendarValues.getDayOfMonth() +
                Constants.DASH_SEPARATOR +
                calendarValues.getMonth() +
                Constants.DASH_SEPARATOR +
                calendarValues.getYear();
    }

    public static String toPeriodStr(final Date firstDate,
                                     final Date lastDate) {
        return DateUtils.toString(firstDate) +
                Constants.DASH_SEPARATOR +
                DateUtils.toString(lastDate);
    }

    public static Calendar toCalendar(final Date date) {
        final Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        return calendarDate;
    }

    public static long toCalendarMillis(final Date date) {
        return toCalendar(date).getTimeInMillis();
    }

    public static LocalDate toLocalDate(final Calendar calendar) {
        final CalendarValues calendarValues = new CalendarValues(calendar);
        return LocalDate.of(calendarValues.getYear(), calendarValues.getMonth(), calendarValues.getDayOfMonth());
    }

    public static LocalDate toLocalDate(final Date date) {
        return toLocalDate(toCalendar(date));
    }

    public static Period getPeriodBetween(final Calendar start,
                                          final Calendar end) {
        final LocalDate startLocalDate = toLocalDate(start);
        final LocalDate endLocalDate = toLocalDate(end);
        return Period.between(startLocalDate, endLocalDate);
    }

    public static List<Calendar> getDefaultPeriodCalendars() {

        final List<Calendar> calendars = new ArrayList<>();

        final Calendar currentMonth = Calendar.getInstance();
        calendars.add(currentMonth);

        final Calendar nextMonth = Calendar.getInstance();
        nextMonth.add(Calendar.MONTH, 1);
        calendars.add(nextMonth);

        return calendars;
    }

    public static boolean isNullPeriod(final Calendar startDate,
                                       final Calendar endDate) {
        return Objects.isNull(startDate) && Objects.isNull(endDate);
    }

    public static Integer getDaysBetween(final Date initDate,
                                         final Date endDate) {
        return getDaysBetween(toCalendar(initDate), toCalendar(endDate));
    }

    public static Integer getDaysBetween(final Calendar initDate,
                                         final Calendar endDate) {
        if (isNullPeriod(initDate, endDate)) {
            return 0;
        }
        final LocalDate lInit = initDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final LocalDate lEnd = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Math.toIntExact(ChronoUnit.DAYS.between(lInit, lEnd));
    }

    public static boolean isInRange(final Date compareDate,
                                    final Date lastRangeDate) {
        final LocalDate localCompareDate = toLocalDate(compareDate);
        final LocalDate localLastRangeDate = toLocalDate(lastRangeDate);
        return (localCompareDate.isBefore(localLastRangeDate) || localCompareDate.isEqual(localLastRangeDate));
    }

    public static boolean isInRange(final Date compareDate,
                                    final Date startRangeDate,
                                    final Date lastRangeDate) {
        final LocalDate localCompareDate = toLocalDate(compareDate);
        final LocalDate localStartRangeDate = toLocalDate(startRangeDate);
        final LocalDate localLastRangeDate = toLocalDate(lastRangeDate);
        return (localCompareDate.isAfter(localStartRangeDate) || localCompareDate.isEqual(localStartRangeDate)) &&
                (localCompareDate.isBefore(localLastRangeDate) || localCompareDate.isEqual(localLastRangeDate));
    }

    public static List<Date> getDefaultPeriodDates() {
        return getDefaultPeriodCalendars()
                .stream()
                .map(Calendar::getTime)
                .collect(Collectors.toList());
    }
}
