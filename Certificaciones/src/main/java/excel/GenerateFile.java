package excel;

import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.Codes;
import data.DataOC;

public class GenerateFile {
	
	public void CreateExcelFile(ArrayList<Codes> outcomeCodes, ArrayList<DataOC> studentsData) {
		XSSFWorkbook workbook = new XSSFWorkbook(); // Workbook is created... SHOULD I NAME IT?
		
		// MAKE A FOR STATEMENT TO CREATE MULTIPLE SHEETS
		for(int i = 0; i <= 7; i++) {
			XSSFSheet spreadsheet = workbook.createSheet("Outcome " + (i + 1)); // Name of the Current Outcome
			
		}
		
	}
}
