package com.github.nansai;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.github.nansai.handlers.OnComputeClickHandler;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onPersonChooseClick(final View view) {

		final Intent intent = new Intent(this, PersonsActivity.class);
		startActivity(intent);
	}

	public void onComputeClick(final View view) {
		getOnComputeClickHandler().onComputeClick(view);
	}

	// public void showDatePickerDialog(final View v) {
	// final DialogFragment newFragment = new DateChangedListener();
	// newFragment.show(getFragmentManager(), "datePicker");
	// }

	private OnComputeClickHandler getOnComputeClickHandler() {
		final OnComputeClickHandler onComputeClick = new OnComputeClickHandler(
				this);
		return onComputeClick;
	}

}
