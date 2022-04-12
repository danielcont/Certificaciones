/*
  *  This Class is made to import files from the computer and get the data from it
 */

package upload;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// GUI Components Libraries are Imported
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

// Import of Addition Classes
import read.ReadFiles;

public class GetFiles extends JFrame implements ActionListener {
	
	// Other Classes
	private ReadFiles read = new ReadFiles();
	// JFrame
	private JFrame frame = new JFrame("Generador de Reportes");
	// JFrame Icon
	private ImageIcon icon = new ImageIcon("C:\\Users\\contr\\OneDrive\\Documents\\FIME\\Ene-Dic2022\\FIME-icon.png");
	// JTabbedPane
	private JTabbedPane tabbedPane = new JTabbedPane();
	// JLabel
	private final JLabel failedLoadingFile_label = new JLabel("Error al cargar documento.\n Fortmato Inválido"), 
				successLoadingFile_label = new JLabel("El archivo se cargó correctamente."), 
					fileSelected_label = new JLabel("Archivo Seleccionado:"),
					fileName_label = new JLabel("");
	// JButton
	private JButton navigate_button, process_button, navigateOutcomes_button; // Button To Navigate Folder and Process Files
	// JPanel
	private JPanel load_panel, list_panel, assignments_panel, loadOutcomes_panel, outcomes_panel;
	// JList
	private JList<File> fileList;
	private DefaultListModel<File> fileListModel, fileListModelOutcomes;
	
	// Main
	public static void main(String[] args) {
		GetFiles getFiles = new GetFiles();
		getFiles.UploadFiles();
	
	}
	
	// GUI
	public void UploadFiles() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Boolean old = UIManager.getBoolean("FileChooser.readOnly");
		UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        final JFileChooser fc = new JFileChooser();
        final FileListAccessory accessory = new FileListAccessory(fc);
        final DefaultListModel model = accessory.getModel();

        fileListModel = new DefaultListModel<>();
		fileList = new JList(fileListModel);
		
        fileListModelOutcomes = new DefaultListModel<>();
		
