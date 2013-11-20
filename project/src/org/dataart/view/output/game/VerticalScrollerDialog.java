package org.dataart.view.output.game;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.dataart.model.Data;

@SuppressWarnings("serial")
public class VerticalScrollerDialog extends JDialog {

	private Data data;
	
	private static final int PLAYER_Y = 200;
	
	/**
	 * Create the dialog.
	 */
	public VerticalScrollerDialog(Data data, String enemyStrengthField, String enemySpeedField, String enemyWeaponStrengthField) {
		this.data = data;
		
		// Set to full screen
		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
		setLayout(new BorderLayout(0, 0));
		final JPanel game = new JPanel();
		add(game, BorderLayout.CENTER);
	}
}
