package utils;

import org.apache.poi.ss.usermodel.*;
import customAnnotations.AnnotationDrivenMapper;
import models.WebTableUser;
import java.io.*;
import java.util.*;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    public void loadExcel(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public void closeExcel() throws IOException {
        if (workbook != null)
            workbook.close();
    }

    public Sheet getSheet(String sheetName) {
        return workbook.getSheet(sheetName);
    }

    public int findHeaderRowIndex(Sheet sheet) {
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row != null && row.getCell(0) != null)
                return i;
        }
        return 0;
    }

    public Map<String, String> getRowDataAsMap(String sheetName, int rowIndex) {
        Sheet sheet = getSheet(sheetName);
        int headerRowIndex = findHeaderRowIndex(sheet);
        Row headerRow = sheet.getRow(headerRowIndex);
        Row dataRow = sheet.getRow(rowIndex);

        Map<String, String> rowMap = new HashMap<>();
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String header = headerRow.getCell(i).toString().trim();
            String value = dataRow.getCell(i) != null ? dataRow.getCell(i).toString().trim() : "";
            rowMap.put(header, value);
        }
        return rowMap;
    }

    private Map<String, Integer> getHeaderIndexMap(Sheet sheet) {
        int headerRowIndex = findHeaderRowIndex(sheet);
        Row headerRow = sheet.getRow(headerRowIndex);
        Map<String, Integer> headerMap = new HashMap<>();

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String header = headerRow.getCell(i).toString().trim();
            headerMap.put(header, i);
        }

        return headerMap;
    }

    public static Object[][] getUsersFromExcel(String filePath, String sheetName) {
        ExcelUtils excelUtils = new ExcelUtils();
        try {
            excelUtils.loadExcel(filePath, sheetName);
            Sheet sheet = excelUtils.getSheet(sheetName);
            int headerRowIndex = excelUtils.findHeaderRowIndex(sheet);
            Map<String, Integer> headerMap = excelUtils.getHeaderIndexMap(sheet);

            List<Object[]> userDataList = new ArrayList<>();
            for (int i = headerRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = excelUtils.getRowDataAsMap(sheetName, i);

                if (rowData != null && !rowData.isEmpty()) {
                    String normalizedDob = DateUtils.getDobFromExcelCell(row, headerMap);
                    if (normalizedDob != null && !normalizedDob.isEmpty()) {
                        rowData.put("DOB", normalizedDob); // overwrite raw value
                    }

                    WebTableUser user = AnnotationDrivenMapper.mapRowToPOJO(rowData, WebTableUser.class);
                    userDataList.add(new Object[] { user });
                }
            }

            return userDataList.toArray(new Object[0][]);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read users from Excel", e);
        } finally {
            try {
                excelUtils.closeExcel();
            } catch (IOException e) {
                // Optional: log or suppress
            }
        }
    }

    public static List<WebTableUser> getUserListFromExcel(String filePath, String sheetName) {
        ExcelUtils excelUtils = new ExcelUtils();
        List<WebTableUser> users = new ArrayList<>();

        try {
            excelUtils.loadExcel(filePath, sheetName);
            Sheet sheet = excelUtils.getSheet(sheetName);
            int headerRowIndex = excelUtils.findHeaderRowIndex(sheet);
            Map<String, Integer> headerMap = excelUtils.getHeaderIndexMap(sheet);

            for (int i = headerRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = excelUtils.getRowDataAsMap(sheetName, i);

                if (rowData != null && !rowData.isEmpty()) {
                    String normalizedDob = DateUtils.getDobFromExcelCell(row, headerMap);
                    if (normalizedDob != null && !normalizedDob.isEmpty()) {
                        rowData.put("DOB", normalizedDob);
                    }

                    WebTableUser user = AnnotationDrivenMapper.mapRowToPOJO(rowData, WebTableUser.class);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read users from Excel", e);
        } finally {
            try {
                excelUtils.closeExcel();
            } catch (IOException ignored) {
            }
        }

        return users;
    }

    public static WebTableUser getFirstUserFromExcel(String filePath, String sheetName) {
        ExcelUtils excelUtils = new ExcelUtils();

        try {
            excelUtils.loadExcel(filePath, sheetName);
            Sheet sheet = excelUtils.getSheet(sheetName);
            int headerRowIndex = excelUtils.findHeaderRowIndex(sheet);
            Map<String, Integer> headerMap = excelUtils.getHeaderIndexMap(sheet);

            Row row = sheet.getRow(headerRowIndex + 1);
            Map<String, String> rowData = excelUtils.getRowDataAsMap(sheetName, headerRowIndex + 1);

            if (rowData != null && !rowData.isEmpty()) {
                String normalizedDob = DateUtils.getDobFromExcelCell(row, headerMap);
                if (normalizedDob != null && !normalizedDob.isEmpty()) {
                    rowData.put("DOB", normalizedDob);
                }

                return AnnotationDrivenMapper.mapRowToPOJO(rowData, WebTableUser.class);
            } else {
                throw new RuntimeException("First data row is empty or invalid");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read first user from Excel", e);
        } finally {
            try {
                excelUtils.closeExcel();
            } catch (IOException ignored) {
            }
        }
    }
    
    public static <T> Object[][] getMappedData(String filePath, String sheetName, Class<T> clazz) {
        ExcelUtils excelUtils = new ExcelUtils();
        try {
            excelUtils.loadExcel(filePath, sheetName);
            Sheet sheet = excelUtils.getSheet(sheetName);
            int headerRowIndex = excelUtils.findHeaderRowIndex(sheet);
            Map<String, Integer> headerMap = excelUtils.getHeaderIndexMap(sheet);

            List<Object[]> data = new ArrayList<>();
            for (int i = headerRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowMap = excelUtils.getRowDataAsMap(sheetName, i);

                if (rowMap != null && !rowMap.isEmpty()) {
                    // Normalize DOB if present
                    String normalizedDob = DateUtils.getDobFromExcelCell(row, headerMap);
                    if (normalizedDob != null && !normalizedDob.isEmpty()) {
                        rowMap.put("DOB", normalizedDob);
                    }

                    T obj = AnnotationDrivenMapper.mapRowToPOJO(rowMap, clazz);
                    data.add(new Object[] { obj });
                }
            }

            return data.toArray(new Object[0][]);
        } catch (IOException e) {
            throw new RuntimeException("Failed to map data from Excel", e);
        } finally {
            try {
                excelUtils.closeExcel();
            } catch (IOException ignored) {
            }
        }
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

}