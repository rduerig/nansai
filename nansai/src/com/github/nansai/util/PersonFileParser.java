package com.github.nansai.util;

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

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PersonFileParser {

	private static final String FIELD_ID = "id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_BIRTH = "birth";

	private final InputStream personStream;

	public PersonFileParser(final InputStream personStream) {
		this.personStream = personStream;
	}

	public List<Person> parse() throws IOException {
		final List<Person> result = Lists.newArrayList();

		final InputStreamReader inr = new InputStreamReader(personStream,
				Charsets.UTF_8);
		final JsonReader reader = new JsonReader(inr);
		reader.beginArray();
		while (reader.hasNext()) {
			final Optional<Person> person = readPerson(reader);
			if (person.isPresent()) {
				result.add(person.get());
			}
		}
		reader.endArray();

		return result;
	}

	private Optional<Person> readPerson(final JsonReader reader)
			throws IOException {
		String id = null;
		String name = null;
		String birth = null;
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
