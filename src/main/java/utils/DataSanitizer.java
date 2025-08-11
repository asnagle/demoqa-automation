package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import models.WebTableUser;

public class DataSanitizer {

    private static final List<DateTimeFormatter> KNOWN_DOB_FORMATS = Arrays.asList(
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("MM/dd/yyyy"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy")
    );

    private static final DateTimeFormatter TARGET_DOB_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // ✅ Core int parsing with fallback
    public static int parseSafeInt(String value, String fieldName, String userName) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            System.err.printf("Invalid %s for user %s: %s%n", fieldName, userName, value);
            return 0;
        }
    }

    // ✅ Overloads for flexibility
    public static int parseSafeInt(int value, String fieldName, String userName) {
        return value;
    }

    public static int parseSafeInt(String value, String fieldName, WebTableUser user) {
        String userName = (user != null) ? user.getFirstName() : "Unknown User";
        return parseSafeInt(value, fieldName, userName);
    }

    public static int parseSafeInt(int value, String fieldName, WebTableUser user) {
        String userName = (user != null) ? user.getFirstName() : "Unknown User";
        return parseSafeInt(value, fieldName, userName);
    }

    // ✅ Mobile sanitization with scientific notation handling
    public static String sanitizeMobile(String rawValue, String fieldName, String context) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return "";
        }

        String trimmed = rawValue.trim();

        if (trimmed.matches("^[0-9.]+E[0-9]+$")) {
            try {
                double scientific = Double.parseDouble(trimmed);
                trimmed = String.valueOf((long) scientific);
            } catch (NumberFormatException e) {
                System.out.printf("Failed to parse scientific notation for %s [%s]: %s%n", fieldName, context, rawValue);
            }
        }

        trimmed = trimmed.replaceAll("\\D", "");

        if (trimmed.length() != 10) {
            System.out.printf("Sanitized mobile number is invalid for %s [%s]: %s%n", fieldName, context, trimmed);
        }

        return trimmed;
    }

    // ✅ DOB sanitization with multiple format support
    public static String sanitizeDOB(String rawValue, String fieldName, String context) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return "";
        }

        String trimmed = rawValue.trim();

        for (DateTimeFormatter formatter : KNOWN_DOB_FORMATS) {
            try {
                LocalDate date = LocalDate.parse(trimmed, formatter);
                return date.format(TARGET_DOB_FORMAT);
            } catch (DateTimeParseException ignored) {
            }
        }

        try {
            int excelDate = Integer.parseInt(trimmed);
            LocalDate date = LocalDate.of(1899, 12, 30).plusDays(excelDate);
            return date.format(TARGET_DOB_FORMAT);
        } catch (NumberFormatException ignored) {
        }

        System.out.printf("Unrecognized DOB format for %s [%s]: %s%n", fieldName, context, trimmed);
        return trimmed;
    }

    // ✅ Overload for simpler usage in tests
    public static String sanitizeDOB(String rawValue) {
        return sanitizeDOB(rawValue, "DOB", "FormTests");
    }
}