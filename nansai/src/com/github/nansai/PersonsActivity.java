package com.github.nansai;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.nansai.data.Person;
import com.github.nansai.provider.ExternalStorageProvider;
import com.github.nansai.provider.PersonFileProvider;
import com.github.nansai.provider.PersonProvider;
import com.github.nansai.provider.ViewProvider;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PersonsActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_persons);
		// Show the Up button in the action bar.
		setupActionBar();

		// fill persons list view
		final PersonProvider prov = new PersonProvider(new PersonFileProvider());
		final List<Person> persons = prov.getPersons();
		final ListView list = new ViewProvider().getPersonsListView(this);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		for (final Person person : persons) {
			adapter.add(person.getName());
		}
		list.setAdapter(adapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.persons, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onAddPersonClick(final View view) {
		if (!ExternalStorageProvider.isExternalStorageWritable()) {
			// TODO show error
			return;
		}
		final PersonFileProvider prov = new PersonFileProvider();
		// TODO show dialog for adding a person
		// prov.getPersonsFile()
		final DialogFragment dialog = new AddPersonDialogFragment();
		dialog.show(getFragmentManager(), "addperson");
	}

}
