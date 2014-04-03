package com.github.nansai.handlers;

import org.joda.time.LocalDate;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.github.nansai.AddPersonDialogFragment.PersonAddHandler;
import com.github.nansai.R;
import com.github.nansai.data.Person;
import com.github.nansai.provider.PersonFileProvider;
import com.github.nansai.provider.PersonProvider;
import com.github.nansai.provider.ViewProvider;
import com.github.nansai.util.DateExtractor;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AddPersonOkClickHandler implements OnClickListener {

	private final DialogFragment dialogFragment;
	private final View view;
	private final PersonAddHandler handler;

	public AddPersonOkClickHandler(final DialogFragment dialogFragment,
			final View view, final PersonAddHandler handler) {
		this.dialogFragment = dialogFragment;
		this.view = view;
		this.handler = handler;
	}

	@Override
	public void onClick(final DialogInterface dialogInterface, final int arg1) {
		// TODO add person

		final EditText nameView = (EditText) view
				.findViewById(R.id.person_name);
		final DatePicker birthView = (DatePicker) view
				.findViewById(R.id.person_birth);

		final String name = nameView.getText().toString();
		final LocalDate birthDate = DateExtractor.extractRequestDate(birthView);
		final String birth = birthDate.toString();

		final Person person = Person.createNew(name, birth);
		final PersonProvider prov = new PersonProvider(new PersonFileProvider());
		final boolean added = prov.addPerson(person);
		// TODO update list of persons
		// on success, error
		// msg otherwise
		if (added) {
			final ListView personsView = new ViewProvider()
					.getPersonsListView(dialogFragment.getActivity());
			// TODO persons list must have a dynamic datasource
			// this way changes are not transported to the list view
			final ArrayAdapter<String> adapter = (ArrayAdapter<String>) personsView
					.getAdapter();
			adapter.add(person.getName());
			adapter.notifyDataSetChanged();

			// notify the handler
			notify(handler, person);
		}
		dialogFragment.getDialog().dismiss();
	}

	// ********************************************************************************

	private void notify(final PersonAddHandler handler, final Person person) {
		if (null != handler) {
			handler.onPersonAdded(person);
		}
	}

}
