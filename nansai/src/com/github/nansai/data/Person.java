package com.github.nansai.data;

import java.util.UUID;

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
	 * @param birth
	 * @return
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
	 * @return
	 */
	public static Person createNewFromPerson(final Person person) {
		final String id = UUID.randomUUID().toString();
		return new Person(id, person.getName(), person.getBirth());
	}

	public static Person create(final String id, final String name,
			final String birth) {
		return new Person(id, name, birth);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBirth() {
		return birth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
