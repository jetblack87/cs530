package org.dataart.view.imprt;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JButton;
import org.dataart.model.Data;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class LetterCountSubpanel extends AImportSubpanel {
	private JTextPane txtpnData;

	/**
	 * Create the panel.
	 */
	public LetterCountSubpanel() {
		PANEL_TITLE = "Letter Count";
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel inputPanel = new JPanel();
		add(inputPanel, BorderLayout.NORTH);
		
		JButton btnBrowse = new JButton("Browse for File");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((Component) arg0.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			    JFileChooser chooser = new JFileChooser();
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    int returnVal = chooser.showOpenDialog(((JComponent)arg0.getSource()).getTopLevelAncestor());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
							FileInputStream fid = new FileInputStream(chooser.getSelectedFile());
						    Scanner scan = new Scanner(fid);  
						    scan.useDelimiter("\\Z");  
						    txtpnData.setText(scan.next());  
					    } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    
				((Component) arg0.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		inputPanel.setLayout(new BorderLayout(0, 0));
		inputPanel.add(btnBrowse);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		txtpnData = new JTextPane();
		scrollPane.setViewportView(txtpnData);

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
		
		List<String> alphabet = Arrays.asList(new String[] { "a", "b", "c", "d", "e", "f",
				"g", "g", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" });

		HashSet<String> foundCharacters = new HashSet<String>();
		
		
		for(String line : txtpnData.getText().split("\\n")) {
			HashMap<String, Object> newElement = new HashMap<String, Object>();
			
			for(String character : line.split("")) {
				String lowerCase = character.toLowerCase();
				if(alphabet.contains(lowerCase)) {
					foundCharacters.add(lowerCase);
					if(newElement.get(lowerCase) != null) {
						newElement.put(lowerCase, (Integer)newElement.get(lowerCase) + 1);											
					} else {
						newElement.put(lowerCase, new Integer(1));						
					}
				}
			}
			
			data.add(newElement);
		}
		
		for(HashMap<String, Object> currElem : data) {
			for(String foundCharacter : foundCharacters) {
				if(currElem.get(foundCharacter) == null) {
					currElem.put(foundCharacter, new Integer(0));
				}
			}
		}

		return data;
	}

	@Override
	protected void updatePanel() {
		// DO NOTHING
	}
	
}
