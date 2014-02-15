package com.github.nansai.provider;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.nansai.R;

// TODO Exceptions when a certain view could not be found
public class ViewProvider {
	public static int DATE_DIFFERENCE_RESULT = R.id.textViewComputed;
	public static int DATE_PICKER = R.id.datePicker1;

	public ViewProvider() {
	}

	public TextView getDateDifferenceResult(final Activity act) {
		final TextView displayResult = (TextView) act
				.findViewById(DATE_DIFFERENCE_RESULT);
		return displayResult;
	}

	public DatePicker getDatePicker(final Activity act) {
		final DatePicker datePicker = (DatePicker) act
				.findViewById(DATE_PICKER);
		return datePicker;
	}

}
