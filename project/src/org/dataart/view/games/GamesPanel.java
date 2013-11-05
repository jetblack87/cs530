package org.dataart.view.games;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.dataart.view.IMainWindowSubpanel;

@SuppressWarnings("serial")
public class GamesPanel extends JPanel implements IMainWindowSubpanel {

	private final static String PANEL_TITLE = "Games";
	
	/**
	 * Create the panel.
	 */
	public GamesPanel() {

	}

	@Override
	public void addThisPanel(JTabbedPane tabbedPane, JMenu playMenu){
		tabbedPane.add(PANEL_TITLE, this);
		playMenu.add(new JMenuItem(PANEL_TITLE));
	}
	
}
