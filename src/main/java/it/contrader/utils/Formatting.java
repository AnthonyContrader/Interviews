package it.contrader.utils;

import org.springframework.util.StringUtils;

public class Formatting {
	private static String capitalizeAll (String text) {
		String loweredText = text.toLowerCase();
		String[] splittedText = StringUtils.split(loweredText, " ");
		if (splittedText != null) {
			for (int i=0; i < splittedText.length; i++) {
				splittedText[i] = StringUtils.capitalize(splittedText[i]);
			}
			return StringUtils.arrayToDelimitedString(splittedText, " ");
		} else {
			return StringUtils.capitalize(loweredText);
		}
	}
	
	public static String formatRecruiterName (String name) {
		return capitalizeAll(name);
	}
	
	public static String formatQuestionText (String text) {
		return StringUtils.capitalize(text);
	}
	
	public static String formatQuestionTopic (String topic) {
		return capitalizeAll(topic);
	}
	
	public static String formatCompanyName (String name) {
		return StringUtils.capitalize(name.toLowerCase());
	}
	
	public static String formatCompanySector (String sector) {
		return capitalizeAll(sector.toLowerCase());
	}
	
	public static String formatCompanyAddress (String address) {
		return capitalizeAll(address.toLowerCase());
	}
	
	public static String formatCompanyCity (String city) {
		return capitalizeAll(city.toLowerCase());
	}
}
