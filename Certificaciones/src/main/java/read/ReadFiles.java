 /*
  * This Class Reads and Process All Files Previously Retrieved From the 'GetFiles' class
 */

package read;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.Codes;


public class ReadFiles {

	private void ReadFiles(DefaultListModel<File> model) throws IOException {
		// Start Reading the File

		// 1. Files
		for(int i = 0; i < model.getSize(); i++) {
			// Is Important to Know the # of Workbooks
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(model.getElementAt(i).toString()));
			// 2. Sheets Within Workbooks are Read
			for(int j = 0; j < workbook.getNumberOfSheets(); j++) {
				XSSFSheet sheet = workbook.getSheetAt(j);
				
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				while(rowIterator.hasNext()) {
					Row row = rowIterator.next();
					
					// For each row, iterate through all the collumns
					Iterator<Cell> cellIterator = row.cellIterator();
					
					while(cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
						int code; // Código de la Materia
						String subjectName; // Nombre de la Actifidad Fundamental a Revisar
						ArrayList<String> outcomes;
						
						// Check the cell type and format accordingly
						switch(cell.getCellType()) {
							case NUMERIC:
	                            System.out.print(cell.getNumericCellValue() + "\t");
								break;
							case STRING:
	                            System.out.print(cell.getStringCellValue() + "\t");
								break;
						}
					}
				}
			}
		}
		
	}
	
	private void OutcomesList(DefaultListModel<File> model) throws Exception {
		ArrayList<Codes> items = new ArrayList<Codes>();
		// Start Reading the File

		// 1. File
		// Is Important to Know the # of Workbooks
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(model.getElementAt(0).toString()));
		// 2. Sheets Within Workbooks are Read
		for(int j = 0; j < workbook.getNumberOfSheets(); j++) {
			XSSFSheet sheet = workbook.getSheetAt(j);
			int count = 0;
			
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				String retrieve_codes = ""; // Código de la Materia
				String retrieve_subjectName = ""; // Nombre de la Actifidad Fundamental a Revisar
				ArrayList<String> retrieve_outcomes = new ArrayList<String>(); // Outcomes a tomar en cuenta
					
				// For each row, iterate through all the collumns
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					// Check the cell type and format accordingly
					switch(cell.getCellType()) {
						case NUMERIC:
							XSSFCell first_row = sheet.getRow(0).getCell(cell.getColumnIndex()); // Get cell of first Row of current Column
                            retrieve_outcomes.add(first_row.toString());
                            
							break;
						case STRING:
							// Validate if this File format is correct.
							if(cell.getRowIndex() == 0
									&& !(cell.getStringCellValue().equals("Unidades de Aprendizaje") 
												|| cell.getStringCellValue().equals("Outcome 1")
												|| cell.getStringCellValue().equals("Outcome 2")
												|| cell.getStringCellValue().equals("Outcome 3")
												|| cell.getStringCellValue().equals("Outcome 4")
												|| cell.getStringCellValue().equals("Outcome 5")
												|| cell.getStringCellValue().equals("Outcome 6")
												|| cell.getStringCellValue().equals("Outcome 7"))) {
								throw new Exception(); // The format is not correct
							} else if(cell.getRowIndex() >= 1 && cell.getColumnIndex() == 0) {
								String[] arrOfStr = cell.getStringCellValue().split(" ", 2);
								retrieve_codes = arrOfStr[0];
								retrieve_subjectName = arrOfStr[1];
								
							}
							
							break;
						default:
					}
				}
				Codes dataC = new Codes(retrieve_codes, retrieve_subjectName, retrieve_outcomes);
				items.add(dataC);
			}
		}
		/*
		for(Codes a: items) {
			System.out.println(a.getCode_Codes() + "  " + a.getSubjectName_Codes() + "\t" + a.getOutcomes_Codes());
		} */
	}
	
	public boolean WaitingUI(DefaultListModel<File> model, int oc) {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Por Favor Espere");
		dialog.setSize(250, 150);
		dialog.setTitle("Procesando...");
		dialog.add(label);
		dialog.setMinimumSize(new Dimension(250, 150));
		dialog.setLocationRelativeTo(null);
		
		dialog.setVisible(true);
		
		boolean isValid = false;

		try {
	    	switch(oc) {
	    		case 1:
					ReadFiles(model);
					isValid = true;
					
	    		case 2:
					OutcomesList(model);
	    			isValid = true;
	    	}
		} catch(Exception e) {
			isValid = false;
		}

		dialog.setVisible(false);
		return isValid;
	}

}
