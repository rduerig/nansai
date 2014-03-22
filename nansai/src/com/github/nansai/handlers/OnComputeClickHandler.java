package com.github.nansai.handlers;

import java.util.Map;

import org.joda.time.LocalDate;

import android.app.Activity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.nansai.provider.ExternalStorageProvider;
import com.github.nansai.provider.PersonFileProvider;
import com.github.nansai.provider.ViewProvider;
import com.github.nansai.util.ActivityConstants;
import com.github.nansai.util.DateDifference;
import com.github.nansai.util.DateDifferenceCalculator;
import com.github.nansai.util.DateDifferenceFormatter;
import com.github.nansai.util.DateExtractor;
import com.google.common.collect.Maps;

public class OnComputeClickHandler {

	private final Activity act;
	private final ViewProvider prov;
	private final PersonFileProvider persons;
	private final DateDifferenceCalculator calc;

	public OnComputeClickHandler(final Activity act) {
		this.act = act;
		this.prov = new ViewProvider();
		this.persons = new PersonFileProvider();
		calc = new DateDifferenceCalculator();
	}

	public void onComputeClick(final View view) {
		final DatePicker datePicker = prov.getDatePicker(act);
		final LocalDate requestDate = DateExtractor
				.extractRequestDate(datePicker);

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

	private Map<String, LocalDate> extractPersonDates() {
		final Map<String, LocalDate> result = Maps.newHashMap();
		if (!ExternalStorageProvider.isExternalStorageReadable()) {
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
