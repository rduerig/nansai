package com.github.nansai.util;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class DateDifferenceCalculator {

	public DateDifference difference(final LocalDate from, final LocalDate to) {
		final Period p = new Period(from, to, PeriodType.yearMonthDay());

		final DateDifference result = new DateDifference(p.getYears(),
				p.getMonths(), p.getDays());
		return result;
	}

}
