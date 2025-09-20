package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import enums.TestContext;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateUtils {

    private DateUtils() {}

    public static String getMonthName(String monthNumber) {
        int month = Integer.parseInt(monthNumber);
        String name = java.time.Month.of(month).name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }

    public static DateParts extractDateParts(String dateStr) {
        String[] parts = dateStr.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new DateParts(day, month, year);
    }

    public static String getDobFromExcelCell(Row row, Map<String, Integer> headerMap) {
        int dobIndex = headerMap.getOrDefault("DOB", -1);
        if (dobIndex == -1) throw new IllegalArgumentException("DOB column not found");

        Cell cell = row.getCell(dobIndex);
        if (cell == null) return "";

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            Date date = cell.getDateCellValue();
            return new SimpleDateFormat("dd-MM-yyyy").format(date);
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else {
            return "";
        }
    }
    
    public static DateTimeFormatter getFormatterForContext(TestContext context) {
        return switch (context) {
            case FORMS -> DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            case DATE_PICKER -> DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
            case DATE_TIME_PICKER -> DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH); // Example format
            default -> throw new IllegalArgumentException("Unhandled TestContext: " + context);
        };
    }
}