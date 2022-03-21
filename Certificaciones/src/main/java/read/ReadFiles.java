 /*
  * This Class Reads and Process All Files Previously Retrieved From the 'GetFiles' class
 */

package read;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadFiles {

	private void readFiles(DefaultListModel<File> model) throws IOException {
		
		// Start Reading the File

		// 1. Files
		for(int i = 0; i < model.getSize(); i++) {
			// Is Important to Know the # of Workbooks
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(model.getElementAt(i).toString()));
			// 2. Sheets Within Workbooks are Read
			for(int j = 0; j < workbook.getNumberOfSheets(); j++) {
				XSSFSheet sheet = workbook.getSheetAt(j);
				System.out.println("hi");
			}
		}
		
	}
	
	public void WaitingUI(DefaultListModel<File> model) {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Por Favor Espere");
		dialog.setSize(250, 150);
		dialog.setTitle("Procesando...");
		dialog.add(label);
		dialog.setMinimumSize(new Dimension(250, 150));
		dialog.setLocationRelativeTo(null);
		
		dialog.setVisible(true);

        try {
			readFiles(model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dialog.setVisible(false);
	}

}
