package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    // 🔄 Load Excel File and Sheet
    public static void loadExcel(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);
    }
    
    public static Sheet getSheet(String sheetName) {
        Sheet targetSheet = workbook.getSheet(sheetName);
        if (targetSheet == null) throw new RuntimeException("Sheet not found: " + sheetName);
        return targetSheet;
    }

    // 📊 Get Cell Data by Row and Column Index
    public static String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) return "";

        Cell cell = row.getCell(colIndex);
        if (cell == null) return "";

        return formatCellValue(cell);
    }

    // 🧠 Format Cell Value to String
    private static String formatCellValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (DateUtil.isCellDateFormatted(cell)) {
            Date date = cell.getDateCellValue();
            return new SimpleDateFormat("dd-MM-yyyy").format(date);
        } else if (cell.getCellType() == CellType.NUMERIC) {
            double num = cell.getNumericCellValue();
            return (num == (int) num) ? String.valueOf((int) num) : String.valueOf(num);
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            return cell.getCellFormula();
        }
        return "";
    }

    // 📋 Get Total Row Count
    public static int getRowCount() {
        return sheet != null ? sheet.getPhysicalNumberOfRows() : 0;
    }

    // 🧾 Get Cell Data by Header Name
    public static String getCellDataByHeader(int rowIndex, String headerName) {
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) throw new RuntimeException("Header row is missing");

        int colIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().trim().equalsIgnoreCase(headerName)) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        if (colIndex == -1) {
            System.out.println("Header '" + headerName + "' not found.");
            return "";
        }

        return getCellData(rowIndex, colIndex);
    }
    
    public static int findHeaderRowIndex(Sheet sheet) {
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            for (Cell cell : row) {
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String value = cell.getStringCellValue().trim();
                    if (value.equalsIgnoreCase("First Name")) {
                        return i;
                    }
                }
            }
        }
        throw new RuntimeException("Header row not found");
    }
    

    // 🗂️ Get Entire Row as Map<Header, Value>
    public static Map<String, String> getRowDataAsMap(String sheetName, int dataRowIndex) {
        Sheet targetSheet = workbook.getSheet(sheetName);
        if (targetSheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

        int headerRowIndex = findHeaderRowIndex(targetSheet);
        Row headerRow = targetSheet.getRow(headerRowIndex);
        Row dataRow = targetSheet.getRow(dataRowIndex);

        if (headerRow == null || dataRow == null) throw new RuntimeException("Missing header or data row");

        Map<String, String> rowData = new HashMap<>();
        int cellCount = headerRow.getLastCellNum();

        for (int i = 0; i < cellCount; i++) {
            Cell headerCell = headerRow.getCell(i);
            Cell valueCell = dataRow.getCell(i);

            if (headerCell == null) continue;

            String header = headerCell.toString().trim();
            String value = (valueCell != null) ? formatCellValue(valueCell) : "";

            rowData.put(header, value);
        }

        return rowData;
    }

    // ❌ Close Workbook
    public static void closeExcel() throws IOException {
        if (workbook != null) workbook.close();
    }
}