package org.dataart.view.output.game;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class VerticalScrollerSubpanel extends AGameSubpanel {
	private final JComboBox<String> enemyStrengthFieldComboBox;
	private final JComboBox<String> enemySpeedFieldComboBox;
	private final JComboBox<String> enemyWeaponStrengthFieldComboBox;

	private final DefaultComboBoxModel<String> enemyStrengthFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> enemySpeedFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> enemyWeaponStrengthFieldComboBoxModel = new DefaultComboBoxModel<String>();

	public VerticalScrollerSubpanel() {
		PANEL_TITLE = "Vertical Scroller";

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblEnemyStrength = new JLabel("Enemy Strength:");
		GridBagConstraints gbc_lblEnemyStrength = new GridBagConstraints();
		gbc_lblEnemyStrength.anchor = GridBagConstraints.EAST;
		gbc_lblEnemyStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnemyStrength.gridx = 0;
		gbc_lblEnemyStrength.gridy = 0;
		add(lblEnemyStrength, gbc_lblEnemyStrength);

		enemyStrengthFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_attackFieldComboBox = new GridBagConstraints();
		gbc_attackFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_attackFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_attackFieldComboBox.gridx = 1;
		gbc_attackFieldComboBox.gridy = 0;
		add(enemyStrengthFieldComboBox, gbc_attackFieldComboBox);
		enemyStrengthFieldComboBox.setModel(enemyStrengthFieldComboBoxModel);

		JLabel lblEnemySpeed = new JLabel("Enemy Speed:");
		GridBagConstraints gbc_lblEnemySpeed = new GridBagConstraints();
		gbc_lblEnemySpeed.anchor = GridBagConstraints.EAST;
		gbc_lblEnemySpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnemySpeed.gridx = 0;
		gbc_lblEnemySpeed.gridy = 1;
		add(lblEnemySpeed, gbc_lblEnemySpeed);

		enemySpeedFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_decayFieldComboBox = new GridBagConstraints();
		gbc_decayFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_decayFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_decayFieldComboBox.gridx = 1;
		gbc_decayFieldComboBox.gridy = 1;
		add(enemySpeedFieldComboBox, gbc_decayFieldComboBox);
		enemySpeedFieldComboBox.setModel(enemySpeedFieldComboBoxModel);

		JLabel lblEnemyWeaponStrength = new JLabel("Enemy Weapon Strength:");
		GridBagConstraints gbc_lblEnemyWeaponStrength = new GridBagConstraints();
		gbc_lblEnemyWeaponStrength.anchor = GridBagConstraints.EAST;
		gbc_lblEnemyWeaponStrength.insets = new Insets(0, 0, 0, 5);
		gbc_lblEnemyWeaponStrength.gridx = 0;
		gbc_lblEnemyWeaponStrength.gridy = 2;
		add(lblEnemyWeaponStrength, gbc_lblEnemyWeaponStrength);

		enemyWeaponStrengthFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_susteinFieldComboBox = new GridBagConstraints();
		gbc_susteinFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_susteinFieldComboBox.gridx = 1;
		gbc_susteinFieldComboBox.gridy = 2;
		add(enemyWeaponStrengthFieldComboBox, gbc_susteinFieldComboBox);
		enemyWeaponStrengthFieldComboBox.setModel(enemyWeaponStrengthFieldComboBoxModel);
	}

	@Override
	public void play() {
		VerticalScrollerDialog bgd = new VerticalScrollerDialog(data,
				"",
				"",
				"");
		bgd.setModal(true);
		bgd.setVisible(true);
	}

	@Override
	protected void updatePanel() {
		for (String dataType : data.get(0).keySet()) {
			enemyStrengthFieldComboBoxModel.addElement(dataType);
			enemySpeedFieldComboBoxModel.addElement(dataType);
			enemyWeaponStrengthFieldComboBoxModel.addElement(dataType);
		}
	}
}
