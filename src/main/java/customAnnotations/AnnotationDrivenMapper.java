package customAnnotations;

import java.lang.reflect.Field;
import java.util.Map;

public class AnnotationDrivenMapper {

    public static <T> T mapRowToPOJO(Map<String, String> rowMap, Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(ColumnMapping.class)) continue;

                ColumnMapping mapping = field.getAnnotation(ColumnMapping.class);
                String headerKey = mapping.name().trim();
                String rawValue = rowMap.getOrDefault(headerKey, "").trim();

                System.out.println("🔍 Mapping field: " + field.getName() +
                                   " | Excel Column: " + headerKey +
                                   " | Value: " + rawValue);

                if (!rawValue.isEmpty()) {
                    field.setAccessible(true);
                    Object converted = convertValue(rawValue, field.getType());
                    field.set(instance, converted);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to map row to POJO: " + clazz.getSimpleName(), e);
        }
    }

    private static Object convertValue(String value, Class<?> type) {
        try {
            if (type == int.class || type == Integer.class)
                return Integer.parseInt(value.replaceAll("[^\\d]", ""));
            if (type == double.class || type == Double.class)
                return Double.parseDouble(value.replaceAll("[^\\d.]", ""));
            if (type == boolean.class || type == Boolean.class)
                return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            System.err.println("⚠️ Failed to convert value: '" + value + "' to type: " + type.getSimpleName());
        }
        return value.trim();
    }
}