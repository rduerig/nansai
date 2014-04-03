package com.github.nansai.handlers;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CancelClickHandler implements OnClickListener {

	private final DialogFragment dialogFragment;

	private CancelClickHandler(final DialogFragment dialogFragment) {
		this.dialogFragment = dialogFragment;
	}

	public static CancelClickHandler create(final DialogFragment dialogFragment) {
		return new CancelClickHandler(dialogFragment);
	}

	@Override
	public void onClick(final DialogInterface arg0, final int arg1) {
		dialogFragment.getDialog().cancel();
	}

}
