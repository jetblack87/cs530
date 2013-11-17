package org.dataart.view.output;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.dataart.view.AMainWindowSubpanel;
import org.dataart.view.ASubpanel;
import org.dataart.view.output.audio.subpanels.AAudioSubpanel;
import org.dataart.view.output.audio.subpanels.ADSRSubpanel;
import org.dataart.view.output.audio.subpanels.BasicMIDISubpanel;

import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class AudioSubpanel extends AMainWindowSubpanel {
	private final JComboBox<ASubpanel> outputTypeComboBox;
	private final DefaultComboBoxModel<ASubpanel> outputTypeComboBoxModel;
	private final JProgressBar progressBar;
	private Timer timer;
	private JButton btnPlayPause;
	
	/**
	 * Create the panel.
	 */
	public AudioSubpanel() {
		PANEL_TITLE = "Audio";
		
		setLayout(new BorderLayout(0, 0));
		
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
		
		outputTypeComboBox = new JComboBox<ASubpanel>();
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
		
		final JPanel cardLayoutPanel = new JPanel();
		add(cardLayoutPanel, BorderLayout.CENTER);
		cardLayoutPanel.setLayout(new CardLayout(0, 0));
				
		JPanel audioControlPanel = new JPanel();
		add(audioControlPanel, BorderLayout.SOUTH);
		audioControlPanel.setLayout(new BorderLayout(0, 0));
		
		btnPlayPause = new JButton("Play");
		btnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnPlayPause.getText().equals("Play")) {
					long audioLength = ((AAudioSubpanel)outputTypeComboBoxModel.getSelectedItem()).play();
					progressBar.setMaximum((int)(audioLength / 1000));
					timer = new Timer();
				    timer.schedule(new UpdateProgressBarTask(), 0, 1);

				    btnPlayPause.setText("Pause");
				} else {
					((AAudioSubpanel)outputTypeComboBoxModel.getSelectedItem()).pause();

					timer.cancel();
					
					btnPlayPause.setText("Play");
				}
			}
		});
		audioControlPanel.add(btnPlayPause, BorderLayout.EAST);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		audioControlPanel.add(progressBar, BorderLayout.CENTER);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		audioControlPanel.add(btnStop, BorderLayout.WEST);
		
		outputTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    CardLayout cl = (CardLayout)(cardLayoutPanel.getLayout());
			    cl.show(cardLayoutPanel, outputTypeComboBox.getSelectedItem().toString());
			}
		});

		
		loadPanels(cardLayoutPanel, outputTypeComboBoxModel);
	}
	
	private void loadPanels(JPanel cardLayoutPanel, DefaultComboBoxModel<ASubpanel> outputTypeComboBoxModel) {
		subpanels.add(new BasicMIDISubpanel());
		subpanels.add(new ADSRSubpanel());
		
		for(ASubpanel subpanel : subpanels) {
			subpanel.addThisPanelToCardLayoutPanelAndComboBoxModel(cardLayoutPanel, outputTypeComboBoxModel);
		}		
	}
	
	@Override
	protected void updatePanel() {
		// DO NOTHING
	}

	private void stop() {
		timer.cancel();
		((AAudioSubpanel)outputTypeComboBoxModel.getSelectedItem()).stop();				
		progressBar.setValue(progressBar.getMinimum());
		btnPlayPause.setText("Play");
	}
	
	class UpdateProgressBarTask extends TimerTask {
		public void run() {
			if(progressBar.getValue() < progressBar.getMaximum()) {
				progressBar.setValue(progressBar.getValue()+1);				
			}
			else {
				stop();
			}
		}
	}
}
