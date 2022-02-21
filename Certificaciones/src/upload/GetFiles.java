/*
 * This Class is made to import files from the computer and get the data from it
 */

package upload;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// GUI Components Libraries are imported
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class GetFiles extends JFrame implements ActionListener {
	
	// JFrame
	private JFrame frame = new JFrame("Cargar Archivos");
	// JButton
	private JButton navigate_button, process_button; // Button To Navigate Folder and Process Files
	// JPanel
	private JPanel load_panel, list_panel;
	// JScrollPane
	private JScrollPane scroll_pane;
	// File Object
	private File[] files;
	// JList of Files
	private JList listFiles;
	
	// Main
	public static void main(String[] args) {
		GetFiles getFiles = new GetFiles();
		getFiles.UploadFiles();
	
	}
	
	// GUI
	public void UploadFiles() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		navigate_button.addActionListener(this); // File Navigator Functionality Added To This Button
		

		// Create 'List Files' Panel
		list_panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension (450, 600);
			};
		};
		this.getContentPane().add(list_panel);
		list_panel.setBorder(BorderFactory.createTitledBorder("Lista de Archivos Seleccionados"));
		
		
		
		// Process Button
		process_button = new JButton("Procesar");
		list_panel.add(process_button, BorderLayout.SOUTH);
		process_button.addActionListener(this);
		if(files == null) process_button.setEnabled(false);
		
		
		// Add Panels to Frame and Assign Them a Position
		frame.setVisible(true);
		frame.setSize(450, 680);
		frame.setMinimumSize(new Dimension(450, 480));
		frame.setLocationRelativeTo(null);
		
		frame.add(load_panel, BorderLayout.NORTH);
		frame.add(list_panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == navigate_button) {
			JFileChooser file_chooser = new JFileChooser();
			
			file_chooser.setMultiSelectionEnabled(true); // Multiple Files Selection is Enabled
			int result = file_chooser.showSaveDialog(this);
			files = file_chooser.getSelectedFiles();
			
			
			// File List Shows Files
			if (result == JFileChooser.APPROVE_OPTION) {
				
				process_button.setEnabled(true);
				
				listFiles = new JList<>(files);
				
				listFiles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				scroll_pane = new JScrollPane(listFiles);
				list_panel.add(scroll_pane, BorderLayout.NORTH);
				
				System.out.println(files.length);
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
				
			}
		}
			
	}
	
}
