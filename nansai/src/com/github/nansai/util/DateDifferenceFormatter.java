package com.github.nansai.util;

public class DateDifferenceFormatter {

	// TODO localize
	public static String format(final DateDifference diff) {
		final StringBuilder result = new StringBuilder();
		result.append(diff.getYears());
		result.append(" Jahre, ");
		result.append(diff.getMonths());
		result.append(" Monate, ");
		result.append(diff.getDays());
		result.append(" Tage, ");

		return result.toString();
	}

}
