package com.github.nansai.handlers;

import com.github.nansai.util.DateDifferenceCalculator;

import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;

public class DateChangedListener implements OnDateChangedListener {
	
	private TextView displayResult;
	private DateDifferenceCalculator calc;

	public DateChangedListener(TextView displayResult, DateDifferenceCalculator calc) {
		this.displayResult = displayResult;
		this.calc = calc;
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
    	displayResult.setText(dayOfMonth+"."+monthOfYear+"."+year);
		
	}

}
