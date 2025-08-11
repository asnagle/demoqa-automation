package converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DobConverter implements ExcelValueConverter {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public Object convert(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            throw new IllegalArgumentException("Date of Birth cell is empty in Excel");
        }

        rawValue = rawValue.trim();

        try {
            LocalDate dob;

            // Case 1: Excel numeric date
            if (rawValue.matches("\\d+")) {
                int excelDate = Integer.parseInt(rawValue);
                dob = LocalDate.of(1899, 12, 30).plusDays(excelDate);
            } else {
                // Case 2: String date in dd/MM/yyyy
                dob = LocalDate.parse(rawValue, INPUT_FORMAT);
            }

            // Return formatted string in dd-MM-yyyy
            return dob.format(OUTPUT_FORMAT);

        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid DOB format in Excel: " + rawValue, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid numeric DOB in Excel: " + rawValue, e);
        }
    }
}