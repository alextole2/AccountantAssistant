package com.personal.accountantAssistant.dto;

import com.personal.accountantAssistant.utils.DateUtils;

import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarPeriod {
    private Calendar start;
    private Calendar end;
    private Period period;

    public static CalendarPeriod initDefaultPeriodDates() {
        final List<Date> defaultPeriodDates = DateUtils.getDefaultPeriodDates();

        final Date firstDate = defaultPeriodDates.get(0);
        final Calendar currentMonth = Calendar.getInstance();
        currentMonth.setTime(firstDate);
        currentMonth.add(Calendar.DAY_OF_MONTH, -1);

        final Date lastDate = defaultPeriodDates.get(1);
        final Calendar nextMonth = Calendar.getInstance();
        nextMonth.setTime(lastDate);
        nextMonth.add(Calendar.DAY_OF_MONTH, +1);

        return new CalendarPeriod(currentMonth, nextMonth);
    }

    public CalendarPeriod(final Date start,
                          final Date end) {
        this.start = DateUtils.toCalendar(start);
        this.end = DateUtils.toCalendar(end);
        this.period = DateUtils.getPeriodBetween(this.start, this.end);
    }

    public CalendarPeriod(final Calendar start,
                          final Calendar end) {
        this.start = start;
        this.end = end;
        this.period = DateUtils.getPeriodBetween(this.start, this.end);
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
