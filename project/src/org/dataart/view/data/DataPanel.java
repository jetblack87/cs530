package org.dataart.view.data;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.dataart.view.IMainWindowSubpanel;

@SuppressWarnings("serial")
public class DataPanel extends JPanel implements IMainWindowSubpanel {

	private final static String PANEL_TITLE = "Data";
	
	/**
	 * Create the panel.
	 */
	public DataPanel() {

	}
	
	@Override
	public void addThisPanel(JTabbedPane tabbedPane, JMenu playMenu){
		tabbedPane.add(PANEL_TITLE, this);
	}

}
