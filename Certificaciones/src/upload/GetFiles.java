/*
 * This Class is made to import files from the computer and get the data from it
 */

package upload;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI Components Libraries are imported
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GetFiles extends JFrame implements ActionListener {
	
	// JFrame
	private JFrame frame = new JFrame("Cargar Archivos");
	// JButton
	private JButton navigate_button; // Button To Navigate Folder
	// JPanel
	private JPanel list_panel, load_panel;
	
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
		
		// Label
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
		
		// Labels
		
		
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
		if(e.getSource() == navigate_button) {
			JFileChooser file_chooser = new JFileChooser();
			
			file_chooser.showOpenDialog(null); // Select File to Open
			
		}
		
	}
	
	
}
