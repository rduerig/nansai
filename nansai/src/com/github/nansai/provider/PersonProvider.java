package com.github.nansai.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import com.github.nansai.data.Person;
import com.github.nansai.io.PersonFileParser;
import com.github.nansai.io.PersonFileWriter;
import com.github.nansai.util.PersonNameComparator;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class PersonProvider {

	private final PersonFileProvider fileProv;
	private List<Person> persons;

	public PersonProvider(final PersonFileProvider fileProv) {
		this.fileProv = fileProv;
	}

	public List<Person> getPersons() {
		if (null == persons) {
			try {
				persons = initPersons(fileProv);
			} catch (final IOException e) {
				// TODO error msg
				persons = Lists.newArrayList();
			}
		}
		return persons;
	}

	public boolean addPerson(final Person person) {
		if (null != person && !Strings.isNullOrEmpty(person.getName())
				&& !Strings.isNullOrEmpty(person.getBirth())) {
			final boolean written = writePerson(person);
			if (written) {
				persons.add(person);
				return true;
			}
		}
		return false;
	}

	// ********************************************************************************

	private List<Person> initPersons(final PersonFileProvider fileProv)
			throws IOException {
		final File file = fileProv.getPersonsFile();
		final InputStream in = new FileInputStream(file);
		final PersonFileParser parser = new PersonFileParser(in);

		final List<Person> result = parser.parse();
		Collections.sort(persons, PersonNameComparator.ordering);
		return result;
	}

	private boolean writePerson(final Person person) {
		final File file = fileProv.getPersonsFile();
		try {
			final OutputStream out = new FileOutputStream(file);
			final PersonFileWriter writer = new PersonFileWriter(out);
			final List<Person> tmpPersons = Lists.newArrayList(getPersons());
			writer.write(tmpPersons);
		} catch (final IOException e) {
			// TODO error msg
			return false;
		}

		return true;

	}

}
