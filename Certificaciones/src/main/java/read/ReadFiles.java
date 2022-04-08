 /*
  * This Class Reads and Process All Files Previously Retrieved From the 'GetFiles' class
 */

package read;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.Codes;
import data.DataOC;
import excel.GenerateFile;


public class ReadFiles {	
	
	// Other Classes
	//private GenerateFile generateReport = new GenerateFile();
	private ArrayList<Codes> items = new ArrayList<Codes>(); // Array where all Outcomes instances will be stored
	private ArrayList<DataOC> itemsStudents = new ArrayList<DataOC>(); // Array where all data from Students instances will be stored

	private void ReadFiles(DefaultListModel<File> modelOutcomes) throws IOException {
		// Start Reading the File

		// 1. Files
		for(int i = 0; i < modelOutcomes.getSize(); i++) {
			// CSV Format File
			// Getting the File Path
			File f = new File(modelOutcomes.getElementAt(i).toString());
			
			// 'list' retrieves the file Data
			List<String> list = new ArrayList<String>();
	        try {
	            list = Files.readAllLines(f.toPath(), Charset.defaultCharset());
	        } catch (IOException ex) {
	            //ex.printStackTrace();
	        }
	        
	    	String certif = "Abet"; // Certificación
	    	String code = ""; // Código de la Materia
	    	String subject = ""; // Nombre de la Materia
	    	String assignment = "";  // Número de la Actifidad Fundamental a Revisar
	    	ArrayList<String> outcomes = null; // Lista de Outcomes
	    	int studentsEX = 0; // Número de Estudiantes con calificación Ex
	    	int studentsG = 0; // Número de Estudiantes con calificación G
	    	int studentsA = 0; // Número de Estudiantes con calificación A
	    	int studentsNS = 0; // Número de Estudiantes con calificación NS; // Número de Estudiantes
	    	float average = 0; // Promedio General de calificaciones
	        
	        int row = 0; // Variable to identify Rows
	        int column_assignment = 0; // Get the column with the Assignment
	        boolean hasContent = false;
	        for(String listItem: list) { // Iterate line by line
	        	String[] arr = listItem.split(","); // Split the elements from a Row using a ","
	        	
	        	if(row == 0) { // First Row identified
	        		code = arr[0].replaceAll("^\"|\"$", ""); // START THE SEARCH FOR THE OUTCOMES LIST
	        		subject = arr[1].replaceAll("^\"|\"$", "");
	        		
	        		// Search and retrieve list of Outcomes and assignments
	        		for(Codes a: items) {
	        			if(code.equals(a.getCode_Codes())) {
		        			//System.out.println(a.getCode_Codes() + " " + code);
	        				outcomes = a.getOutcomes_Codes();
	        				assignment = a.getAssignment_Codes();
	        				break;
	        			}
	        		}
	        		
	        	} else if(row == 1 && arr[0].replaceAll("^\"|\"$", "").equals("Gpo.")) {
	        		
	        		for(int iarr = 0; iarr < arr.length; iarr++) {
	        			if(arr[iarr].replaceAll("^\"|\"$", "").equals(assignment)) {
	        				column_assignment = iarr;
	        			}
	        		}
	        		
	        	} else if(row >= 1 && arr[0].replaceAll("^\"|\"$", "").equals("") && !arr[column_assignment].replaceAll("^\"|\"$", "").equals("")) {
	        		int grade = Integer.parseInt(arr[column_assignment].replaceAll("^\"|\"$", ""));
	        		
	        		if(grade >= 90) {
	        			studentsEX ++;
	        		} else if(grade >= 80 && grade <= 89) {
	        			studentsG ++;
	        		} else if(grade >= 70 && grade <= 79) {
	        			studentsA ++;
	        		} else if(grade <= 69) {
	        			studentsNS ++;
	        		}
	        		
	        		average += grade;
	        		hasContent = true;
	        	}
	        	row ++;
	        }
	        
			//if(hasContent) {
				DataOC dataOC = new DataOC(certif, code, subject, assignment, outcomes, studentsEX, studentsG, studentsA, studentsNS, average);
				itemsStudents.add(dataOC);
			//}
			
		}

		for(DataOC b: itemsStudents) {
			System.out.println(b.getCode_DataOC() + "  " + b.getAssignment_DataOC() + "\t\t" + b.getStudentsEX_DataOC() + "  " + b.getStudentsG_DataOC() + "  " 
					+ b.getStudentsA_DataOC() + "  " + b.getStudentsNS_DataOC() + "\t");
		}
		
	}
	
	private ArrayList<Codes> OutcomesList(DefaultListModel<File> model) throws Exception {
		// ArrayList<Codes> items = new ArrayList<Codes>();
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
				String retrieve_subjectName = ""; // Nombre de la Materia
				String retrieve_assignment = "";
				ArrayList<String> retrieve_outcomes = new ArrayList<String>(); // Outcomes a tomar en cuenta
					
				// For each row, iterate through all the collumns
				Iterator<Cell> cellIterator = row.cellIterator();
				
				boolean hasContent = false;
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
							if(cell.getRowIndex() == 0 && cell.getColumnIndex() <= 8
									&& !(cell.getStringCellValue().equals("Unidades de Aprendizaje") 
												|| cell.getStringCellValue().equals("Outcome 1")
												|| cell.getStringCellValue().equals("Outcome 2")
												|| cell.getStringCellValue().equals("Outcome 3")
												|| cell.getStringCellValue().equals("Outcome 4")
												|| cell.getStringCellValue().equals("Outcome 5")
												|| cell.getStringCellValue().equals("Outcome 6")
												|| cell.getStringCellValue().equals("Outcome 7")
												|| cell.getStringCellValue().equals("Actividad Fun."))) {
								System.out.print(cell.getStringCellValue());
								throw new Exception(); // The format is not correct
							} else if(cell.getRowIndex() >= 1) {
								// Get Code and Name of the Subject
								if(cell.getColumnIndex() == 0) {
									String[] arrOfStr = cell.getStringCellValue().split(" ", 2);
									retrieve_codes = arrOfStr[0];
									retrieve_subjectName = arrOfStr[1];
									hasContent = true;
								}
								// Get the Assignment
								if(cell.getColumnIndex() == 8) {
									retrieve_assignment = cell.getStringCellValue();
								}
							}
							
							break;
						default:
					}
				}
				if(hasContent && !retrieve_assignment.equals("")) {
					Codes dataC = new Codes(retrieve_codes, retrieve_subjectName, retrieve_assignment, retrieve_outcomes);
					items.add(dataC);
				}
			}
		}
		
		//for(Codes a: items) {
			//System.out.println(a.getCode_Codes() + "  " + a.getSubjectName_Codes() + "\t" + a.getOutcomes_Codes() + "\t" + a.getAssignment_Codes());
		//} 
		
		return items;
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
			    	//generateReport.CreateExcelFile(items, itemsStudents);
					break;
	    		case 2:
					OutcomesList(model);
	    			isValid = true;
	    			break;
	    	}
		
		} catch(Exception e) {
			isValid = false;
			e.printStackTrace();
		}

		dialog.setVisible(false);
		return isValid;
	}

}
