package utils;

import org.apache.poi.ss.usermodel.*;
import customAnnotations.AnnotationDrivenMapper;
import models.WebTableUser;
import java.io.*;
import java.util.*;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    // ────────────────────────────────
    // 📥 Load & Close Excel
    // ────────────────────────────────
    public void loadExcel(String filePath, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new IllegalArgumentException("❌ Sheet '" + sheetName + "' not found.");
        }
    }

    public void closeExcel() throws IOException {
        if (workbook != null) workbook.close();
    }

    // ────────────────────────────────
    // 📊 Sheet & Header Utilities
    // ────────────────────────────────
    private int findHeaderRowIndex(Sheet sheet) {
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row != null && row.getCell(0) != null) return i;
        }
        return 0;
    }

    private Map<String, Integer> getHeaderIndexMap(Sheet sheet) {
        int headerRowIndex = findHeaderRowIndex(sheet);
        Row headerRow = sheet.getRow(headerRowIndex);
        Map<String, Integer> headerMap = new HashMap<>();

        for (Cell cell : headerRow) {
            headerMap.put(cell.toString().trim(), cell.getColumnIndex());
        }
        return headerMap;
    }

    // ────────────────────────────────
    // 🧾 Row Mapping
    // ────────────────────────────────
    public Map<String, String> getRowDataAsMap(String sheetName, int rowIndex) {
        Sheet sheet = workbook.getSheet(sheetName);
        int headerRowIndex = findHeaderRowIndex(sheet);
        Row headerRow = sheet.getRow(headerRowIndex);
        Row dataRow = sheet.getRow(rowIndex);

        if (headerRow == null || dataRow == null) return Collections.emptyMap();

        Map<String, String> rowMap = new HashMap<>();
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String header = Optional.ofNullable(headerRow.getCell(i)).map(Cell::toString).orElse("").trim();
            String value = Optional.ofNullable(dataRow.getCell(i)).map(Cell::toString).orElse("").trim();
            rowMap.put(header, value);
        }

        System.out.println("🧾 Row " + rowIndex + ": " + rowMap);
        return rowMap;
    }

    private static boolean hasField(Class<?> clazz, String fieldName) {
        return Arrays.stream(clazz.getDeclaredFields())
                     .anyMatch(field -> field.getName().equals(fieldName));
    }

    private static <T> T mapRow(Map<String, String> rowMap, Row row, Map<String, Integer> headerMap, Class<T> clazz) {
        if (hasField(clazz, "DOB") && rowMap.containsKey("DOB")) {
            String normalizedDob = DateUtils.getDobFromExcelCell(row, headerMap);
            if (normalizedDob != null && !normalizedDob.isEmpty()) {
                rowMap.put("DOB", normalizedDob);
            }
        }
        return AnnotationDrivenMapper.mapRowToPOJO(rowMap, clazz);
    }

    // ────────────────────────────────
    // 🔄 Generic Mapping
    // ────────────────────────────────
    public static <T> Object[][] getMappedData(String filePath, String sheetName, Class<T> clazz) {
        ExcelUtils excelUtils = new ExcelUtils();
        List<Object[]> data = new ArrayList<>();

        try {
            excelUtils.loadExcel(filePath, sheetName);
            Sheet sheet = excelUtils.sheet;
            int headerRowIndex = excelUtils.findHeaderRowIndex(sheet);
            Map<String, Integer> headerMap = excelUtils.getHeaderIndexMap(sheet);

            for (int i = headerRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowMap = excelUtils.getRowDataAsMap(sheetName, i);
                if (rowMap.isEmpty()) {
                    System.err.println("⚠️ Skipping empty row at index " + i);
                    continue;
                }

                try {
                    T obj = mapRow(rowMap, row, headerMap, clazz);
                    data.add(new Object[] { obj });
                } catch (Exception e) {
                    System.err.println("❌ Failed to map row " + i + ": " + rowMap);
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file", e);
        } finally {
            try {
                excelUtils.closeExcel();
            } catch (IOException ignored) {}
        }

        return data.toArray(new Object[0][]);
    }

    public static <T> List<T> getMappedList(String filePath, String sheetName, Class<T> clazz) {
        Object[][] rawData = getMappedData(filePath, sheetName, clazz);
        List<T> result = new ArrayList<>();

        for (Object[] row : rawData) {
            if (row.length > 0 && clazz.isInstance(row[0])) {
                result.add(clazz.cast(row[0]));
            }
        }
        return result;
    }

    // ────────────────────────────────
    // 👤 WebTableUser Shortcuts
    // ────────────────────────────────
    public static Object[][] getUsersFromExcel(String filePath, String sheetName) {
        return getMappedData(filePath, sheetName, WebTableUser.class);
    }

    public static List<WebTableUser> getUserListFromExcel(String filePath, String sheetName) {
        Object[][] rawData = getUsersFromExcel(filePath, sheetName);
        List<WebTableUser> users = new ArrayList<>();

        for (Object[] row : rawData) {
            if (row.length > 0 && row[0] instanceof WebTableUser) {
                users.add((WebTableUser) row[0]);
            }
        }
        return users;
    }

    public static WebTableUser getFirstUserFromExcel(String filePath, String sheetName) {
        List<WebTableUser> users = getUserListFromExcel(filePath, sheetName);
        if (users.isEmpty()) throw new RuntimeException("First data row is empty or invalid");
        return users.get(0);
    }
}