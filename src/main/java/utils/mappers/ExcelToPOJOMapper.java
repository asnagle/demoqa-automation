package utils.mappers;

import customAnnotations.ColumnMapping;
import converters.ExcelValueConverter;
import converters.NoOpConverter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelToPOJOMapper {

    public static <T> List<T> mapRowsToPOJOs(List<List<String>> excelData, Class<T> clazz) {
        List<T> pojoList = new ArrayList<>();
        List<String> headers = excelData.get(0);

        for (int i = 1; i < excelData.size(); i++) {
            List<String> row = excelData.get(i);
            try {
                T instance = clazz.getDeclaredConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    mapFieldValue(instance, field, row, headers);
                }

                pojoList.add(instance);
            } catch (Exception e) {
                throw new RuntimeException("Failed to map row to POJO: " + e.getMessage(), e);
            }
        }

        return pojoList;
    }

    private static <T> void mapFieldValue(T instance, Field field, List<String> row, List<String> headers) throws Exception {
        System.out.println("🔍 Inspecting field: " + field.getName());

        ColumnMapping mapping = field.getAnnotation(ColumnMapping.class);
        if (mapping == null) {
            System.out.println("⏭️ Skipping field (no annotation)");
            return;
        }

        String headerName = mapping.name();
        String defaultValue = mapping.defaultValue();

        int columnIndex = headers.indexOf(headerName);
        String rawValue = (columnIndex != -1 && columnIndex < row.size())
            ? row.get(columnIndex)
            : defaultValue;
        
        System.out.println("🧪 Field: " + field.getName());
        ColumnMapping mapping2 = field.getAnnotation(ColumnMapping.class);
        System.out.println("Annotation present: " + (mapping2 != null));
        if (mapping2 != null) {
            System.out.println("Converter: " + mapping2.converter().getSimpleName());
        }

        Object resolvedValue = resolveValue(rawValue, field, mapping);

//        Object resolvedValue = resolveValue(rawValue, field, mapping);

        System.out.printf("Field: %-15s | Expected: %-15s | Raw: %-12s | Resolved: %-15s%n",
            field.getName(),
            field.getType().getSimpleName(),
            rawValue,
            resolvedValue != null ? resolvedValue.getClass().getSimpleName() : "null"
        );

        if (resolvedValue != null && !field.getType().isAssignableFrom(resolvedValue.getClass())) {
            throw new RuntimeException("Type mismatch: cannot assign " + resolvedValue.getClass().getSimpleName()
                + " to field " + field.getName() + " of type " + field.getType().getSimpleName());
        }

        field.setAccessible(true);
        field.set(instance, resolvedValue);
    }

    private static Object resolveValue(String cellValue, Field field, ColumnMapping annotation) throws Exception {
        Class<? extends ExcelValueConverter> converterClass = annotation.converter();

        if (!converterClass.equals(NoOpConverter.class)) {
            ExcelValueConverter converter = converterClass.getDeclaredConstructor().newInstance();
            Object converted = converter.convert(cellValue);

            if (converted != null && !field.getType().isAssignableFrom(converted.getClass())) {
                throw new RuntimeException("Converter returned " + converted.getClass().getSimpleName()
                    + " but field " + field.getName() + " expects " + field.getType().getSimpleName());
            }

            return converted;
        }

        return convertValue(cellValue, field.getType());
    }

    private static Object convertValue(String cellValue, Class<?> targetType) {
        if (targetType == String.class) return cellValue;
        if (targetType == int.class || targetType == Integer.class) return Integer.parseInt(cellValue);
        if (targetType == double.class || targetType == Double.class) return Double.parseDouble(cellValue);
        if (targetType == boolean.class || targetType == Boolean.class) return Boolean.parseBoolean(cellValue);

        throw new RuntimeException("No converter provided for non-primitive type: " + targetType.getSimpleName());
    }
}