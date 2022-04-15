package excel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.Codes;
import data.DataOC;

public class GenerateFile extends JFrame {
	// Other Classes
	private ReportData reportData = new ReportData();
	// Panel for File generated
	private JPanel generatedReport = new JPanel();
	// JFrame Icon
	private ImageIcon icon = new ImageIcon("C:\\Users\\contr\\OneDrive\\Documents\\FIME\\Ene-Dic2022\\FIME-icon.png");
	
	public void CreateExcelFile(ArrayList<Codes> outcomeCodes, ArrayList<DataOC> studentsData)  throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();

		reportData.ABETData();
		
		// Enable new lines within Cells
		CellStyle multipleLines = workbook.createCellStyle();
		multipleLines.setWrapText(true);
		
		// Set background - grey color
		CellStyle bgBlue = workbook.createCellStyle();
		bgBlue.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.index);
		bgBlue.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Set background - green color
		CellStyle bgCoral = workbook.createCellStyle();
		bgCoral.setFillForegroundColor(IndexedColors.TAN.index);
		bgCoral.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle combinedBlue = workbook.createCellStyle();
		combinedBlue.cloneStyleFrom(bgBlue);
		combinedBlue.setWrapText(true);
		
		CellStyle combinedCoral = workbook.createCellStyle();
		combinedCoral.cloneStyleFrom(bgCoral);
		combinedCoral.setWrapText(true);
		
		// Create Sheets
		for(int i = 0; i < 7; i++) {
			XSSFSheet spreadsheet = workbook.createSheet("Outcome " + (i + 1));
			
			int totalRows = 18; // Total of rows for ABET report
			Map<String, String> outcomeData = reportData.AllOutcomesData.get(i);

			int assignmentsTotal = 0;
			for(DataOC a: studentsData) {
				for(int x = 0; x < a.getOutcomes_DataOC().size(); x ++) {
					for(String key : outcomeData.keySet()) {
						if(a.getOutcomes_DataOC().get(x).equals(key)) {
							assignmentsTotal ++;
						}
						
					}
				}
			}
			
			// Iterate over Rows
			for(int currentRow = 0; currentRow <= totalRows; currentRow++) {
				
				Row row = spreadsheet.createRow(currentRow);
				Cell cell;
				
				if(currentRow == 1) { // Main Outcome data is added
					cell = row.createCell(1);
					cell.setCellValue("ABET \nOutcome");

					spreadsheet.addMergedRegion(new CellRangeAddress(
					        1, // first row
					        1, // last row
					        1, // first column
					        2  // last column
					));
					cell.setCellStyle(combinedCoral);
					
					cell = row.createCell(3);
					cell.setCellValue(outcomeData.get("Outcome " + (i + 1))); // Outcome General data is set
					
					int nos = ((outcomeData.size() - 1) * 5) + assignmentsTotal;
					
					spreadsheet.addMergedRegion(new CellRangeAddress(
					        1, // first row
					        1, // last row
					        3, // first column
					        nos + 2 // last column
					));
					cell.setCellStyle(combinedBlue);
					
					row.setHeightInPoints((3 * spreadsheet.getDefaultRowHeightInPoints()));
					
				} else if(currentRow == 2) { // Suboutcome data is added
					cell = row.createCell(1);
					cell.setCellValue("The student will be able to");

					spreadsheet.addMergedRegion(new CellRangeAddress(
					        2, // first row
					        2, // last row
					        1, // first column
					        2  // last column
					));
					cell.setCellStyle(combinedCoral);

					
					int lastColumnOutcome = 3; // APROVECHAR
					Row row_results = spreadsheet.createRow(3);
					for(Map.Entry<String, String> subOutcomes : outcomeData.entrySet()) {
						if(!subOutcomes.getKey().equals("Outcome " + (i + 1))) {
							cell = row.createCell(lastColumnOutcome);
							cell.setCellValue(subOutcomes.getValue());
							
							int subjectCount = 0;

							if(spreadsheet.getRow(3) == null) ;
							Cell cell_results;
							
							Row row_results_data;
							Cell cell_results_data;
							
							for(DataOC a: studentsData) {
								for(int x = 0; x < a.getOutcomes_DataOC().size(); x ++) {
									if(a.getOutcomes_DataOC().get(x).equals(subOutcomes.getKey())) {
										int newCell = lastColumnOutcome + 2 + subjectCount;
										
										cell_results = row_results.createCell(newCell);
										cell_results.setCellValue(a.getCode_DataOC() + " " + a.getAssignment_DataOC());
										cell_results.setCellStyle(combinedBlue);
										System.out.println("hi");

										subjectCount ++;
										/*
										for(int idk = 4; idk <= 8; idk ++) { // 'idk' represents rows and iterates within the same column
											if(spreadsheet.getRow(idk) != null) row_results_data = spreadsheet.createRow(idk);
											
											cell_results_data = spreadsheet.getRow(idk).createCell(a.getStudentsEX_DataOC());
											cell_results_data.setCellValue(100);
											
										}*/
										
									}
								}
							}
							
							
							spreadsheet.addMergedRegion(new CellRangeAddress(
							        2, // first row
							        2, // last row
							        lastColumnOutcome, // first column
							        lastColumnOutcome + subjectCount + 4 // last column
							));
							cell.setCellStyle(multipleLines);
							lastColumnOutcome += subjectCount + 5;
						}
						
					}

					row.setHeightInPoints((4*spreadsheet.getDefaultRowHeightInPoints()));
					
				} else if (currentRow >= 3) {
					
				}
				
			}
		}
		
		// Create unique file name
		DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy - HH mm ss");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		String fileName = "ABET-" + strDate + ".xlsx";
		
		// Create Directory Path
		String PATH = "C:/ABET/";
		File directory = new File(PATH);
		
		// Create a directory if it does not exist
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		File file = new File(PATH + "/" + fileName);
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		workbook.close();
		
		FileLocationGUI(file);
		
	}
	
	// 
	private void FileLocationGUI(final File generatedFile) {
		JFrame report = new JFrame();
		
		JLabel label = new JLabel("Reporte Generado Exitosamente");
		label.setFont(new Font("verdana", Font.PLAIN, 14));
		
		JLabel fileName_label = new JLabel("<HTML><U>" + generatedFile.getName().toString() + "<HTML><U>");
		fileName_label.setFont(new Font("verdana", Font.PLAIN, 14));
		fileName_label.setForeground(new Color(0, 78, 154));
		fileName_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fileName_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(generatedFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		

		generatedReport = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (300, 200);
			};
		};
		generatedReport.setBorder(BorderFactory.createTitledBorder("Seleccionar Lista de Outcomes"));
		
		generatedReport.add(label);
		generatedReport.add(fileName_label);
		
		report.add(generatedReport);
		report.setSize(320, 200);
		report.setMinimumSize(new Dimension(320, 200));
		report.setLocationRelativeTo(null);
		report.setIconImage(icon.getImage());
		report.setTitle("Estado de Reporte");
		
		report.setVisible(true);
		report.setAlwaysOnTop(true);
	}
}
