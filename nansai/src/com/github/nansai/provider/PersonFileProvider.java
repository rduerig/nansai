package com.github.nansai.provider;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

import com.google.common.io.Files;

public class PersonFileProvider {

	private static final String PERSONS_FILE = "persons.txt";
	private static final String PERSONS_DIRECTORY = "nansai";
	private static final File PERSONS_PATH = Environment
			.getExternalStorageDirectory();

	public File getPersonsFile() {
		final File file = new File(getPersonsDir(), PERSONS_FILE);
		try {
			Files.touch(file);
		} catch (final IOException e) {
			// TODO error message
		}

		return file;
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
}
