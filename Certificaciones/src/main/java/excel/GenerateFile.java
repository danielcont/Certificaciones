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
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.Codes;
import data.DataOC;

@SuppressWarnings("serial")
public class GenerateFile extends JFrame {
	// Other Classes
	private ReportData reportData = new ReportData();
	// Panel for File generated
	private JPanel generatedReport = new JPanel();
	// JFrame Icon
	private ImageIcon icon = new ImageIcon("C:\\Users\\contr\\OneDrive\\Documents\\FIME\\Ene-Dic2022\\FIME-icon.png");
	
	@SuppressWarnings("unchecked")
	public void CreateExcelFile(ArrayList<Codes> outcomeCodes, ArrayList<DataOC> studentsData)  throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();

		reportData.ABETData();
		
		// Set Cell Borders
		CellStyle borders = workbook.createCellStyle();
		borders.setBorderBottom(BorderStyle.MEDIUM);
		borders.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		borders.setBorderLeft(BorderStyle.MEDIUM);
		borders.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		borders.setBorderTop(BorderStyle.MEDIUM);
		borders.setTopBorderColor(IndexedColors.BLACK.getIndex());
		borders.setBorderRight(BorderStyle.MEDIUM);
		borders.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		// Enable new lines within Cells
		CellStyle multipleLines = workbook.createCellStyle();
		multipleLines.cloneStyleFrom(borders);
		multipleLines.setWrapText(true);
		
		// Cell alignment
		CellStyle alignment = workbook.createCellStyle();
		alignment.cloneStyleFrom(multipleLines);
		alignment.setAlignment(HorizontalAlignment.CENTER);
		alignment.setVerticalAlignment(VerticalAlignment.CENTER);
		
		// Set background - grey color
		CellStyle bgGrey = workbook.createCellStyle();
		bgGrey.cloneStyleFrom(alignment);
		bgGrey.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		bgGrey.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Set background - green color
		CellStyle bgLime = workbook.createCellStyle();
		bgLime.cloneStyleFrom(alignment);
		bgLime.setFillForegroundColor(IndexedColors.LIME.index);
		bgLime.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle combinedGrey = workbook.createCellStyle();
		combinedGrey.cloneStyleFrom(bgGrey);
		combinedGrey.setWrapText(true);
		
		CellStyle combinedLime = workbook.createCellStyle();
		combinedLime.cloneStyleFrom(bgLime);
		combinedLime.setWrapText(true);
		
		// Create Sheets
		for(int i = 0; i < 7; i++) {
			XSSFSheet spreadsheet = workbook.createSheet("Outcome " + (i + 1));
			spreadsheet.setDefaultColumnWidth(16);
			
			int totalRows = 2; // Total of rows for ABET report
			Map<String, String> outcomeData = reportData.AllOutcomesData.get(i);
			Map<String, String> ABETscore = reportData.ABETscore;

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
					cell = row.createCell(currentRow);
					cell.setCellValue("ABET \nOutcome");

					CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 2);
					spreadsheet.addMergedRegion(cellRangeAddress);
					cell.setCellStyle(combinedLime);
					
					cell = row.createCell(3);
					cell.setCellValue(outcomeData.get("Outcome " + (i + 1))); // Outcome General data is set
					
					int nos = ((outcomeData.size() - 1) * 5) + assignmentsTotal;
					
					CellRangeAddress outcomeRangeAddress = new CellRangeAddress(1, 1, 3, nos + 2);
					spreadsheet.addMergedRegion(outcomeRangeAddress);
					cell.setCellStyle(combinedGrey);
					
