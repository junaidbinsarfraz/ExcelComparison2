package com.excelcomparison.util;

import java.util.ArrayList;
import java.util.List;

public class NameUtil {
	
	public static String getShorterName(String name) {
		
		String shorterName = "";
		
		if(name != null && name != "") {
			String simpleName = removeTitlesAndQaulifications(name);
			
			if(simpleName != null && simpleName != "") {
				String[] nameParts = simpleName.split(" ");
				
				shorterName = nameParts.length > 1 ? nameParts[0].substring(0, 1) + "." + nameParts[nameParts.length - 1] : nameParts[0];
			}
		}
		
		return shorterName;
		
	}
	
	private static String removeTitlesAndQaulifications(String name) {
		
		String modifiedName = "";
		
		if(name != null && name != "") {
			String[] nameParts = name.split(" ");
			
			List<String> unwantedWords = new ArrayList<>();
			
			unwantedWords.addAll(Constants.TITLES);
			unwantedWords.addAll(Constants.QUALIFICATIONS);
			
			for(String namePart : nameParts) {
				
				Boolean isUnwantedWord = Boolean.FALSE;
				
				for(String unwantedWord : unwantedWords) {
					if((namePart.equals(unwantedWord + ".") || namePart.equals(unwantedWord))) {
						isUnwantedWord = Boolean.TRUE;
					}
				}
				
				if(Boolean.FALSE.equals(isUnwantedWord)) {
					modifiedName += namePart + " ";
				}
			}
		}
		
		return modifiedName.trim();
	}
	
}
