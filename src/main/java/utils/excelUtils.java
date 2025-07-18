package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {
	private static Workbook workbook;
	private static Sheet sheet;

//	Load Excel File
	public static void loadExcel(String filePath, String sheetName) throws IOException {

		FileInputStream file = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sheetName);
	}

//	Get Cell Data as String
	public static String getCellData(int row, int col) {

		Cell cell = sheet.getRow(row).getCell(col);
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			Date date = cell.getDateCellValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // customize format if needed
			return sdf.format(date);
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf((int) cell.getNumericCellValue());
		}
		return "";
	}

//	Get Row Count
	public static int getRowCount() {

		return sheet.getPhysicalNumberOfRows();

	}

//	Close the Workbook
	public static void closeExcel() throws IOException {

		workbook.close();
	}

}
