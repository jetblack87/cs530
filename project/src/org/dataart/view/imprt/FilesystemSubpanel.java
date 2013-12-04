package org.dataart.view.imprt;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Cursor;

import org.dataart.model.Data;
import org.dataart.util.Finder;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FilesystemSubpanel extends AImportSubpanel {

	private JTextField textDirectoryBase;
	private JTextField textFileselectPattern;
	
	private final JList<Path> filelist;
	private final DefaultListModel<Path> filelistModel = new DefaultListModel<Path>();
		
	/**
	 * Create the panel.
	 */
	public FilesystemSubpanel() {
		PANEL_TITLE = "Filesystem";
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel inputPanel = new JPanel();
		add(inputPanel, BorderLayout.NORTH);
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_inputPanel.rowHeights = new int[]{0, 0, 0};
		gbl_inputPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
		
		JLabel lblDirectoryBase = new JLabel("Directory Base:");
		GridBagConstraints gbc_lblDirectoryBase = new GridBagConstraints();
		gbc_lblDirectoryBase.anchor = GridBagConstraints.EAST;
		gbc_lblDirectoryBase.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirectoryBase.gridx = 0;
		gbc_lblDirectoryBase.gridy = 0;
		inputPanel.add(lblDirectoryBase, gbc_lblDirectoryBase);
		
		textDirectoryBase = new JTextField();
		GridBagConstraints gbc_textDirectoryBase = new GridBagConstraints();
		gbc_textDirectoryBase.insets = new Insets(0, 0, 5, 5);
		gbc_textDirectoryBase.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDirectoryBase.gridx = 1;
		gbc_textDirectoryBase.gridy = 0;
		inputPanel.add(textDirectoryBase, gbc_textDirectoryBase);
		textDirectoryBase.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse for Folder");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	chooser.setCurrentDirectory(new File(textDirectoryBase.getText()));
			    int returnVal = chooser.showOpenDialog(((JComponent)arg0.getSource()).getTopLevelAncestor());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	textDirectoryBase.setText(chooser.getSelectedFile().getAbsolutePath());
			    }
			}
		});
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowse.gridx = 2;
		gbc_btnBrowse.gridy = 0;
		inputPanel.add(btnBrowse, gbc_btnBrowse);
		
		JLabel lblFileSelectPattern = new JLabel("File select pattern:");
		GridBagConstraints gbc_lblFileSelectPattern = new GridBagConstraints();
		gbc_lblFileSelectPattern.anchor = GridBagConstraints.EAST;
		gbc_lblFileSelectPattern.insets = new Insets(0, 0, 0, 5);
		gbc_lblFileSelectPattern.gridx = 0;
		gbc_lblFileSelectPattern.gridy = 1;
		inputPanel.add(lblFileSelectPattern, gbc_lblFileSelectPattern);
		
		textFileselectPattern = new JTextField("*");
		GridBagConstraints gbc_textFileselectPattern = new GridBagConstraints();
		gbc_textFileselectPattern.insets = new Insets(0, 0, 0, 5);
		gbc_textFileselectPattern.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFileselectPattern.gridx = 1;
		gbc_textFileselectPattern.gridy = 1;
		inputPanel.add(textFileselectPattern, gbc_textFileselectPattern);
		textFileselectPattern.setColumns(10);
		
		JButton btnFetch = new JButton("Fetch Files");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((Component) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					Finder finder = new Finder(textFileselectPattern.getText(), Finder.FIND_TYPES.FILES);
					Files.walkFileTree(Paths.get(textDirectoryBase.getText()), finder);
					for(Path path : finder.done()) {
						filelistModel.addElement(path);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				((Component) e.getSource()).setCursor(Cursor.getDefaultCursor());
			}
		});
		GridBagConstraints gbc_btnFetch = new GridBagConstraints();
		gbc_btnFetch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFetch.gridx = 2;
		gbc_btnFetch.gridy = 1;
		inputPanel.add(btnFetch, gbc_btnFetch);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Path selectedPath : filelist.getSelectedValuesList()) {					
					filelistModel.removeElement(selectedPath);
				}
			}
		});
		add(btnRemove, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		filelist = new JList<Path>();
		scrollPane.setViewportView(filelist);
		filelist.setModel(filelistModel);

	}

	@Override
	public String toString()
	{
		return PANEL_TITLE;
	}

	@Override
	public Data importData() {
		Data data = new Data();
		
		data.setDataSource(PANEL_TITLE);

		for(int i = 0; i < filelistModel.size(); i++) {
			Path currPath = filelistModel.get(i);
			HashMap<String, Object> newElement = new HashMap<String, Object>();
			
			try {
			// Path
			newElement.put("Filename", currPath.getFileName());
			// Path
			newElement.put("Filepath", currPath);
			// Long
			newElement.put("Filesize", new Long(Files.size(currPath)));

			// String
			newElement.put("File owner", Files.getOwner(currPath).getName());
			
			// Calendar
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTimeInMillis(Files.getLastModifiedTime(currPath).toMillis());
			newElement.put("Last Modified Time", cal);		
			
			// Calendar
			cal = GregorianCalendar.getInstance();
			cal.setTimeInMillis(((FileTime)Files.getAttribute(currPath, "lastAccessTime")).toMillis());
			newElement.put("Last Access Time", cal);
			
			// Calendar
			cal = GregorianCalendar.getInstance();
			cal.setTimeInMillis(((FileTime)Files.getAttribute(currPath, "creationTime")).toMillis());
			newElement.put("Creation Time", cal);
			} catch (Exception e) {
				System.err.println("Exception thrown. Will not import the following: " + currPath);
				e.printStackTrace();
			}
			
			data.add(newElement);
		}

		return data;
	}

	@Override
	protected void updatePanel() {
		// DO NOTHING
	}
	
}
