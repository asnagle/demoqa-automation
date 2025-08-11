package converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import utils.DateComponents;

public class DatePickerConverter implements ExcelValueConverter {

    private static final String[] SUPPORTED_FORMATS = { "dd-MM-yyyy", "dd/MM/yyyy" };

    @Override
    public Object convert(String dobStr) {
        return parse(dobStr);
    }

    /**
     * Parses a DOB string into DateComponents.
     * Supports formats: dd-MM-yyyy and dd/MM/yyyy
     */
    public static DateComponents parse(String dobStr) {
        Date date = parseDateStrict(dobStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return new DateComponents(
            cal.get(Calendar.DAY_OF_MONTH),
            cal.get(Calendar.MONTH) + 1, // Calendar.MONTH is 0-based
            cal.get(Calendar.YEAR)
        );
    }

    /**
     * Attempts to strictly parse the date string using supported formats.
     */
    private static Date parseDateStrict(String dobStr) {
        if (dobStr == null || dobStr.trim().isEmpty()) {
            throw new IllegalArgumentException("DOB string is null or empty");
        }

        for (String formatStr : SUPPORTED_FORMATS) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(formatStr);
                format.setLenient(false);
                return format.parse(dobStr.trim());
            } catch (ParseException ignored) {
                // Try next format
            }
        }

        throw new RuntimeException("Invalid DOB format. Expected dd-MM-yyyy or dd/MM/yyyy but got: " + dobStr);
    }
}