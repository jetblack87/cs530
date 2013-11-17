package org.dataart.view.imprt;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import org.dataart.model.Data;
import org.dataart.view.ASubpanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

@SuppressWarnings("serial")
public class ImportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private int OK_CANCEL_OPTION = JOptionPane.CANCEL_OPTION;
	
	/**
	 * Create the dialog.
	 */
	public ImportDialog(final Data data) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		final JPanel cardLayoutPanel = new JPanel();
		contentPanel.add(cardLayoutPanel, BorderLayout.CENTER);
		cardLayoutPanel.setLayout(new CardLayout(0, 0));
		final JComboBox<ASubpanel> sourceTypesComboBox;
		final DefaultComboBoxModel<ASubpanel> sourceTypeComboBoxModel = new DefaultComboBoxModel<ASubpanel>();
		{
			JPanel sourceTypePanel = new JPanel();
			contentPanel.add(sourceTypePanel, BorderLayout.NORTH);
			sourceTypePanel.setLayout(new BoxLayout(sourceTypePanel, BoxLayout.X_AXIS));
			{
				JLabel lblSourceType = new JLabel("Source Type: ");
				sourceTypePanel.add(lblSourceType);
			}
			{
				sourceTypesComboBox = new JComboBox<ASubpanel>();
				sourceTypesComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    CardLayout cl = (CardLayout)(cardLayoutPanel.getLayout());
					    cl.show(cardLayoutPanel, sourceTypesComboBox.getSelectedItem().toString());
					}
				});
				sourceTypesComboBox.setModel(sourceTypeComboBoxModel);
				sourceTypePanel.add(sourceTypesComboBox);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton importButton = new JButton("Import");
				importButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						importData(data, (AImportSubpanel)sourceTypesComboBox.getSelectedItem());
					}
				});
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
		
		loadPanels(cardLayoutPanel, sourceTypeComboBoxModel);
	}
	
	private void loadPanels(JPanel cardLayoutPanel, DefaultComboBoxModel<ASubpanel> sourceTypeComboBoxModel) {
		new FilesystemSubpanel().addThisPanelToCardLayoutPanelAndComboBoxModel(cardLayoutPanel, sourceTypeComboBoxModel);
		new LetterCountSubpanel().addThisPanelToCardLayoutPanelAndComboBoxModel(cardLayoutPanel, sourceTypeComboBoxModel);
	}
	
	private void cancelImport() {
		OK_CANCEL_OPTION = JOptionPane.CANCEL_OPTION;
		this.dispose();
	}
	
	private void importData(Data data, AImportSubpanel panel) {
		Data returnData = panel.importData();
		data.clear();
		data.addAll(returnData);
		data.setDataSource(returnData.getDataSource());
		OK_CANCEL_OPTION = JOptionPane.OK_OPTION;
		this.dispose();
	}
	
	public int showImportDialog(Component parentComponent) {
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		return OK_CANCEL_OPTION;
	}
}
