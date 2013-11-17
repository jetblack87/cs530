package org.dataart.view.data;

import javax.swing.JMenu;
import javax.swing.JTabbedPane;

import org.dataart.view.AMainWindowSubpanel;

@SuppressWarnings("serial")
public class DataPanel extends AMainWindowSubpanel {	
	/**
	 * Create the panel.
	 */
	public DataPanel() {
		PANEL_TITLE = "Data (not implemented)";
	}

	@Override
	protected void updatePanel() {
	}
	
	@Override
	public void addThisPanelToTabbedPaneAndJMenu(JTabbedPane tabbedPane, JMenu playMenu){
		tabbedPane.add(PANEL_TITLE, this);
	}
}
