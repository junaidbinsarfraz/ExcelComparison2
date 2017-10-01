package com.excelcomparison.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getDateFormat(String dateStr) {
		
		if(dateStr != null && dateStr != "") {
			for(String format : Constants.VALID_DATE_FORMATS) {
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
