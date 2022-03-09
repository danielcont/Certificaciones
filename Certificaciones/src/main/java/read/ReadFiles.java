 /*
  * This Class Reads and Process All Files Previously Retrieved From the 'GetFiles' class
 */

package read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.DefaultListModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadFiles {

	public void readFiles(DefaultListModel<File> model) throws IOException {
		
		// Start Reading the File
			// model.getSize()
		
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(""));
		
	}

}
