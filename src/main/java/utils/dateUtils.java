package utils;

import java.text.DateFormatSymbols;

public class dateUtils {

	public static String getMonthName(String monthNumber) {
		int month = Integer.parseInt(monthNumber);
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	public static boolean isValidDobFormat(String dobStr) {
		return dobStr != null && dobStr.matches("\\d{2}-\\d{2}-\\d{4}");
	}

}
