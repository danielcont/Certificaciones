/*
 * This Class Reads and Process All Files Previously Retrieved From the 'GetFiles' class
 */

package read;

import java.io.File;

import javax.swing.DefaultListModel;

public class ReadFiles {

	public void getData(DefaultListModel<File> model) {
		
		// Start Reading the file
		for(int i = 0; i < model.getSize(); i++) {
			System.out.println(model.getElementAt(i));
		}
	}

}
