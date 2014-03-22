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
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.nansai.data.Person;
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
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(R.layout.dialog_addperson, null))
				// Add action buttons
				.setPositiveButton(R.string.person_ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog,
									final int id) {
								// TODO add person
								final TextView nameView = prov
										.getPersonNameView(getActivity());
								final DatePicker birthView = prov
										.getPersonBirth(getActivity());

								final String name = nameView.getText()
										.toString();
								final LocalDate birthDate = DateExtractor
										.extractRequestDate(birthView);
								final String birth = birthDate.toString();

								final Person person = Person.createNew(name, birth);
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

		return builder.create();
	}

}
