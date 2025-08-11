package customAnnotations;

import java.lang.reflect.Field;
import java.util.Map;

public class AnnotationDrivenMapper {

    public static <T> T mapRowToPOJO(Map<String, String> rowMap, Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(ColumnMapping.class)) {
                    ColumnMapping mapping = field.getAnnotation(ColumnMapping.class);
                    String header = mapping.name();
                    String value = rowMap.get(header);

                    if (value != null) {
                        field.setAccessible(true);
                        field.set(instance, convertValue(value, field.getType()));
                    }
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map row to POJO: " + e.getMessage(), e);
        }
    }

    private static Object convertValue(String value, Class<?> type) {
        if (type == int.class || type == Integer.class) return Integer.parseInt(value.replaceAll("[^\\d]", ""));
        if (type == double.class || type == Double.class) return Double.parseDouble(value.replaceAll("[^\\d.]", ""));
        if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
        return value.trim();
    }
}