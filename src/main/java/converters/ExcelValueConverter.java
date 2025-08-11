package converters;

/**
 * A contract for converting raw Excel string values into sanitized or formatted objects.
 */
public interface ExcelValueConverter {
    Object convert(String rawValue);
}