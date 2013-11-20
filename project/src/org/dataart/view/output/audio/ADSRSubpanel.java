package org.dataart.view.output.audio;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class ADSRSubpanel extends AAudioSubpanel {
	private final JComboBox<String> attackFieldComboBox;
	private final JComboBox<String> decayFieldComboBox;
	private final JComboBox<String> susteinFieldComboBox;
	private final JComboBox<String> releaseFieldComboBox;
	private final JComboBox<String> volumeFieldComboBox;
	private final JComboBox<String> noteFieldComboBox;
	private JSpinner tempoSpinner;

	private final DefaultComboBoxModel<String> attackFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> decayFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> susteinFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> releaseFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> volumeFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> noteFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final SpinnerNumberModel tempoSpinnerModel = new SpinnerNumberModel();

	private static int MIN_TEMPO = 1;
	private static int MAX_TEMPO = 200;
	
	public ADSRSubpanel() {
		PANEL_TITLE = "ADSR (not implemented)";

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblAttack = new JLabel("Attack:");
		GridBagConstraints gbc_lblAttack = new GridBagConstraints();
		gbc_lblAttack.anchor = GridBagConstraints.EAST;
		gbc_lblAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttack.gridx = 0;
		gbc_lblAttack.gridy = 0;
		add(lblAttack, gbc_lblAttack);

		attackFieldComboBox = new JComboBox<String>();
		attackFieldComboBox.setEnabled(false);
		GridBagConstraints gbc_attackFieldComboBox = new GridBagConstraints();
		gbc_attackFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_attackFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_attackFieldComboBox.gridx = 1;
		gbc_attackFieldComboBox.gridy = 0;
		add(attackFieldComboBox, gbc_attackFieldComboBox);
		attackFieldComboBox.setModel(attackFieldComboBoxModel);

		JLabel lblDecay = new JLabel("Decay:");
		GridBagConstraints gbc_lblDecay = new GridBagConstraints();
		gbc_lblDecay.anchor = GridBagConstraints.EAST;
		gbc_lblDecay.insets = new Insets(0, 0, 5, 5);
		gbc_lblDecay.gridx = 0;
		gbc_lblDecay.gridy = 1;
		add(lblDecay, gbc_lblDecay);

		decayFieldComboBox = new JComboBox<String>();
		decayFieldComboBox.setEnabled(false);
		GridBagConstraints gbc_decayFieldComboBox = new GridBagConstraints();
		gbc_decayFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_decayFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_decayFieldComboBox.gridx = 1;
		gbc_decayFieldComboBox.gridy = 1;
		add(decayFieldComboBox, gbc_decayFieldComboBox);
		decayFieldComboBox.setModel(decayFieldComboBoxModel);

		JLabel lblSustein = new JLabel("Sustein:");
		GridBagConstraints gbc_lblSustein = new GridBagConstraints();
		gbc_lblSustein.anchor = GridBagConstraints.EAST;
		gbc_lblSustein.insets = new Insets(0, 0, 5, 5);
		gbc_lblSustein.gridx = 0;
		gbc_lblSustein.gridy = 2;
		add(lblSustein, gbc_lblSustein);

		susteinFieldComboBox = new JComboBox<String>();
		susteinFieldComboBox.setEnabled(false);
		GridBagConstraints gbc_susteinFieldComboBox = new GridBagConstraints();
		gbc_susteinFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_susteinFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_susteinFieldComboBox.gridx = 1;
		gbc_susteinFieldComboBox.gridy = 2;
		add(susteinFieldComboBox, gbc_susteinFieldComboBox);
		susteinFieldComboBox.setModel(susteinFieldComboBoxModel);

		JLabel lblRelease = new JLabel("Release:");
		GridBagConstraints gbc_lblRelease = new GridBagConstraints();
		gbc_lblRelease.anchor = GridBagConstraints.EAST;
		gbc_lblRelease.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelease.gridx = 0;
		gbc_lblRelease.gridy = 3;
		add(lblRelease, gbc_lblRelease);

		releaseFieldComboBox = new JComboBox<String>();
		releaseFieldComboBox.setEnabled(false);
		GridBagConstraints gbc_releaseFieldComboBox = new GridBagConstraints();
		gbc_releaseFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_releaseFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_releaseFieldComboBox.gridx = 1;
		gbc_releaseFieldComboBox.gridy = 3;
		add(releaseFieldComboBox, gbc_releaseFieldComboBox);
		releaseFieldComboBox.setModel(releaseFieldComboBoxModel);

		JLabel lblVolume = new JLabel("Velocity/Volume:");
		GridBagConstraints gbc_lblVolume = new GridBagConstraints();
		gbc_lblVolume.anchor = GridBagConstraints.EAST;
		gbc_lblVolume.insets = new Insets(0, 0, 5, 5);
		gbc_lblVolume.gridx = 0;
		gbc_lblVolume.gridy = 4;
		add(lblVolume, gbc_lblVolume);

		volumeFieldComboBox = new JComboBox<String>();
		volumeFieldComboBox.setEnabled(false);
		GridBagConstraints gbc_volumeFieldComboBox = new GridBagConstraints();
		gbc_volumeFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_volumeFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_volumeFieldComboBox.gridx = 1;
		gbc_volumeFieldComboBox.gridy = 4;
		add(volumeFieldComboBox, gbc_volumeFieldComboBox);
		volumeFieldComboBox.setModel(volumeFieldComboBoxModel);

		JLabel lblNote = new JLabel("Note:");
		GridBagConstraints gbc_lblNote = new GridBagConstraints();
		gbc_lblNote.anchor = GridBagConstraints.EAST;
		gbc_lblNote.insets = new Insets(0, 0, 5, 5);
		gbc_lblNote.gridx = 0;
		gbc_lblNote.gridy = 5;
		add(lblNote, gbc_lblNote);

		noteFieldComboBox = new JComboBox<String>();
		noteFieldComboBox.setEnabled(false);
		GridBagConstraints gbc_noteFieldComboBox = new GridBagConstraints();
		gbc_noteFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_noteFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_noteFieldComboBox.gridx = 1;
		gbc_noteFieldComboBox.gridy = 5;
		add(noteFieldComboBox, gbc_noteFieldComboBox);
		noteFieldComboBox.setModel(noteFieldComboBoxModel);

		JLabel lblTempo = new JLabel("Tempo:");
		GridBagConstraints gbc_lblTempo = new GridBagConstraints();
		gbc_lblTempo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTempo.anchor = GridBagConstraints.EAST;
		gbc_lblTempo.gridx = 0;
		gbc_lblTempo.gridy = 6;
		add(lblTempo, gbc_lblTempo);

		// FIXME: Need to figure out how to not go beyond boundaries
		tempoSpinner = new JSpinner();
		tempoSpinner.setEnabled(false);
		GridBagConstraints gbc_tempoSpinner = new GridBagConstraints();
		gbc_tempoSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_tempoSpinner.gridx = 1;
		gbc_tempoSpinner.gridy = 6;
		add(tempoSpinner, gbc_tempoSpinner);
		tempoSpinner.setModel(tempoSpinnerModel);
		tempoSpinnerModel.setValue(MIN_TEMPO);
		tempoSpinnerModel.setMinimum(MIN_TEMPO);
		tempoSpinnerModel.setMaximum(MAX_TEMPO);
	}

	@Override
	public long play() {
		return 0;
	}
	@Override
	public void pause() {
	}
	@Override
	public void stop() {
	}

	@Override
	protected void updatePanel() {
		for (String dataType : data.get(0).keySet()) {
			attackFieldComboBoxModel.addElement(dataType);
			decayFieldComboBoxModel.addElement(dataType);
			susteinFieldComboBoxModel.addElement(dataType);
			releaseFieldComboBoxModel.addElement(dataType);
			volumeFieldComboBoxModel.addElement(dataType);
			noteFieldComboBoxModel.addElement(dataType);
		}
	}
}
