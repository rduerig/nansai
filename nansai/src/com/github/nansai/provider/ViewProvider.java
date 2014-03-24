package com.github.nansai.provider;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.nansai.R;

// TODO Exceptions when a certain view could not be found
public class ViewProvider {
	public static final int DATE_DIFFERENCE_RESULT = R.id.textViewComputed;
	public static final int DATE_PICKER = R.id.datePicker1;
	public static final int SCROLL_VIEW = R.id.scrollView1;
	public static final int PERSON_NAME_VIEW = R.id.person_name;
	public static final int PERSON_BIRTH_VIEW = R.id.person_birth;
	public static final int LISTVIEW_PERSONS = R.id.listView1;

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

	public EditText getPersonNameView(final Activity act) {
		final EditText displayResult = (EditText) act
				.findViewById(PERSON_NAME_VIEW);
		return displayResult;
	}

	public DatePicker getPersonBirth(final Activity act) {
		final DatePicker datePicker = (DatePicker) act
				.findViewById(PERSON_BIRTH_VIEW);
		return datePicker;
	}

	public ListView getPersonsListView(final Activity act) {
		final ListView listView = (ListView) act.findViewById(LISTVIEW_PERSONS);
		return listView;
	}

}
