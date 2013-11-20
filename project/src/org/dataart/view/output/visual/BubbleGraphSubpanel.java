package org.dataart.view.output.visual;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;


@SuppressWarnings("serial")
public class BubbleGraphSubpanel extends AVisualSubpanel {

	JComboBox<String> xInputFieldComboBox;
	JComboBox<String> yInputFieldComboBox;
	JComboBox<String> zInputFieldComboBox;

	private DefaultComboBoxModel<String> xInputFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> yInputFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> zInputFieldComboBoxModel = new DefaultComboBoxModel<String>();

	public BubbleGraphSubpanel() {
		PANEL_TITLE = "Bubble Graph";

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblXInputField = new JLabel("X Input Field:");
		GridBagConstraints gbc_lblXInputField = new GridBagConstraints();
		gbc_lblXInputField.anchor = GridBagConstraints.EAST;
		gbc_lblXInputField.insets = new Insets(0, 0, 5, 5);
		gbc_lblXInputField.gridx = 0;
		gbc_lblXInputField.gridy = 0;
		add(lblXInputField, gbc_lblXInputField);

		xInputFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_xInputFieldComboBox = new GridBagConstraints();
		gbc_xInputFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_xInputFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_xInputFieldComboBox.gridx = 1;
		gbc_xInputFieldComboBox.gridy = 0;
		add(xInputFieldComboBox, gbc_xInputFieldComboBox);

		xInputFieldComboBox.setModel(xInputFieldComboBoxModel);

		JLabel lblYInputField = new JLabel("Y Input Field:");
		GridBagConstraints gbc_lblYInputField = new GridBagConstraints();
		gbc_lblYInputField.anchor = GridBagConstraints.EAST;
		gbc_lblYInputField.insets = new Insets(0, 0, 5, 5);
		gbc_lblYInputField.gridx = 0;
		gbc_lblYInputField.gridy = 1;
		add(lblYInputField, gbc_lblYInputField);

		yInputFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_yInputFieldComboBox = new GridBagConstraints();
		gbc_yInputFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_yInputFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_yInputFieldComboBox.gridx = 1;
		gbc_yInputFieldComboBox.gridy = 1;
		add(yInputFieldComboBox, gbc_yInputFieldComboBox);

		yInputFieldComboBox.setModel(yInputFieldComboBoxModel);

		JLabel lblZInputField = new JLabel("Z Input Field:");
		GridBagConstraints gbc_lblZInputField = new GridBagConstraints();
		gbc_lblZInputField.anchor = GridBagConstraints.EAST;
		gbc_lblZInputField.insets = new Insets(0, 0, 0, 5);
		gbc_lblZInputField.gridx = 0;
		gbc_lblZInputField.gridy = 2;
		add(lblZInputField, gbc_lblZInputField);

		zInputFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_zInputFieldComboBox = new GridBagConstraints();
		gbc_zInputFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_zInputFieldComboBox.gridx = 1;
		gbc_zInputFieldComboBox.gridy = 2;
		add(zInputFieldComboBox, gbc_zInputFieldComboBox);

		zInputFieldComboBox.setModel(zInputFieldComboBoxModel);
	}

	@Override
	public void play() {
		BubbleGraphDialog bgd = new BubbleGraphDialog(data,
				(String) xInputFieldComboBox.getSelectedItem(),
				(String) yInputFieldComboBox.getSelectedItem(),
				(String) zInputFieldComboBox.getSelectedItem());
		bgd.setModal(true);
		bgd.setVisible(true);
	}

	@Override
	protected void updatePanel() {
		for (String dataType : data.get(0).keySet()) {
			xInputFieldComboBoxModel.addElement(dataType);
			yInputFieldComboBoxModel.addElement(dataType);
			zInputFieldComboBoxModel.addElement(dataType);
		}
	}
}
