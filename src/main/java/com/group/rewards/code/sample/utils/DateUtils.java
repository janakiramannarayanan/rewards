package com.group.rewards.code.sample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final String DATE_TIME_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ssZ";

	public static Date convertStringToDate(String dateString) throws ParseException {
		SimpleDateFormat dateTimeWithTimeZoneFormat = new SimpleDateFormat(DATE_TIME_WITH_TIMEZONE);
		return dateTimeWithTimeZoneFormat.parse(dateString);
	}

}
