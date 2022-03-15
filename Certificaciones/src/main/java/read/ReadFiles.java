 /*
  * This Class Reads and Process All Files Previously Retrieved From the 'GetFiles' class
 */

package read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.DefaultListModel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadFiles {

	public void readFiles(DefaultListModel<File> model) throws IOException {
		
		// Start Reading the File
		
		// 1. Files
		for(int i = 0; i < model.getSize(); i++) {
			// Is Important to Know the # of Workbooks
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(model.getElementAt(i).toString()));
			// 2. Sheets Within Workbooks are Read
			for(int j = 0; j < workbook.getNumberOfSheets(); i++) {
				XSSFSheet sheet = workbook.getSheetAt(j);
				System.out.println("hi");
			}
		}
		
	}

}
