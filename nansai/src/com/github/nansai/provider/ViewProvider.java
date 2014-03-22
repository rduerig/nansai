package com.github.nansai.provider;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.nansai.R;

// TODO Exceptions when a certain view could not be found
public class ViewProvider {
	public static int DATE_DIFFERENCE_RESULT = R.id.textViewComputed;
	public static int DATE_PICKER = R.id.datePicker1;
	public static int SCROLL_VIEW = R.id.scrollView1;
	public static int PERSON_NAME_VIEW = R.id.person_name;
	public static int PERSON_BIRTH_VIEW = R.id.person_birth;

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

	public ScrollView getScrollView(final Activity act) {
		final ScrollView view = (ScrollView) act.findViewById(SCROLL_VIEW);
		return view;
	}

	public TextView getPersonNameView(final Activity act) {
		final TextView displayResult = (TextView) act
				.findViewById(PERSON_NAME_VIEW);
		return displayResult;
	}

	public DatePicker getPersonBirth(final Activity act) {
		final DatePicker datePicker = (DatePicker) act
				.findViewById(PERSON_BIRTH_VIEW);
		return datePicker;
	}

}
