package org.dataart.view.output;

import org.dataart.view.AMainWindowSubpanel;
import org.dataart.view.ASubpanel;
import org.dataart.view.output.game.VerticalScrollerSubpanel;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.dataart.view.output.game.AGameSubpanel;

@SuppressWarnings("serial")
public class GameSubpanel extends AMainWindowSubpanel {

	private DefaultComboBoxModel<ASubpanel> outputTypeComboBoxModel;

	/**
	 * Create the panel.
	 */
	public GameSubpanel() {
		PANEL_TITLE = "Games (not implemented)";

		setLayout(new BorderLayout(0, 0));
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((AGameSubpanel)outputTypeComboBoxModel.getSelectedItem()).play();
			}
		});
		add(btnPlay, BorderLayout.SOUTH);
		
		JPanel outputTypePanel = new JPanel();
		add(outputTypePanel, BorderLayout.NORTH);
		GridBagLayout gbl_outputTypePanel = new GridBagLayout();
		gbl_outputTypePanel.columnWidths = new int[]{0, 0, 0};
		gbl_outputTypePanel.rowHeights = new int[]{0, 0, 0};
		gbl_outputTypePanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_outputTypePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		outputTypePanel.setLayout(gbl_outputTypePanel);
		
		JLabel lblOutputType = new JLabel("Output Type:");
		GridBagConstraints gbc_lblOutputType = new GridBagConstraints();
		gbc_lblOutputType.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutputType.anchor = GridBagConstraints.EAST;
		gbc_lblOutputType.gridx = 0;
		gbc_lblOutputType.gridy = 0;
		outputTypePanel.add(lblOutputType, gbc_lblOutputType);
		
		JComboBox<ASubpanel> outputTypeComboBox = new JComboBox<ASubpanel>();
		GridBagConstraints gbc_outputTypeComboBox = new GridBagConstraints();
		gbc_outputTypeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_outputTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_outputTypeComboBox.gridx = 1;
		gbc_outputTypeComboBox.gridy = 0;
		outputTypePanel.add(outputTypeComboBox, gbc_outputTypeComboBox);
		
		outputTypeComboBoxModel = new DefaultComboBoxModel<ASubpanel>();
		outputTypeComboBox.setModel(outputTypeComboBoxModel);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		outputTypePanel.add(separator, gbc_separator);
		
		JPanel cardLayoutPanel = new JPanel();
		add(cardLayoutPanel, BorderLayout.CENTER);
		cardLayoutPanel.setLayout(new CardLayout(0, 0));
		
		loadPanels(cardLayoutPanel, outputTypeComboBoxModel);

	}

	private void loadPanels(JPanel cardLayoutPanel, DefaultComboBoxModel<ASubpanel> outputTypeComboBoxModel) {
		subpanels.add(new VerticalScrollerSubpanel());
		
		for(ASubpanel subpanel : subpanels) {
			subpanel.addThisPanelToCardLayoutPanelAndComboBoxModel(cardLayoutPanel, outputTypeComboBoxModel);
		}
	}

	@Override
	protected void updatePanel() {
		// DO NOTHING
	}
}
