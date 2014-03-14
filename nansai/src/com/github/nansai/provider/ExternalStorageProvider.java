package com.github.nansai.provider;

import android.os.Environment;

public class ExternalStorageProvider {

	/** Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable() {
		final String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/** Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable() {
		final String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

}
