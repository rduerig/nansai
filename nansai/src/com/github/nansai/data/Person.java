package com.github.nansai.data;

import java.util.UUID;

import com.google.common.base.Objects;

public class Person {

	private final String id;
	private final String name;
	private final String birth;

	private Person(final String id, final String name, final String birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}

	/**
	 * Creates a new person with the given name and birth date. An id is
	 * automatically generated.
	 * 
	 * @param name
	 *            the person's name
	 * @param birth
	 *            the person's birth
	 * @return the newly created person
	 */
	public static Person createNew(final String name, final String birth) {
		final String id = UUID.randomUUID().toString();
		return new Person(id, name, birth);
	}

	/**
	 * Creates a new person using the name and the birth date of the given
	 * person. Only the new person's id is generated.
	 * 
	 * @param person
	 *            the person to copy
	 * @return the newly created person
	 */
	public static Person createNewFromPerson(final Person person) {
		final String id = UUID.randomUUID().toString();
		return new Person(id, person.getName(), person.getBirth());
	}

	/**
	 * Creates a new person using the name and the birth date of the given
	 * person. The newly created person will also have the given id.
	 * 
	 * @param id
	 *            the person's id
	 * @param name
	 *            the person's name
	 * @param birth
	 *            the person's birth
	 * @return the newly created person
	 */
	public static Person create(final String id, final String name,
			final String birth) {
		return new Person(id, name, birth);
	}

	/**
	 * Returns the person's id.
	 * 
	 * @return the person's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the person's name.
	 * 
	 * @return the person's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the person's birth.
	 * 
	 * @return the person's birth
	 */
	public String getBirth() {
		return birth;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		final Person other = (Person) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
