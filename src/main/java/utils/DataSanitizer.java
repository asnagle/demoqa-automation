package utils;

public class DataSanitizer {
	public static int parseSafeInt(String value, String fieldName, String userName) {
        try {
            return Integer.parseInt(value.trim().replaceAll("[^\\d]", ""));
        } catch (NumberFormatException e) {
            demoqaLog.error("Invalid " + fieldName + " format: '" + value + "' for user: " + userName);
            throw new RuntimeException("Invalid " + fieldName + " format for user: " + userName, e);
        }
    }

}
