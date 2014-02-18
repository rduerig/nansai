package com.github.nansai.provider;

import java.io.File;
import java.io.IOException;

import android.annotation.TargetApi;
import android.os.Environment;

import com.google.common.io.Files;

@TargetApi(19)
public class PersonProvider {

	private static final String PERSONS_FILE = "persons.txt";
	private static final String PERSONS_DIRECTORY = "nansai";
	private static final File PERSONS_PATH = Environment
			.getExternalStorageDirectory();

	/** Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		final String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/** Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		final String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	// ********************************************************************************

	private File getPersonsDir() {
		// Get the directory for the user's public pictures directory.
		final File file = new File(PERSONS_PATH, PERSONS_DIRECTORY);
		if (!file.mkdirs()) {
			// TODO error message
			// Log.e(LOG_TAG, "Directory not created");
		}
		return file;
	}

	private File getPersonsFile() {
		final File file = new File(getPersonsDir(), PERSONS_FILE);
		try {
			Files.touch(file);
		} catch (final IOException e) {
			// TODO error message
		}

		return file;
	}
}
