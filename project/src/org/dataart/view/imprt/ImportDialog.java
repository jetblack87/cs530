package org.dataart.view.imprt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ImportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ImportDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel sourceTypePanel = new JPanel();
			contentPanel.add(sourceTypePanel, BorderLayout.NORTH);
			sourceTypePanel.setLayout(new BoxLayout(sourceTypePanel, BoxLayout.X_AXIS));
			{
				JLabel lblSourceType = new JLabel("Source Type: ");
				sourceTypePanel.add(lblSourceType);
			}
			{
				JComboBox sourceTypesComboBox = new JComboBox();
				sourceTypePanel.add(sourceTypesComboBox);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton importButton = new JButton("Import");
				importButton.setActionCommand("Import");
				buttonPane.add(importButton);
				getRootPane().setDefaultButton(importButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelImport();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void cancelImport() {
		this.dispose();
	}

}
