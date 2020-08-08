package com.personal.accountantAssistant.dto;

import com.personal.accountantAssistant.utils.DateUtils;
import com.personal.accountantAssistant.utils.ParserUtils;

import java.util.Calendar;
import java.util.Date;

public class CalendarValues {

    private Calendar calendar;
    private Date date;
    private String strDate;

    private int year;
    private int month;
    private int dayOfMonth;

    public CalendarValues(final Calendar calendar) {
        this.calendar = calendar;
        this.date = calendar.getTime();
        this.strDate = DateUtils.toString(this.date);

        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
