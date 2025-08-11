package customAnnotations;

import converters.ExcelValueConverter;
import converters.NoOpConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Annotation to map Excel columns to POJO fields.
 * Supports default values and custom converters.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnMapping {

    /**
     * The name of the column header in the Excel sheet.
     */
    String name();

    /**
     * The default value to use if the cell is empty or missing.
     */
    String defaultValue() default "";

    /**
     * Optional converter to transform the raw Excel value into a target type.
     * Defaults to NoOpConverter which returns the raw string.
     */
    Class<? extends ExcelValueConverter> converter() default NoOpConverter.class;
}