package org.dataart.view.visual;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.dataart.view.IMainWindowSubpanel;

@SuppressWarnings("serial")
public class VisualPanel extends JPanel implements IMainWindowSubpanel {

	private final static String PANEL_TITLE = "Visual";
	
	/**
	 * Create the panel.
	 */
	public VisualPanel() {

	}
	
	@Override
	public void addThisPanel(JTabbedPane tabbedPane, JMenu playMenu){
		tabbedPane.add(PANEL_TITLE, this);
		playMenu.add(new JMenuItem(PANEL_TITLE));
	}
}
