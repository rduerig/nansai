package com.github.nansai.handlers;

import org.joda.time.LocalDate;

import android.app.Activity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.nansai.data.Person;
import com.github.nansai.provider.PersonFileProvider;
import com.github.nansai.provider.PersonProvider;
import com.github.nansai.provider.ViewProvider;
import com.github.nansai.util.ActivityConstants;
import com.github.nansai.util.DateDifference;
import com.github.nansai.util.DateDifferenceCalculator;
import com.github.nansai.util.DateDifferenceFormatter;
import com.github.nansai.util.DateExtractor;

public class OnComputeClickHandler {

	private final Activity act;
	private final ViewProvider prov;
	private final PersonProvider persons;
	private final DateDifferenceCalculator calc;

	public OnComputeClickHandler(final Activity act) {
		this.act = act;
		this.prov = new ViewProvider();
		this.persons = new PersonProvider(new PersonFileProvider());
		calc = new DateDifferenceCalculator();
	}

	public void onComputeClick(final View view) {
		final DatePicker datePicker = prov.getDatePicker(act);
		final LocalDate requestDate = DateExtractor
				.extractRequestDate(datePicker);

		final int personYear = act.getIntent().getIntExtra(
				ActivityConstants.PERSON_YEAR, 1988);
		final int personMonth = act.getIntent().getIntExtra(
				ActivityConstants.PERSON_MONTH, 3);
		final int personDay = act.getIntent().getIntExtra(
				ActivityConstants.PERSON_DAY, 29);
		final LocalDate personDate = new LocalDate(personYear, personMonth,
				personDay);

		final TextView displayResult = prov.getDateDifferenceResult(act);
		final String age = computeResult(personDate, requestDate);
		displayResult.setText(age);

		// TODO foreach person in list / file
		// final List<Person> personList = persons.getPersons();
		// final ScrollView scrollView = prov.getScrollView(act);
		// for (final Person p : personList) {
		// final TextView child = new TextView(act);
		// final String personText = formatPerson(p, age);
		// child.setText(personText);
		// scrollView.addView(child);
		// }

	}

	// ********************************************************************************

	private String formatPerson(final Person p, final String age) {
		final StringBuilder sb = new StringBuilder();
		sb.append(p.getName());
		sb.append(" ist: ");
		sb.append(age);
		return sb.toString();
	}

	private String computeResult(final LocalDate requestDate,
			final LocalDate personDate) {
		final DateDifference diff = calc.difference(requestDate, personDate);
		final String result = DateDifferenceFormatter.format(diff);
		return result;
	}

}
