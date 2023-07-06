package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	@DataProvider(name = "invalidloginData")
	public Object[][] invalidLoginDataProvider() throws IOException {

		// We are creating an object from the excel sheet data by calling a method that
		// reads data from the excel stored locally in our system
		Object[][] arrObj = getExcelData(
				"C:\\Users\\vishn\\git\\finalProject\\finalProject\\src\\test\\resources\\excelData\\invalidLoginData.xlsx",
				"Sheet1");
		return arrObj;
	}

	@DataProvider(name = "contactsData")
	public Object[][] contactsDataProvider() throws IOException {

		// We are creating an object from the excel sheet data by calling a method that
		// reads data from the excel stored locally in our system
		Object[][] arrObj = getExcelData(
				"C:\\Users\\vishn\\git\\finalProject\\finalProject\\src\\test\\resources\\excelData\\contactData.xlsx",
				"Sheet1");
		return arrObj;
	}
	
	@DataProvider(name = "userData")
	public Object[][] userDataProvider() throws IOException {

		// We are creating an object from the excel sheet data by calling a method that
		// reads data from the excel stored locally in our system
		Object[][] arrObj = getExcelData(
				"C:\\Users\\vishn\\git\\finalProject\\finalProject\\src\\test\\resources\\excelData\\userData.xlsx",
				"Sheet1");
		return arrObj;
	}
	
	@DataProvider(name = "expenseData")
	public Object[][] expenseDataProvider() throws IOException {

		// We are creating an object from the excel sheet data by calling a method that
		// reads data from the excel stored locally in our system
		Object[][] arrObj = getExcelData(
				"C:\\Users\\vishn\\git\\finalProject\\finalProject\\src\\test\\resources\\excelData\\expenseData.xlsx",
				"Sheet1");
		return arrObj;
	}

	public String[][] getExcelData(String fileName, String sheetName) throws IOException {
		String[][] data = null;
		String value = null;
		int num;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row;
			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCols = sheet.getRow(0).getLastCellNum();
			data = new String[noOfRows - 1][noOfCols];

			for (int i = 1; i < noOfRows; i++) {
				row = sheet.getRow(i);
				boolean isEmptyRow = true; // Flag to indicate if the row is empty

				for (int j = 0; j < noOfCols; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							break;
						case NUMERIC:
							num = (int) cell.getNumericCellValue();
							value = String.valueOf(num);
							break;
						default:
							break;
						}
						System.out.println(value);
						data[i - 1][j] = value;
						isEmptyRow = false; // Row contains data, so set the flag to false
					}
				}

				if (isEmptyRow) {
					break; // Break out of the loop if an empty row is encountered
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
