package com.github.nansai.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonWriter;

import com.github.nansai.data.Person;
import com.google.common.base.Charsets;
import com.google.common.io.Closeables;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PersonFileWriter {

	public boolean write(final List<Person> persons,
			final OutputStream personStream) {
		final OutputStreamWriter ouw = new OutputStreamWriter(personStream,
				Charsets.UTF_8);
		final JsonWriter writer = new JsonWriter(ouw);
		try {
			writer.beginArray();
			for (final Person person : persons) {
				writePerson(person, writer);
			}
			writer.endArray();
			writer.close();
			return true;
		} catch (final IOException e) {
			Closeables.closeQuietly(writer);
		} finally {
			Closeables.closeQuietly(writer);
		}

		return false;
	}

	// ********************************************************************************

	private void writePerson(final Person person, final JsonWriter writer)
			throws IOException {
		writer.beginObject();
		writer.name(PersonFileConstants.FIELD_ID);
		writer.value(person.getId());
		writer.name(PersonFileConstants.FIELD_NAME);
		writer.value(person.getName());
		writer.name(PersonFileConstants.FIELD_BIRTH);
		writer.value(person.getBirth());
		writer.endObject();
	}

}
