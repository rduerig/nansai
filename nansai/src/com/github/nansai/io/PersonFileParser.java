package com.github.nansai.io;

import static com.github.nansai.io.PersonFileConstants.FIELD_BIRTH;
import static com.github.nansai.io.PersonFileConstants.FIELD_ID;
import static com.github.nansai.io.PersonFileConstants.FIELD_NAME;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;

import com.github.nansai.data.Person;
import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.io.Closeables;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PersonFileParser {

	public List<Person> parse(final InputStream personStream) {
		final List<Person> result = Lists.newArrayList();

		final InputStreamReader inr = new InputStreamReader(personStream,
				Charsets.UTF_8);
		final JsonReader reader = new JsonReader(inr);
		try {
			reader.beginArray();
			while (reader.hasNext()) {
				final Optional<Person> person = readPerson(reader);
				if (person.isPresent()) {
					result.add(person.get());
				}
			}
			reader.endArray();
			reader.close();
		} catch (final IOException e) {
			Closeables.closeQuietly(reader);
		} finally {
			Closeables.closeQuietly(reader);
		}

		return result;
	}

	private Optional<Person> readPerson(final JsonReader reader)
			throws IOException {
		String id = null;
		String name = null;
		String birth = null;
		reader.beginObject();
		while (reader.hasNext()) {
			final String field = reader.nextName();
			if (FIELD_ID.equalsIgnoreCase(field)) {
				// read id field -> parse id
				id = reader.nextString();
			} else if (FIELD_NAME.equalsIgnoreCase(field)) {
				// read name field -> parse name
				name = reader.nextString();
			} else if (FIELD_BIRTH.equalsIgnoreCase(field)) {
				// read birth field -> parse birth
				birth = reader.nextString();
			} else {
				// read unknown field -> skip value
				reader.skipValue();
			}
		}
		reader.endObject();

		if (null != id && null != name && null != birth) {
			return Optional.of(Person.create(id, name, birth));
		}

		return Optional.absent();
	}

}
