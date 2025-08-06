package utils.mappers;

import java.lang.reflect.Field;
import java.util.Map;

public class ExcelToPOJOMapper {
	 public static <T> T mapRowToPojo(Class<T> clazz, Map<String, String> rowData) {
	        try {
	            T instance = clazz.getDeclaredConstructor().newInstance();

	            for (Field field : clazz.getDeclaredFields()) {
	                field.setAccessible(true); // allow access to private fields

	                String header = toHeader(field.getName()); // convert camelCase to Excel header
	                String value = rowData.getOrDefault(header, "");

	                try {
	                    field.set(instance, value); // set value via reflection
	                } catch (IllegalAccessException e) {
	                    System.err.println("Failed to set field: " + field.getName());
	                    e.printStackTrace();
	                }
	            }

	            return instance;

	        } catch (Exception e) {
	            throw new RuntimeException("Failed to map row to POJO: " + clazz.getSimpleName(), e);
	        }
	    }

	    // Converts camelCase to "Camel Case"
	    private static String toHeader(String fieldName) {
	        String spaced = fieldName.replaceAll("([a-z])([A-Z])", "$1 $2");
	        return spaced.substring(0, 1).toUpperCase() + spaced.substring(1);
	    }

}
