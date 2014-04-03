package com.github.nansai.handlers;

import org.joda.time.LocalDate;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.nansai.provider.ViewProvider;
import com.github.nansai.util.DateDifference;
import com.github.nansai.util.DateDifferenceCalculator;
import com.github.nansai.util.DateDifferenceFormatter;
import com.github.nansai.util.TimeZoneProvider;

@SuppressLint("NewApi")
@Deprecated
public class DateChangedListener extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private final DateDifferenceCalculator calc;
	private final ViewProvider prov;

	public DateChangedListener() {
		calc = new DateDifferenceCalculator();
		prov = new ViewProvider();
	}

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		// Use the current date as the default date in the picker

		final LocalDate date = LocalDate.now(TimeZoneProvider.getDefault());

		// Create a new instance of DatePickerDialog and return it
		final int year = date.getYear();
		final int month = date.getMonthOfYear();
		final int day = date.getDayOfMonth();
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(final DatePicker view, final int year,
			final int month, final int day) {
		// Do something with the date chosen by the user

		// TODO fetch person's date
		final LocalDate personDate = new LocalDate().minusYears(2);
		final LocalDate requestDate = new LocalDate(year, month, day);

		final TextView displayResult = prov
				.getDateDifferenceResult(getActivity());
		displayResult.setText(computeResult(personDate, requestDate));

	}

	private String computeResult(final LocalDate requestDate,
			final LocalDate personDate) {
		final DateDifference diff = calc.difference(requestDate, personDate);
		final String result = DateDifferenceFormatter.format(diff);
		return result;
	}

}