					row.setHeightInPoints((3 * spreadsheet.getDefaultRowHeightInPoints()));
					
				} else if(currentRow == 2) { // Suboutcome data is added
					cell = row.createCell(1);
					cell.setCellValue("The student \nwill be able to");

					CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 1, 2);
					spreadsheet.addMergedRegion(cellRangeAddress);
					cell.setCellStyle(combinedLime);

					
					int lastColumnOutcome = 3;
					Row rowResults = null;
					if(spreadsheet.getRow(3) == null) rowResults = spreadsheet.createRow(3);
					Cell cellResults, cellScore;
					
					for(Map.Entry<String, String> subOutcomes : outcomeData.entrySet()) {
						if(!subOutcomes.getKey().equals("Outcome " + (i + 1))) {
							cell = row.createCell(lastColumnOutcome);
							cell.setCellValue(subOutcomes.getValue());
							
							int subjectCount = 0;
							
							for(DataOC a: studentsData) {
								for(int x = 0; x < a.getOutcomes_DataOC().size(); x ++) {
									if(a.getOutcomes_DataOC().get(x).equals(subOutcomes.getKey())) {
										
										cellResults = rowResults.createCell(lastColumnOutcome + 2 + subjectCount);
										cellResults.setCellValue(a.getCode_DataOC() + " " + a.getSubject_DataOC());
										cellResults.setCellStyle(combinedGrey);
										
										for(int idk = 4; idk <= 8; idk ++) {
											if(spreadsheet.getRow(idk) == null) spreadsheet.createRow(idk);
											
											cellResults = spreadsheet.getRow(idk).createCell(lastColumnOutcome + 2 + subjectCount);
											if(idk != 8) {
												cellResults.setCellValue(a.getStudentsScore_DataOC()[idk - 4]);
												cellResults.setCellStyle(borders);
												
												String e2 = Integer.toString(idk - 4);
												cellScore = spreadsheet.getRow(idk).createCell(lastColumnOutcome + 1);
												cellScore.setCellValue(ABETscore.get(e2));
												cellScore.setCellStyle(borders);
											} else {
												cellResults.setCellValue(a.getTotalStudents_DataOC());
												cellResults.setCellStyle(borders);
											}
										}
										
										subjectCount ++;
									}
								}
								
								if(subjectCount != 0) {
									cellResults = rowResults.createCell(lastColumnOutcome + subjectCount + 2);
									cellResults.setCellValue("TOTAL");
									cellResults.setCellStyle(combinedGrey);
									
									for(int idk = 4; idk <= 8; idk ++) {
										if(spreadsheet.getRow(idk) == null) spreadsheet.createRow(idk);
										
										String SUM = "";
										String PCTG = "";
										cellResults = spreadsheet.getRow(idk).createCell(lastColumnOutcome + subjectCount + 2);
										
										CellReference start = new CellReference(idk, lastColumnOutcome + 2);
										CellReference end = new CellReference(idk, lastColumnOutcome + subjectCount + 1);
										
										CellReference startTotal = new CellReference(8, lastColumnOutcome + 2);
										CellReference endTotal = new CellReference(8, lastColumnOutcome + subjectCount + 1);
										
										if(start.formatAsString().equals(end.formatAsString())) {
											SUM = "SUM(" + start.formatAsString() + ")";
											
											PCTG = "(SUM(" + start.formatAsString() + ")/SUM(" + 
													startTotal.formatAsString() + "))*100";
										} else {
											SUM = "SUM(" + start.formatAsString() + ":" + end.formatAsString() + ")";
											
											PCTG = "(SUM(" + start.formatAsString() + ":" + end.formatAsString() + ")/SUM(" + 
													startTotal.formatAsString() + ":" + endTotal.formatAsString() + "))*100";
										}
										
										cellResults.setCellFormula(SUM);
										cellResults.setCellStyle(borders);
										if(idk == 8) cellResults.setCellStyle(bgLime);
										
										cellResults = spreadsheet.getRow(idk).createCell(lastColumnOutcome + subjectCount + 3);
										
										cellResults.setCellFormula(PCTG);
										cellResults.setCellStyle(borders);
									}
									
									
									cellResults = rowResults.createCell(lastColumnOutcome + subjectCount + 3);
									cellResults.setCellValue("%");
									cellResults.setCellStyle(combinedGrey);
								}
							}
							
							spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, lastColumnOutcome, lastColumnOutcome + subjectCount + 4));
							cell.setCellStyle(alignment);
							lastColumnOutcome += subjectCount + 5;
						}
						
					}

					row.setHeightInPoints((4*spreadsheet.getDefaultRowHeightInPoints()));
					rowResults.setHeightInPoints((4*spreadsheet.getDefaultRowHeightInPoints()));
					
				}
			}
			
			
			List<CellRangeAddress> mergedRegions = spreadsheet.getMergedRegions();
			for (CellRangeAddress rangeAddress : mergedRegions) {
			    RegionUtil.setBorderTop(BorderStyle.MEDIUM, rangeAddress, spreadsheet);
			    RegionUtil.setBorderLeft(BorderStyle.MEDIUM, rangeAddress, spreadsheet);
			    RegionUtil.setBorderRight(BorderStyle.MEDIUM, rangeAddress, spreadsheet);
			    RegionUtil.setBorderBottom(BorderStyle.MEDIUM, rangeAddress, spreadsheet);
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
