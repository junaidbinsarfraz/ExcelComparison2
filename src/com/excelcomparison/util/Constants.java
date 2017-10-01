package com.excelcomparison.util;

import java.util.Arrays;
import java.util.List;

public interface Constants {
	
	String STATUS_MATCH = "Matched";
	String STATUS_ADDED = "Added";
	String STATUS_DELETED = "Deleted";
	String STATUS_UPDATED = "Updated";
	
	String DELETE_DUPLICATE = "Duplicate";
	String DELETE_FALSE_POSITIVE = "FP";
	String DELETE_UPDATE = "Delete Update";
	
	String ADD_FALSE_NEGATIVE = "FN";
	String ADD_UPDATE = "Add Update";
	
	String CORRECTION = "Correction";
	String TRANSFORMATION = "Transformation";
	String CORRECTION_TRANSFORMATION = "Correction Transformation";
	
	List<String> TITLES = Arrays.asList("Miss", "Mr", "Mrs", "Dr", "Sir", "Lord", "Jr", "HRH");
	List<String> QUALIFICATIONS = Arrays.asList("Phd", "MBA", "MBBS");
	
	List<String> VALID_DATE_FORMATS = Arrays.asList("dd/MM/yyyy", "MM/dd/yyyy", "dd-MM-yyyy", "d/mm/yy", "MM/dd/yy", "dd-MON-yy", "yyyy-dd-MM", "dd.MM.yyyy");
	
}
