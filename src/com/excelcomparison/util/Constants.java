package com.excelcomparison.util;

import java.util.Arrays;
import java.util.List;

/**
 * The interface Constants is use to hold the constants
 * 
 * @author Junaid
 */
public interface Constants {

	String CORRECTION = "Correction";
	String TRANSFORMATION = "Transformation";
	String CORRECTION_TRANSFORMATION = "Correction Transformation";

	List<String> TITLES = Arrays.asList("Miss", "Mr", "Mrs", "Dr", "Sir", "Lord", "Jr", "HRH");
	List<String> QUALIFICATIONS = Arrays.asList("Phd", "MBA", "MBBS");

	List<String> VALID_DATE_FORMATS = Arrays.asList("dd/MM/yyyy", "MM/dd/yyyy", "dd-MM-yyyy", "d/mm/yy", "MM/dd/yy",
			"dd-MON-yy", "yyyy-dd-MM", "dd.MM.yyyy");

}
