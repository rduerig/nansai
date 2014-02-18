package com.github.nansai.handlers;

import java.util.Calendar;
import java.util.Map;

import org.joda.time.LocalDate;

import android.app.Activity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.nansai.provider.PersonProvider;
import com.github.nansai.provider.ViewProvider;
import com.github.nansai.util.ActivityConstants;
import com.github.nansai.util.DateDifference;
import com.github.nansai.util.DateDifferenceCalculator;
import com.github.nansai.util.DateDifferenceFormatter;
import com.google.common.collect.Maps;

public class OnComputeClickHandler {

	private final Activity act;
	private final ViewProvider prov;
	private final PersonProvider persons;
	private final DateDifferenceCalculator calc;

	public OnComputeClickHandler(final Activity act) {
		this.act = act;
		this.prov = new ViewProvider();
		this.persons = new PersonProvider();
		calc = new DateDifferenceCalculator();
	}

	public void onComputeClick(final View view) {
		final LocalDate requestDate = extractRequestDate();

		// TODO get person's date
		final int personYear = act.getIntent().getIntExtra(
				ActivityConstants.PERSON_YEAR, 1988);
		final int personMonth = act.getIntent().getIntExtra(
				ActivityConstants.PERSON_MONTH, 3);
		final int personDay = act.getIntent().getIntExtra(
				ActivityConstants.PERSON_DAY, 29);
		final LocalDate personDate = new LocalDate(personYear, personMonth,
				personDay);

		final TextView displayResult = prov.getDateDifferenceResult(act);
		displayResult.setText(computeResult(personDate, requestDate));

		// TODO foreach person in list / file
		// final ScrollView scrollView = prov.getScrollView(act);
		// TextView child = new TextView(act);
		// child.setText(computeResult(personDate, requestDate));
		// scrollView.addView(child);

	}

	// ********************************************************************************

	private LocalDate extractRequestDate() {
		final DatePicker datePicker = prov.getDatePicker(act);
		final int year = datePicker.getYear();
		final int month = datePicker.getMonth();
		final int day = datePicker.getDayOfMonth();
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final LocalDate requestDate = LocalDate.fromCalendarFields(calendar);
		return requestDate;
	}

	private Map<String, LocalDate> extractPersonDates() {
		final Map<String, LocalDate> result = Maps.newHashMap();
		if (!persons.isExternalStorageReadable()) {
			return result;
		}
		return result;
	}

	private String computeResult(final LocalDate requestDate,
			final LocalDate personDate) {
		final DateDifference diff = calc.difference(requestDate, personDate);
		final String result = DateDifferenceFormatter.format(diff);
		return result;
	}

}
