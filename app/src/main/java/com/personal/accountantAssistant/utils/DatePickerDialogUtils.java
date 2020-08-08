package com.personal.accountantAssistant.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.EditText;

import com.personal.accountantAssistant.db.LocalStorage;
import com.personal.accountantAssistant.dto.CalendarPeriod;
import com.savvi.rangedatepicker.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.functions.Action;

public class DatePickerDialogUtils {

    public static void setDatePickerDialogFrom(final Context context,
                                               final EditText dateEditText,
                                               final String defaultDateText) {
        dateEditText.setText(defaultDateText);
        final Calendar defaultCalendar = Calendar.getInstance();
        defaultCalendar.setTime(DateUtils.toDate(defaultDateText));
        final DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    dateEditText.setText(DateUtils.toString(newDate.getTime()));
                },
                defaultCalendar.get(Calendar.YEAR),
                defaultCalendar.get(Calendar.MONTH),
                defaultCalendar.get(Calendar.DAY_OF_MONTH));

        dateEditText.setOnClickListener(view -> datePickerDialog.show());
        dateEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                datePickerDialog.show();
            }
        });
    }

    public static void initializeCalendarPickerView(final CalendarPickerView calendarPickerView,
                                                    final Action selectedPeriodAction,
                                                    final Action selectedDateAction) {
        final CalendarPeriod calendarPeriod = CalendarPeriod.initDefaultPeriodDates();
        final Calendar calendarStart = calendarPeriod.getStart();
        final Calendar calendarEnd = calendarPeriod.getEnd();
        calendarPickerView.init(calendarStart.getTime(), calendarEnd.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDates(LocalStorage.getPeriodDates()
                        .stream()
                        .filter(periodDate -> DateUtils.isInRange(periodDate, calendarStart.getTime(), calendarEnd.getTime()))
                        .collect(Collectors.toList()));

        final List<Date> selectedDates = new ArrayList<>();
        calendarPickerView.setTypeface(Typeface.SANS_SERIF);
        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                selectedDates.add(date);
                if (selectedDates.size() == 2) {
                    final Date firstSelectedDate = selectedDates.get(0);
                    final Date lastSelectedDate = selectedDates.get(1);
                    LocalStorage.setPeriodDates(firstSelectedDate, lastSelectedDate);
                    ActionUtils.runAction(selectedPeriodAction);
                }
                ActionUtils.runAction(selectedDateAction);
            }

            @Override
            public void onDateUnselected(Date date) {
                selectedDates.clear();
            }
        });
    }
}

