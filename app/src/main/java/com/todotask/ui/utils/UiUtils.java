package com.todotask.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.todotask.common.Constants.DATE_TITLE_FORMAT;

/**
 *
 */
public final class UiUtils {

	/**
	 * Formats miliseconds in the format DD MMMM (January etc), YYYY (2015)
	 * @param miliseconds to convert
	 * @return formatted date
	 */
	public static String formatToFullDate(long miliseconds) {

		String pattern = DATE_TITLE_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = new Date(miliseconds);

		return sdf.format(date);

	}
}
