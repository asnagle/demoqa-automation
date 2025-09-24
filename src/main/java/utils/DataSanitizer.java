package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import models.WebTableUser;

public class DataSanitizer {

	private static final List<DateTimeFormatter> KNOWN_DOB_FORMATS = Arrays.asList(
		    DateTimeFormatter.ofPattern("dd/MM/yyyy"),
		    DateTimeFormatter.ofPattern("MM/dd/yyyy"),
		    DateTimeFormatter.ofPattern("M/d/yy"),
		    DateTimeFormatter.ofPattern("yyyy-MM-dd"),
		    DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
		    DateTimeFormatter.ofPattern("dd-MM-yyyy"),
		    DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH),
		    DateTimeFormatter.ofPattern("d MMMM,yyyy", Locale.ENGLISH)
		);

    private static final DateTimeFormatter TARGET_DOB_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public static Optional<LocalDate> sanitizeDOBToDate(String rawValue, String fieldName, String context) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return Optional.empty();
        }

        String trimmed = rawValue.trim()
                                 .replace(",", " ")
                                 .replaceAll("\\s+", " ");

        for (DateTimeFormatter formatter : KNOWN_DOB_FORMATS) {
            try {
                LocalDate date = LocalDate.parse(trimmed, formatter);
                return Optional.of(date);
            } catch (DateTimeParseException ignored) {
            }
        }

        try {
            int excelDate = Integer.parseInt(trimmed);
            LocalDate date = LocalDate.of(1899, 12, 30).plusDays(excelDate);
            return Optional.of(date);
        } catch (NumberFormatException ignored) {
        }

        System.out.printf("❌ Unrecognized DOB format for %s [%s]: %s%n", fieldName, context, trimmed);
        return Optional.empty();
    }
    
    public static Optional<LocalDate> sanitizeDOBToDate(String rawValue) {
        return sanitizeDOBToDate(rawValue, "DOB", "AutoSanitizer");
    }
    

    // ✅ Core int parsing with fallback
//    public static int parseSafeInt(String value, String fieldName, String userName) {
//        try {
//            return Integer.parseInt(value.trim());
//        } catch (Exception e) {
//            System.err.printf("Invalid %s for user %s: %s%n", fieldName, userName, value);
//            return 0;
//        }
//    }
    
 // ✅ Core int parsing with fallback and ".0" normalization
    public static int parseSafeInt(String value, String fieldName, String userName) {
    	System.out.printf("🧪 parseSafeInt called for field '%s' with value: %s%n", fieldName, value);
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }

        String sanitized = value.trim();

        try {
            // If it looks like a decimal, parse as double then cast down
            if (sanitized.matches("^-?\\d+\\.\\d+$")) {
                double d = Double.parseDouble(sanitized);
                return (int) d; // 34.0 -> 34, 45.99 -> 45
            }

            return Integer.parseInt(sanitized);

        } catch (NumberFormatException e) {
            System.err.printf("Invalid %s for user %s: %s%n", fieldName, userName, sanitized);
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

    // ✅ DOB sanitization with expanded format support
    public static String sanitizeDOB(String rawValue, String fieldName, String context) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return "";
        }

        String trimmed = rawValue.trim()
                                 .replace(",", " ")
                                 .replaceAll("\\s+", " ");

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

    public static String sanitizeDOB(String rawValue) {
        return sanitizeDOB(rawValue, "DOB", "FormTests");
    }
    
    public static Optional<LocalTime> sanitizeTime(String raw, String label, String context) {
        if (raw == null || raw.trim().isEmpty()) {
            System.out.printf("⚠️ [%s] Empty time string for %s%n", context, label);
            return Optional.empty();
        }

        String cleaned = raw.trim()
                            .replace(".", ":")
                            .replaceAll("\\s+", " ")
                            .toUpperCase(Locale.ENGLISH);

        List<String> patterns = Arrays.asList(
            "h:mm a", "hh:mm a",         // 6:45 AM, 06:45 AM
            "h:mm:ss a", "hh:mm:ss a",   // 6:45:00 AM
            "H:mm", "HH:mm",             // 6:45 (24h)
            "H:mm:ss", "HH:mm:ss",       // 18:45:00
            "HHmm", "Hmm"                // 0645 or 645
        );

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                String toParse = cleaned;

                if (pattern.equals("HHmm") || pattern.equals("Hmm")) {
                    String digits = cleaned.replaceAll("\\D+", "");
                    if (digits.length() == 3) digits = "0" + digits; // 645 → 0645
                    toParse = digits;
                }

                LocalTime parsedTime = LocalTime.parse(toParse, formatter);
                System.out.printf("🧼 [%s] Parsed %s as %s using pattern '%s'%n", context, label, parsedTime, pattern);
                return Optional.of(parsedTime);
            } catch (DateTimeParseException ignored) {
                // Try next pattern
            }
        }

        System.err.printf("❌ [%s] Unrecognized time format for %s: %s%n", context, label, raw);
        return Optional.empty();
    }

    public static Optional<LocalDateTime> combineDateAndTime(String dateStr, String timeStr, String context) {
        Optional<LocalDate> dateOpt = sanitizeDOBToDate(dateStr, "DOB", context);
        Optional<LocalTime> timeOpt = sanitizeTime(timeStr, "DOBTime", context);

        if (dateOpt.isEmpty()) {
            System.err.printf("❌ [%s] Invalid or missing DOB: %s%n", context, dateStr);
            return Optional.empty();
        }

        if (timeOpt.isEmpty()) {
            if ("DATE_PICKER".equalsIgnoreCase(context)) {
                System.err.printf("❌ [%s] DOBTime is required but missing or invalid: %s%n", context, timeStr);
                return Optional.empty(); // enforce time presence for DATE_PICKER
            } else {
                System.out.printf("ℹ️ [%s] DOBTime missing, defaulting to midnight%n", context);
            }
        }

        LocalTime time = timeOpt.orElse(LocalTime.MIDNIGHT);
        return Optional.of(LocalDateTime.of(dateOpt.get(), time));
    }
    
    public static String normalizeText(String input) {
        return input
            .replaceAll("\\r?\\n", " ")
            .replaceAll("\\s+", " ")
            .trim();
    }

    // ✅ Picture sanitization: extract filename only
    public static String sanitizePicture(String rawValue, String fieldName, String context) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return "";
        }

        String trimmed = rawValue.trim();

        int lastSlash = Math.max(trimmed.lastIndexOf('/'), trimmed.lastIndexOf('\\'));
        if (lastSlash != -1 && lastSlash < trimmed.length() - 1) {
            trimmed = trimmed.substring(lastSlash + 1);
        }

        return normalizeText(trimmed);
    }
    
    private static String sanitizeList(String value) {
        return value.replaceAll("\\s*,\\s*", ",").trim(); // removes spaces around commas
    }

    // ✅ Field-aware sanitization with alias support
    public static String sanitizeField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) return "";

        String key = fieldName.trim().toLowerCase();

        switch (key) {
            case "dob":
            case "date of birth":
                return sanitizeDOB(value, fieldName, "AutoSanitizer");

            case "mobile":
            case "phone":
            case "contact":
                return sanitizeMobile(value, fieldName, "AutoSanitizer");

            case "picture":
            case "photo":
            case "image":
            case "upload":
            case "file":
            case "attachment":
                return sanitizePicture(value, fieldName, "AutoSanitizer");

            case "hobbies":
            case "subjects":
                return sanitizeList(value);

            default:
                return normalizeText(value);
        }
    }
}