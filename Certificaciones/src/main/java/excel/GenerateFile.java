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
import java.util.LinkedHashMap;
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
	public void CreateExcelFile(ArrayList<Codes> outcomeCodes, ArrayList<DataOC> studentsData)  throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet;

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

		Map<String, String> PCTGscore = new LinkedHashMap<String, String>();
		Map<String, String> ABETscore = reportData.ABETscore;
		
		// Create Sheets
		for(int i = 0; i <= 7; i++) {
			
			if(i != 7) {
				spreadsheet = workbook.createSheet("Outcome " + (i + 1));
				spreadsheet.setDefaultColumnWidth(16);
				
				int totalRows = 2; // Total of rows for ABET report
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
										
										Row ResultsPCTG, TotalSUM;
										Cell cellResultsPCTG, cellTotalSUM;
										
										int PCTGcount = 0;
										for(int idk = 4; idk <= 8; idk ++) {
											if(spreadsheet.getRow(idk) == null) spreadsheet.createRow(idk);
											
											int SUM = 0;
											int TOTAL = 0;
											for(int pctg = lastColumnOutcome + 2; pctg <= lastColumnOutcome + subjectCount + 1; pctg ++) {
	
												ResultsPCTG = spreadsheet.getRow(idk);
												cellResultsPCTG = ResultsPCTG.getCell(pctg);									
												SUM += cellResultsPCTG.getNumericCellValue();
												
												TotalSUM = spreadsheet.getRow(8);
												cellTotalSUM = TotalSUM.getCell(pctg);
												TOTAL += cellTotalSUM.getNumericCellValue();
												
											}
	
											cellResults = spreadsheet.getRow(idk).createCell(lastColumnOutcome + subjectCount + 2);
											cellResults.setCellValue(SUM);
											cellResults.setCellStyle(alignment);
											if(idk == 8) cellResults.setCellStyle(bgLime);
											
											cellResults = spreadsheet.getRow(idk).createCell(lastColumnOutcome + subjectCount + 3);
											
											int PCTG = (int) Math.round((SUM * 100.0f) / TOTAL);
											cellResults.setCellValue(PCTG);
											cellResults.setCellStyle(alignment);
											
											String arr[] = subOutcomes.getKey().split(" ");
											PCTGscore.put(arr[1] + PCTGcount, String.valueOf(PCTG));
											
											PCTGcount ++;
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
				
				// TABLE #3
				Row row = spreadsheet.createRow(12);
				Cell cell;
	
				cell = row.createCell(4);
				cell.setCellValue("ABET Outcome " + (i + 1));
	
				CellRangeAddress cellRangeAddress = new CellRangeAddress(12, 12, 4, 3 + outcomeData.size());
				spreadsheet.addMergedRegion(cellRangeAddress);
				cell.setCellStyle(combinedLime);
				
				int col = 4;
				for(Map.Entry<String, String> subOutcomes : outcomeData.entrySet()) {
					
					if(col == 4) {
						// ABET score data is added
						for(int idk = 13; idk <= 17; idk ++) {
							if(spreadsheet.getRow(idk) == null) spreadsheet.createRow(idk);
							
							if(idk == 13) {
								cell = spreadsheet.getRow(idk).createCell(col);
								cell.setCellStyle(borders);
								
							} else {
								cell = spreadsheet.getRow(idk).createCell(col);
								
								String e2 = Integer.toString(idk - 14);
								cell.setCellValue(ABETscore.get(e2));
								cell.setCellStyle(borders);
							}
						}
						
					} else {
						// Percentages by each suboutcome is added
						
						String outcomeName = subOutcomes.getKey();
						String[] arr = outcomeName.split(" ");
						int PCTGcount = 0;
						for(int idk = 13; idk <= 17; idk ++) {
							if(spreadsheet.getRow(idk) == null) spreadsheet.createRow(idk);
							
							if(idk == 13) {
								cell = spreadsheet.getRow(idk).createCell(col);
	
								cell.setCellValue(arr[1]);
								cell.setCellStyle(alignment);
							} else { 
								cell = spreadsheet.getRow(idk).createCell(col);
							
								if(PCTGscore.get(arr[1] + PCTGcount) != null) {
									cell.setCellValue(PCTGscore.get(arr[1] + PCTGcount));
								} else {
									cell.setCellValue("-");
								}
								cell.setCellStyle(alignment);
								PCTGcount ++;
							}
						}
					}
					
					col ++;
				}
			} else {
				// Last SHEET is generated
				spreadsheet = workbook.createSheet("ABET");
				spreadsheet.setDefaultColumnWidth(12);

				Row row = spreadsheet.createRow(1);
				Cell cell, cellData;

				// ABET score data is added
				for(int idk = 4; idk <= 7; idk ++) {
					if(spreadsheet.getRow(idk) == null) spreadsheet.createRow(idk);
					
					cell = spreadsheet.getRow(idk).createCell(1);
					
					String e2 = Integer.toString(idk - 4);
					cell.setCellValue(ABETscore.get(e2));
					cell.setCellStyle(borders);

				}
				
				cell = row.createCell(2);
				cell.setCellValue("ABET OUTCOMES RESULTS");
				
				CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 2, 16);
				spreadsheet.addMergedRegion(cellRangeAddress);
				cell.setCellStyle(combinedGrey);
				
				int col = 2;
				for(int cdc = 0; cdc < 7; cdc ++) {
					Map<String, String> outcomeData = reportData.AllOutcomesData.get(cdc);
					
					int lastCol = 0;
					for(Map.Entry<String, String> subOutcomes : outcomeData.entrySet()) {

						String outcomeName = subOutcomes.getKey();
						String[] arr = outcomeName.split(" ");
						
						if(lastCol == 0) {
							if(spreadsheet.getRow(2) == null) spreadsheet.createRow(2);
							
							cellData = spreadsheet.getRow(2).createCell(col);
							cellData.setCellValue(outcomeName);
							cellData.setCellStyle(combinedLime);

							if(outcomeData.size() > 2) {
								cellRangeAddress = new CellRangeAddress(2, 2, col, col + outcomeData.size() - 2);
								spreadsheet.addMergedRegion(cellRangeAddress);
							}
							
						} else {
							
							int PCTGcount = 0;
							for(int idk = 3; idk <= 7; idk ++) {
								if(spreadsheet.getRow(idk) == null) spreadsheet.createRow(idk);
								
								if(idk == 3) {
									cell = spreadsheet.getRow(idk).createCell(col);

									cell.setCellValue(arr[1]);
									cell.setCellStyle(alignment);
								} else { 
									cell = spreadsheet.getRow(idk).createCell(col);
								
									if(PCTGscore.get(arr[1] + PCTGcount) != null) {
										cell.setCellValue(PCTGscore.get(arr[1] + PCTGcount));
									} else {
										cell.setCellValue("-");
									}
									cell.setCellStyle(alignment);
									PCTGcount ++;
								}
							}
							col ++;
						}
						lastCol ++;
					}
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
