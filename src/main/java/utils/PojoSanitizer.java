package utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import customAnnotations.ColumnMapping;
import utils.DataSanitizer;


public class PojoSanitizer {
	private static final Logger log = LoggerFactory.getLogger(PojoSanitizer.class);

    public static Map<String, String> extractSanitizedFields(Object pojo) {
        Map<String, String> expected = new HashMap<>();
        Class<?> clazz = pojo.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            ColumnMapping mapping = field.getAnnotation(ColumnMapping.class);
            if (mapping == null) continue;

            field.setAccessible(true);
            try {
                Object rawValue = field.get(pojo);
                String sanitized = DataSanitizer.sanitizeField(
                    rawValue != null ? rawValue.toString() : "",
                    mapping.name()
                );

                log.debug("📦 Field='{}', Raw='{}', Sanitized='{}'", field.getName(), rawValue, sanitized);
                expected.put(mapping.name(), sanitized);
            } catch (IllegalAccessException e) {
                log.error("🚫 Failed to access field '{}': {}", field.getName(), e.getMessage());
                throw new RuntimeException("Failed to access field: " + field.getName(), e);
            }
        }

        return expected;
    }


}
