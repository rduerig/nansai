package com.github.nansai;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.github.nansai.data.Person;
import com.github.nansai.handlers.AddPersonOkClickHandler;
import com.github.nansai.handlers.CancelClickHandler;
import com.github.nansai.provider.ViewProvider;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AddPersonDialogFragment extends DialogFragment {

	private final ViewProvider prov;
	private PersonAddHandler handler;

	public AddPersonDialogFragment() {
		prov = new ViewProvider();
	}

	public void setPersonAddHandler(final PersonAddHandler handler) {
		this.handler = handler;
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
		final OnClickListener okListener = new AddPersonOkClickHandler(
				AddPersonDialogFragment.this, view, handler);
		builder.setView(view)
				// Add action buttons
				.setPositiveButton(R.string.person_ok, okListener)
				.setNegativeButton(R.string.person_cancel,
						CancelClickHandler.create(AddPersonDialogFragment.this));

		final AlertDialog dialog = builder.create();
		return dialog;
	}

	// ********************************************************************************

	public interface PersonAddHandler {
		void onPersonAdded(Person p);
	}

}
