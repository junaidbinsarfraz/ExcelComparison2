package com.excelcomparison.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.excelcomparison.util.Constants;
import com.excelcomparison.util.DateUtil;
import com.excelcomparison.util.NameUtil;

/**
 * The class EmployeeController is use to control the comparison of employees' data
 * 
 * @author Junaid
 */
public class EmployeeController {

	/**
	 * The method compareNames() is use to compare employees' name
	 * @param names to be compared
	 * @return result
	 */
	public List<String> compareNames(Map<String, String> names) {
		List<String> results = new LinkedList<>();
		
		try {
			// loop on each name
			for(String rawName : names.keySet()) {
				String result = "";
				String edittedName = names.get(rawName);
				
				// Check if not null and empty
				if(rawName != null && rawName != "" && edittedName != null && edittedName != "" && !edittedName.equals(rawName)) {
					String shortRawName = NameUtil.getShorterName(rawName);
					
					if(edittedName.equals(shortRawName)) {
						result = Constants.TRANSFORMATION;
					} else if(rawName.equals(shortRawName)) {
						result = Constants.CORRECTION;
					} else {
						result = Constants.CORRECTION_TRANSFORMATION;
					}
				}
				
				results.add(result);
			}
			
		} catch(Exception e) {
			return null;
		}
		
		return results;
	}
	
	/**
	 * The method compareDates is use to compare employees' date
	 * @param dates to be compared
	 * @return results
	 */
	public List<String> compareDates(Map<String, String> dates) {
		List<String> results = new LinkedList<>();
		
		try {
			// loop on eah date
			for(String rawDateStr : dates.keySet()) {
				String result = "";
				String edittedDateStr = dates.get(rawDateStr);
				
				if(rawDateStr != null && rawDateStr != "" && edittedDateStr != null && edittedDateStr != "" && !edittedDateStr.equals(rawDateStr)) {
					
					// get the raw and edited dates' format
					String rawDateFormat = DateUtil.getDateFormat(rawDateStr);
					String edittedDateFormat = DateUtil.getDateFormat(edittedDateStr);
					
					if(rawDateFormat != null && edittedDateFormat != null) {
						
						// Convert string date to date object
						Date rawDate = DateUtil.getDate(rawDateStr, rawDateFormat);
						Date edittedDate = DateUtil.getDate(edittedDateStr, edittedDateFormat);

						// compare dates
						if(rawDateFormat.equals(edittedDateFormat)) {
							// Check for correction
							if(rawDate.compareTo(edittedDate) != 0) {
								result = Constants.CORRECTION;
							}
						} else if(rawDate.compareTo(edittedDate) == 0) {
							// Transformation 
							result = Constants.TRANSFORMATION;
						} else if(rawDate.compareTo(edittedDate) != 0) {
							// Transformation Correction
							result = Constants.CORRECTION_TRANSFORMATION;
						}
					}
				}
				
				results.add(result);
			}
			
		} catch(Exception e) {
			return null;
		}
		
		return results;
	}

}
