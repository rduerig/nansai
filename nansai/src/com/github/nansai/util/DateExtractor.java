package com.github.nansai.util;

import java.util.Calendar;

import org.joda.time.LocalDate;

import android.widget.DatePicker;

public class DateExtractor {

	public static LocalDate extractRequestDate(final DatePicker datePicker) {
		final int year = datePicker.getYear();
		final int month = datePicker.getMonth();
		final int day = datePicker.getDayOfMonth();
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final LocalDate requestDate = LocalDate.fromCalendarFields(calendar);
		return requestDate;
	}

}
