 /*
  *  This Class is made to import files from the computer and get the data from it
 */

package upload;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// GUI Components Libraries are Imported
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

// Import of Addition Classes
import read.ReadFiles;

public class GetFiles extends JFrame implements ActionListener {
	
	// Other Classes
	private ReadFiles read = new ReadFiles();
	// JFrame
	private JFrame frame = new JFrame("Cargar Archivos");
	// JButton
	private JButton navigate_button, process_button; // Button To Navigate Folder and Process Files
	// JPanel
	private JPanel load_panel, list_panel;
	// JList
	private JList<File> fileList;
	private DefaultListModel<File> fileListModel;
	
	// Main
	public static void main(String[] args) {
		GetFiles getFiles = new GetFiles();
		getFiles.UploadFiles();
	
	}
	
	// GUI
	public void UploadFiles() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser fc = new JFileChooser();
        FileListAccessory accessory = new FileListAccessory(fc);
        DefaultListModel model = accessory.getModel();

        fileListModel = new DefaultListModel<>();
		fileList = new JList(fileListModel);
		
		// Create 'Choose File' Panel
		load_panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (450, 80);
			};
		};
		load_panel.setBorder(BorderFactory.createTitledBorder("Seleccionar Archivos"));
		
		// 'Choose File' Label
		JLabel chooseFile_label = new JLabel("Seleccione los Archivos:");
		chooseFile_label.setFont(new Font("verdana", Font.PLAIN, 14));
		load_panel.add(chooseFile_label);
		
		// SEARCH FILES Button
		navigate_button = new JButton("Buscar...");
		load_panel.add(navigate_button);
		navigate_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                fc.setAccessory(accessory);
		                fc.setMultiSelectionEnabled(true);

		                int open = fc.showOpenDialog(fc);
		                if (open == JFileChooser.APPROVE_OPTION) {
		                	fileListModel.removeAllElements();
		                	
		                	if (model.getSize() == 0) process_button.setEnabled(false);
		                	else process_button.setEnabled(true);
		                	
		                    for (int i = 0; i < model.getSize(); i++) {
		                        fileListModel.addElement(((File)model.getElementAt(i)));
		                    }
		                }
		            }
		        });
			}
		}); // File Navigator Functionality Added To This Button
		

		// Create 'List Files' Panel
		list_panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (450, 600);
			};
		};
		list_panel.setBorder(BorderFactory.createTitledBorder("Lista de Archivos Seleccionados"));
		
		list_panel.add(new JScrollPane(fileList), BorderLayout.NORTH);
		
		// Process Button
		process_button = new JButton("Procesar");
		list_panel.add(process_button);
		process_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				read.getData(model);
			}
            
		});
		
		if(fileListModel.isEmpty()) process_button.setEnabled(false);
		
		// Add Panels to Frame and Assign Them a Position
		frame.setVisible(true);
		frame.setSize(350, 350);
		frame.setMinimumSize(new Dimension(350, 350));
		frame.setLocationRelativeTo(null);
		
		frame.add(load_panel, BorderLayout.NORTH);
		frame.add(list_panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

	}
	
}