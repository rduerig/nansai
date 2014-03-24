package com.github.nansai;

import org.joda.time.LocalDate;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.github.nansai.data.Person;
import com.github.nansai.provider.PersonFileProvider;
import com.github.nansai.provider.PersonProvider;
import com.github.nansai.provider.ViewProvider;
import com.github.nansai.util.DateExtractor;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AddPersonDialogFragment extends DialogFragment {

	private final ViewProvider prov;

	public AddPersonDialogFragment() {
		prov = new ViewProvider();
	}

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());

		builder.setCancelable(true);
		final LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog
		// layoutview
		final View view = inflater.inflate(R.layout.dialog_addperson, null);
		builder.setView(view)
				// Add action buttons
				.setPositiveButton(R.string.person_ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog,
									final int id) {
								// TODO add person

								final EditText nameView = (EditText) view
										.findViewById(R.id.person_name);
								final DatePicker birthView = (DatePicker) view
										.findViewById(R.id.person_birth);

								final String name = nameView.getText()
										.toString();
								final LocalDate birthDate = DateExtractor
										.extractRequestDate(birthView);
								final String birth = birthDate.toString();

								final Person person = Person.createNew(name,
										birth);
								final PersonProvider prov = new PersonProvider(
										new PersonFileProvider());
								final boolean added = prov.addPerson(person);
								// TODO update list of persons on success, error
								// msg otherwise
								if (added) {
									final ListView personsView = new ViewProvider()
											.getPersonsListView(getActivity());
									final ArrayAdapter<String> adapter = (ArrayAdapter<String>) personsView
											.getAdapter();
									adapter.add(person.getName());
									adapter.notifyDataSetChanged();
								}
								AddPersonDialogFragment.this.getDialog()
										.dismiss();
							}
						})
				.setNegativeButton(R.string.person_cancel,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog,
									final int id) {
								AddPersonDialogFragment.this.getDialog()
										.cancel();
							}
						});

		final AlertDialog dialog = builder.create();
		return dialog;
	}

}
