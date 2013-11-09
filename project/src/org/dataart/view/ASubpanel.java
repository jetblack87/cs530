package org.dataart.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class ASubpanel extends JPanel {
	public String PANEL_TITLE;
	
	@Override
	public String toString(){
		return PANEL_TITLE;
	}
	
	public void addThisPanelToCardLayoutPanelAndComboBoxModel(JPanel cardLayoutPanel, DefaultComboBoxModel<ASubpanel> comboBoxModel) {
		cardLayoutPanel.add(this, PANEL_TITLE);
		comboBoxModel.addElement(this);
	}
}
