package org.dataart.view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public abstract class AMainWindowSubpanel extends ASubpanel {

	public void addThisPanelToTabbedPaneAndJMenu(JTabbedPane tabbedPane, JMenu playMenu){
		tabbedPane.add(PANEL_TITLE, this);
		playMenu.add(new JMenuItem(PANEL_TITLE));
	}
}
