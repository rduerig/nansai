package com.github.nansai.util;

public class DateDifference {

	private final int years;
	private final int months;
	private final int days;

	public DateDifference(final int years, final int months, final int days) {
		this.years = years;
		this.months = months;
		this.days = days;
	}

	public int getYears() {
		return years;
	}

	public int getMonths() {
		return months;
	}

	public int getDays() {
		return days;
	}

}
