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
import java.util.Dictionary;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.BorderStyle;
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
			Dictionary<String, String> outcomeData = reportData.AllOutcomesData.get(i);
			
			// Iterate over Rows
			for(int currentRow = 0; currentRow <= totalRows; currentRow++) {
				
				Row row = spreadsheet.createRow(currentRow);
				Cell cell;
				
				if(currentRow == 1) {
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
					cell.setCellValue(outcomeData.get("0"));
					
					spreadsheet.addMergedRegion(new CellRangeAddress(
					        1, //first row
					        1, //last row
					        3, //first column
					        15  //last column
					));
					cell.setCellStyle(combinedBlue);
					
					row.setHeightInPoints((4*spreadsheet.getDefaultRowHeightInPoints()));
				} else if(currentRow == 2) {
					cell = row.createCell(1);
					cell.setCellValue("The student will be \nable to");

					spreadsheet.addMergedRegion(new CellRangeAddress(
					        2, // first row
					        2, // last row
					        1, // first column
					        2  // last column
					));
					cell.setCellStyle(combinedCoral);
					
					/*
					spreadsheet.addMergedRegion(new CellRangeAddress(
					        1, //first row
					        1, //last row
					        1, //first column
					        2  //last column
					));*/

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
					System.out.println("File Clicked");
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
