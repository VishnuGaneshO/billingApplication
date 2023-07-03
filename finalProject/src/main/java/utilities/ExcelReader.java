package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String getLogin(int a, int b) {

		FileInputStream file = null;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		String value = null;

		try {
			file = new FileInputStream(new File("src/test/resources/excelData/login.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(a);
//			int noOfRows=sheet.getPhysicalNumberOfRows();
			XSSFCell cell = row.getCell(b);
			// Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.ge
			switch (cell.getCellType()) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				value = String.valueOf(cell.getNumericCellValue());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}

	public String getUserDetails(int a, int b) {

		FileInputStream file = null;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		String value = null;

		try {
			file = new FileInputStream(new File("src/test/resources/excelData/userDetails.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(a);
//			int noOfRows=sheet.getPhysicalNumberOfRows();
			XSSFCell cell = row.getCell(b);
			// Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.ge
			switch (cell.getCellType()) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				value = String.valueOf(cell.getNumericCellValue());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}
	
	public String getExpenseDetails(int a, int b) {

		FileInputStream file = null;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		String value = null;

		try {
			file = new FileInputStream(new File("src/test/resources/excelData/expenseDetails.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(a);
//			int noOfRows=sheet.getPhysicalNumberOfRows();
			XSSFCell cell = row.getCell(b);
			// Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.ge
			switch (cell.getCellType()) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				value = String.valueOf(cell.getNumericCellValue());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}
	
	
	public String getContactDetails(int a, int b) {

		FileInputStream file = null;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		String value = null;

		try {
			file = new FileInputStream(new File("src/test/resources/excelData/contactDetails.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(a);
//			int noOfRows=sheet.getPhysicalNumberOfRows();
			XSSFCell cell = row.getCell(b);
			// Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.ge
			switch (cell.getCellType()) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				value = String.valueOf(cell.getNumericCellValue());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}

}
