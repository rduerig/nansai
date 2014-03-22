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

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PersonFileWriter {

	private final OutputStream personStream;

	public PersonFileWriter(final OutputStream personStream) {
		this.personStream = personStream;
	}

	public void write(final List<Person> persons) throws IOException {
		final OutputStreamWriter ouw = new OutputStreamWriter(personStream,
				Charsets.UTF_8);
		final JsonWriter writer = new JsonWriter(ouw);
		writer.beginArray();
		for (final Person person : persons) {
			writePerson(person, writer);
		}
		writer.endArray();
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
