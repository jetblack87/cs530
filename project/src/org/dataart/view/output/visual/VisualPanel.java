package org.dataart.view.output.visual;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import org.dataart.view.AMainWindowSubpanel;
import org.dataart.view.ASubpanel;
import org.dataart.view.output.visual.subpanels.BubbleGraphSubpanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VisualPanel extends AMainWindowSubpanel {
	
	/**
	 * Create the panel.
	 */
	public VisualPanel() {
		PANEL_TITLE = "Visual";
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel outputTypePanel = new JPanel();
		add(outputTypePanel, BorderLayout.NORTH);
		GridBagLayout gbl_outputTypePanel = new GridBagLayout();
		gbl_outputTypePanel.columnWidths = new int[]{0, 0, 0};
		gbl_outputTypePanel.rowHeights = new int[]{0, 0};
		gbl_outputTypePanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_outputTypePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		outputTypePanel.setLayout(gbl_outputTypePanel);
		
		JLabel lblOutputType = new JLabel("Output Type:");
		GridBagConstraints gbc_lblOutputType = new GridBagConstraints();
		gbc_lblOutputType.insets = new Insets(0, 0, 0, 5);
		gbc_lblOutputType.anchor = GridBagConstraints.EAST;
		gbc_lblOutputType.gridx = 0;
		gbc_lblOutputType.gridy = 0;
		outputTypePanel.add(lblOutputType, gbc_lblOutputType);
		
		final JComboBox<ASubpanel> outputTypeComboBox = new JComboBox<ASubpanel>();
		GridBagConstraints gbc_outputTypeComboBox = new GridBagConstraints();
		gbc_outputTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_outputTypeComboBox.gridx = 1;
		gbc_outputTypeComboBox.gridy = 0;
		outputTypePanel.add(outputTypeComboBox, gbc_outputTypeComboBox);
		
		DefaultComboBoxModel<ASubpanel> outputTypeComboBoxModel = new DefaultComboBoxModel<ASubpanel>();
		outputTypeComboBox.setModel(outputTypeComboBoxModel);
		
		JButton btnPlay = new JButton("Play");
		add(btnPlay, BorderLayout.SOUTH);
		
		final JPanel cardLayoutPanel = new JPanel();
		add(cardLayoutPanel, BorderLayout.CENTER);
		cardLayoutPanel.setLayout(new CardLayout(0, 0));
		
		loadPanels(cardLayoutPanel, outputTypeComboBoxModel);
		
		outputTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    CardLayout cl = (CardLayout)(cardLayoutPanel.getLayout());
			    cl.show(cardLayoutPanel, outputTypeComboBox.getSelectedItem().toString());
			}
		});
	}
	
	private void loadPanels(JPanel cardLayoutPanel, DefaultComboBoxModel<ASubpanel> outputTypeComboBoxModel) {
		new BubbleGraphSubpanel().addThisPanelToCardLayoutPanelAndComboBoxModel(cardLayoutPanel, outputTypeComboBoxModel);
	}
}
