package com.github.nansai.util;

import com.github.nansai.data.Person;
import com.google.common.base.Function;
import com.google.common.collect.Ordering;

public class PersonNameComparator {
	public static Ordering<Person> ordering = Ordering
			.from(String.CASE_INSENSITIVE_ORDER).nullsFirst()
			.onResultOf(new Function<Person, String>() {
				@Override
				public String apply(final Person p) {
					return p.getName();
				}
			});

}
