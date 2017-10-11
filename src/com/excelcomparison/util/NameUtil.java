package com.excelcomparison.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The class NameUtil is use to manipulate names
 * 
 * @author Junaid
 */
public class NameUtil {

	/**
	 * The method getShorterName() is use to convert the name into shorter name
	 * 
	 * @param name
	 *            to be converted
	 * @return shorter name
	 */
	public static String getShorterName(String name) {

		String shorterName = "";

		if (name != null && name != "") {
			String simpleName = removeTitlesAndQaulifications(name);

			// Get parts of name
			if (simpleName != null && simpleName != "") {
				String[] nameParts = simpleName.split(" ");

				shorterName = nameParts.length > 1
						? nameParts[0].substring(0, 1) + "." + nameParts[nameParts.length - 1] : nameParts[0];
			}
		}

		return shorterName;

	}

	/**
	 * The method removeTitlesAndQaulifications() is use to remove unwanted
	 * titles and qualifications from name
	 * 
	 * @param name
	 *            to be manipulated
	 * @return simpler name
	 */
	private static String removeTitlesAndQaulifications(String name) {

		String modifiedName = "";

		if (name != null && name != "") {
			String[] nameParts = name.split(" ");

			List<String> unwantedWords = new ArrayList<>();

			// Add all unwanted words
			unwantedWords.addAll(Constants.TITLES);
			unwantedWords.addAll(Constants.QUALIFICATIONS);

			for (String namePart : nameParts) {

				Boolean isUnwantedWord = Boolean.FALSE;

				// Check if we need that word or not
				for (String unwantedWord : unwantedWords) {
					if ((namePart.equals(unwantedWord + ".") || namePart.equals(unwantedWord))) {
						isUnwantedWord = Boolean.TRUE;
					}
				}

				if (Boolean.FALSE.equals(isUnwantedWord)) {
					modifiedName += namePart + " ";
				}
			}
		}

		return modifiedName.trim();
	}

}
