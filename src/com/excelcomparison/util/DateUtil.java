package com.excelcomparison.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class DateUtil is use to manipulate the dates
 * 
 * @author Junaid
 */
public class DateUtil {

	/**
	 * The method getDateFormat() is use to get the date format of given string
	 * date
	 * 
	 * @param dateStr
	 *            whose format need to be extracted
	 * @return date format
	 */
	public static String getDateFormat(String dateStr) {

		if (dateStr != null && dateStr != "") {
			for (String format : Constants.VALID_DATE_FORMATS) {
				try {
					SimpleDateFormat simpleDateFormate = new SimpleDateFormat(format);
					simpleDateFormate.setLenient(Boolean.FALSE);

					Date date = simpleDateFormate.parse(dateStr);

					return format;
				} catch (Exception e) {
					// Wrong date format, no need to log
				}
			}
		}

		return null;
	}

	/**
	 * The method getDate() is use to convert string date to date object in
	 * given format
	 * 
	 * @param dateStr
	 *            need to be converted to date object
	 * @param format
	 *            date object format
	 * @return date object
	 */
	public static Date getDate(String dateStr, String format) {

		try {

			SimpleDateFormat simpleDateFormate = new SimpleDateFormat(format);
			simpleDateFormate.setLenient(Boolean.FALSE);

			return simpleDateFormate.parse(dateStr);

		} catch (Exception e) {
			return null;
		}

	}

}
