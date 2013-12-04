package org.dataart.view;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import org.dataart.model.Data;

@SuppressWarnings("serial")
public abstract class ASubpanel extends JPanel {
	public String PANEL_TITLE;
	
	protected Data data;
	protected ArrayList<ASubpanel> subpanels = new ArrayList<ASubpanel>();
	
	@Override
	public String toString(){
		return PANEL_TITLE;
	}
	
	public void addThisPanelToCardLayoutPanelAndComboBoxModel(JPanel cardLayoutPanel, DefaultComboBoxModel<ASubpanel> comboBoxModel) {
		cardLayoutPanel.add(this, PANEL_TITLE);
		comboBoxModel.addElement(this);
	}

	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Data data) {
		this.data = data;
		updatePanel();
		for(ASubpanel subpanel : subpanels) {
			subpanel.setData(data);
		}
	}
	
	/**
	 * Updates the panel using the data
	 */
	protected abstract void updatePanel();
}
