package com.github.nansai.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import com.google.common.io.Closeables;

public class PersonProvider {

	private final PersonFileProvider fileProv;
	private List<Person> persons;

	public PersonProvider(final PersonFileProvider fileProv) {
		this.fileProv = fileProv;
	}

	public List<Person> getPersons() {
		if (null == persons) {
			persons = initPersons(fileProv);
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

	private List<Person> initPersons(final PersonFileProvider fileProv) {
		List<Person> result = Lists.newArrayList();
		final File file = fileProv.getPersonsFile();
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			final PersonFileParser parser = new PersonFileParser();
			result = parser.parse(in);
			in.close();
			Collections.sort(result, PersonNameComparator.ordering);
			return result;
		} catch (final FileNotFoundException e) {
			if (null != in) {
				Closeables.closeQuietly(in);
			}
		} catch (final IOException e) {
			if (null != in) {
				Closeables.closeQuietly(in);
			}
		} finally {
			if (null != in) {
				Closeables.closeQuietly(in);
			}
		}

		return result;

	}

	private boolean writePerson(final Person person) {
		final File file = fileProv.getPersonsFile();
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			final PersonFileWriter writer = new PersonFileWriter();
			final List<Person> tmpPersons = Lists.newArrayList(getPersons());
			tmpPersons.add(person);
			writer.write(tmpPersons, out);
			out.close();
			return true;
		} catch (final IOException e) {
			if (null != out) {
				Closeables.closeQuietly(out);
			}
		} finally {
			if (null != out) {
				Closeables.closeQuietly(out);
			}
		}
		return false;

	}

}