		// 'CALIFICACIONES' Pane
		// Create 'Choose File' Panel
		load_panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (350, 70);
			};
		};
		load_panel.setBorder(BorderFactory.createTitledBorder("Seleccionar Archivos"));
		
		// 'Choose File' Label
		JLabel chooseMultipleFiles_label = new JLabel("Seleccione los Archivos:");
		chooseMultipleFiles_label.setFont(new Font("verdana", Font.PLAIN, 14));
		load_panel.add(chooseMultipleFiles_label);
		
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
		                	
		                	if (model.isEmpty() && fileListModelOutcomes.isEmpty()) process_button.setEnabled(false);
		                	else process_button.setEnabled(true);
		                	
		                    for (int i = 0; i < model.getSize(); i++) {
		                        fileListModel.addElement((File) model.getElementAt(i));
		                    }
		                }
		            }
		        });
			}
		}); // File Navigator Functionality Added To This Button
		

		// Create 'List Files' Panel
		list_panel = new JPanel(new FlowLayout()) {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (350, 215);
			};
		};
		list_panel.setBorder(BorderFactory.createTitledBorder("Lista de Archivos Seleccionados"));
		
		// Adding ScrollPane
		JScrollPane list_scrollPane = new JScrollPane(fileList);
		list_scrollPane.setPreferredSize(new Dimension(290, 150));
		list_panel.add(list_scrollPane);
		
		// Process Button
		process_button = new JButton("Procesar");
		list_panel.add(process_button, BorderLayout.CENTER);
		process_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    read.WaitingUI(model, 1);
			}
            
		});
		
		if(fileListModel.isEmpty()  && fileListModelOutcomes.isEmpty()) process_button.setEnabled(false);
		
		// 'OUTCOMES' Pane
		loadOutcomes_panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (350, 280);
			};
		};
		loadOutcomes_panel.setBorder(BorderFactory.createTitledBorder("Seleccionar Lista de Outcomes"));
		
		// 'Choose File' Label
		JLabel chooseFile_label = new JLabel("Seleccione el Archivo:");
		chooseFile_label.setFont(new Font("verdana", Font.PLAIN, 14));
		loadOutcomes_panel.add(chooseFile_label);
		
		navigateOutcomes_button = new JButton("Buscar...");
		loadOutcomes_panel.add(navigateOutcomes_button);
		navigateOutcomes_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean old = UIManager.getBoolean("FileChooser.readOnly");
				UIManager.put("FileChooser.readOnly", Boolean.TRUE);
				final JFileChooser fc_outcomes = new JFileChooser();
				UIManager.put("FileChooser.readOnly", old);
				
		        fileListModelOutcomes.removeAllElements();
				
		        // JFILECHOOSER is filtered and only Excel files are shown
				FileFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xml", "xlsm");
				fc_outcomes.setAcceptAllFileFilterUsed(false); // Disables "All Files" option
           	 	fc_outcomes.setFileFilter(filter);
				
                int op = fc_outcomes.showOpenDialog(fc_outcomes);
                if (op == JFileChooser.APPROVE_OPTION) {
                	// ADD COMPONENTS WHEN A FILE IS UPLOADED
                	fileListModelOutcomes.addElement(fc_outcomes.getSelectedFile());

    				fileName_label.setText("");
    				fileName_label.setText(fc_outcomes.getSelectedFile().getName());
    				fileName_label.setVisible(true);
                	
        			boolean isValid = read.WaitingUI(fileListModelOutcomes, 2);
        			
        			if(isValid) {
        				// Set 'Correct' Label visible
        				fileSelected_label.setVisible(true);
        				failedLoadingFile_label.setVisible(false);
        				successLoadingFile_label.setVisible(true);
        			} else {
        				// Set 'Error' Label Visible
        				fileSelected_label.setVisible(true);
        				successLoadingFile_label.setVisible(false);
        				failedLoadingFile_label.setVisible(true);
        			}
                }
			}
		}); // File Navigator Functionality Added To This Button
		
		// 'File Selected' name Label
		fileSelected_label.setFont(new Font("verdana", Font.PLAIN, 14));
		loadOutcomes_panel.add(fileSelected_label);
		fileSelected_label.setVisible(false);
		
		// 'File Name Selected' name Label
		fileName_label.setFont(new Font("verdana", Font.PLAIN, 14));
		fileName_label.setForeground(new Color(0, 78, 154));
		loadOutcomes_panel.add(fileName_label);
		fileName_label.setVisible(false);
		
		// 'Error' Label
		failedLoadingFile_label.setFont(new Font("verdana", Font.PLAIN, 14));
		failedLoadingFile_label.setForeground(new Color(138, 3, 3));
		loadOutcomes_panel.add(failedLoadingFile_label);
		failedLoadingFile_label.setVisible(false);
		
		// 'Successfully loaded' Label
		successLoadingFile_label.setFont(new Font("verdana", Font.PLAIN, 14));
		successLoadingFile_label.setForeground(new Color(56, 124, 68));
		loadOutcomes_panel.add(successLoadingFile_label);
		successLoadingFile_label.setVisible(false);
		
		// Add Panels to TabbedPane and Assign Frame a Position
		frame.setVisible(true);
		frame.setSize(385, 365);
		frame.setMinimumSize(new Dimension(385, 365));
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon.getImage());
		
		// TabbedPanes
		assignments_panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (370, 395);
			};
		};
		assignments_panel.add(load_panel, BorderLayout.NORTH);
		assignments_panel.add(list_panel, BorderLayout.CENTER);
		
		outcomes_panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (370, 395);
			};
		};
		outcomes_panel.add(loadOutcomes_panel);

		tabbedPane.addTab("Lista Outcomes", outcomes_panel);
		tabbedPane.addTab("Calificaciones", assignments_panel);
		
		frame.add(tabbedPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

	}
	
}