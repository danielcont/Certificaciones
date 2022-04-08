package upload;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.compress.utils.FileNameUtils;

public class FileListAccessory extends JComponent implements PropertyChangeListener {

    private File file = null;
    private DefaultListModel model;
    private JList list;
    private JButton removeItem;

    public FileListAccessory(JFileChooser chooser) {
        chooser.addPropertyChangeListener(this);

        model = new DefaultListModel();
        list = new JList(model);
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(350, 250));

        removeItem = createRemoveItemButton();
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());
        add(pane);
        add(removeItem, BorderLayout.SOUTH);

    }

    public DefaultListModel getModel() {
        return model;
    }

    private void addFileToList() {
    	boolean isRepeated = false;
    	String extension = FileNameUtils.getExtension(file.toString()); // Get the Extension Name
    	
    	// Accepts Supported file (EXCEL FILES ONLY)
    	if(extension.equals("xls") || extension.equals("xlsm") || extension.equals("xml") || extension.equals("xlsx") || extension.equals("csv")) {
    		// Avoid Repeated Files
    		for(int i = 0; i < model.getSize(); i++) {
        		if(model.getElementAt(i).toString().equals(file.toString())) {
        			isRepeated = true;
        			break;
        		}
        	}
        	
        	if(!isRepeated) model.addElement(file);
        }	
    }

    private void removeFileFromList() {
        if (list.getSelectedIndex() != -1) {
             model.remove(list.getSelectedIndex());
        }
    }

    private JButton createRemoveItemButton() {
        JButton button = new JButton("Remove");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeFileFromList();
            }
        });
        return button;
    }

    public void propertyChange(PropertyChangeEvent e) {
        boolean update = false;
        String prop = e.getPropertyName();

        //If the directory changed, don't do anything
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
            file = null;
            update = true;
            //If a file became selected, find out which one.
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            file = (File) e.getNewValue();
            update = true;
        }

        if (update && file != null) {
            addFileToList();
        }
    }
}