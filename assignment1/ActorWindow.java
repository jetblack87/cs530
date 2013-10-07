/*
 * Mark Albrecht
 * mwa29@drexel.edu
 * CS530:DUI, Assignment 1
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ActorWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public static final int OK_OPTION = JOptionPane.OK_OPTION;
	public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;

	int okOrCancel = CANCEL_OPTION;
	private JTextField txtName = new JTextField();
	private final JComboBox cmbExpression = new JComboBox();
	private final JComboBox cmbShirt = new JComboBox();
	private final JComboBox cmbPants = new JComboBox();
	private final JSpinner spnHeight = new JSpinner();
	private final JSpinner spnWidth = new JSpinner();

	private final PreviewPane previewPane = new PreviewPane();

	/**
	 * Create the dialog.
	 */
	public ActorWindow(JFrame owner) {
		super(owner);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancel();
			}
		});
		setTitle("Actor");
		setBounds(100, 100, 500, 275);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[] { 0, 0, 0 };
				gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
				gbl_panel.columnWeights = new double[] { 0.0, 1.0,
						Double.MIN_VALUE };
				gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
						0.0, Double.MIN_VALUE };
				panel.setLayout(gbl_panel);
				{
					JLabel lblName = new JLabel("Name:");
					GridBagConstraints gbc_lblName = new GridBagConstraints();
					gbc_lblName.anchor = GridBagConstraints.EAST;
					gbc_lblName.insets = new Insets(0, 0, 5, 5);
					gbc_lblName.gridx = 0;
					gbc_lblName.gridy = 0;
					panel.add(lblName, gbc_lblName);
				}
				{
					GridBagConstraints gbc_textField = new GridBagConstraints();
					gbc_textField.insets = new Insets(0, 0, 5, 0);
					gbc_textField.fill = GridBagConstraints.HORIZONTAL;
					gbc_textField.gridx = 1;
					gbc_textField.gridy = 0;
					panel.add(txtName, gbc_textField);
					txtName.setColumns(10);
				}
				{
					JLabel lblExpression = new JLabel("Expression:");
					GridBagConstraints gbc_lblExpression = new GridBagConstraints();
					gbc_lblExpression.anchor = GridBagConstraints.EAST;
					gbc_lblExpression.insets = new Insets(0, 0, 5, 5);
					gbc_lblExpression.gridx = 0;
					gbc_lblExpression.gridy = 1;
					panel.add(lblExpression, gbc_lblExpression);
				}
				{
					GridBagConstraints gbc_cmbExpression = new GridBagConstraints();
					gbc_cmbExpression.insets = new Insets(0, 0, 5, 0);
					gbc_cmbExpression.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbExpression.gridx = 1;
					gbc_cmbExpression.gridy = 1;
					cmbExpression.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Actor previewActor = previewPane.getActor();
							if (previewActor != null) {
								previewActor
										.setExpression((Actor.Expression) cmbExpression
												.getSelectedItem());
								previewPane.update();
							}
						}
					});
					panel.add(cmbExpression, gbc_cmbExpression);

					DefaultComboBoxModel cmbExpressionModel = new DefaultComboBoxModel(
							Actor.Expression.values());
					cmbExpression.setModel(cmbExpressionModel);
				}
				{
					JLabel lblShirt = new JLabel("Shirt:");
					GridBagConstraints gbc_lblShirt = new GridBagConstraints();
					gbc_lblShirt.anchor = GridBagConstraints.EAST;
					gbc_lblShirt.insets = new Insets(0, 0, 5, 5);
					gbc_lblShirt.gridx = 0;
					gbc_lblShirt.gridy = 2;
					panel.add(lblShirt, gbc_lblShirt);
				}
				{
					GridBagConstraints gbc_cmbShirt = new GridBagConstraints();
					gbc_cmbShirt.insets = new Insets(0, 0, 5, 0);
					gbc_cmbShirt.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbShirt.gridx = 1;
					gbc_cmbShirt.gridy = 2;
					cmbShirt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Actor previewActor = previewPane.getActor();
							if (previewActor != null) {
								previewActor.setShirtColor(Actor.ALLOWED_COLORS
										.get((String) cmbShirt
												.getSelectedItem()));
								previewPane.update();
							}
						}
					});
					panel.add(cmbShirt, gbc_cmbShirt);

					DefaultComboBoxModel cmbShirtModel = new DefaultComboBoxModel(
							Actor.ALLOWED_COLORS.keySet().toArray());
					cmbShirt.setModel(cmbShirtModel);
				}
				{
					JLabel lblPants = new JLabel("Pants");
					GridBagConstraints gbc_lblPants = new GridBagConstraints();
					gbc_lblPants.anchor = GridBagConstraints.EAST;
					gbc_lblPants.insets = new Insets(0, 0, 5, 5);
					gbc_lblPants.gridx = 0;
					gbc_lblPants.gridy = 3;
					panel.add(lblPants, gbc_lblPants);
				}
				{
					GridBagConstraints gbc_cmbPants = new GridBagConstraints();
					gbc_cmbPants.insets = new Insets(0, 0, 5, 0);
					gbc_cmbPants.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbPants.gridx = 1;
					gbc_cmbPants.gridy = 3;
					cmbPants.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Actor previewActor = previewPane.getActor();
							if (previewActor != null) {
								previewActor.setPantsColor(Actor.ALLOWED_COLORS
										.get((String) cmbPants
												.getSelectedItem()));
								previewPane.update();
							}
						}
					});
					panel.add(cmbPants, gbc_cmbPants);

					DefaultComboBoxModel cmbPantsModel = new DefaultComboBoxModel(
							Actor.ALLOWED_COLORS.keySet().toArray());
					cmbPants.setModel(cmbPantsModel);
				}
				{
					JLabel lblHeight = new JLabel("Height:");
					GridBagConstraints gbc_lblHeight = new GridBagConstraints();
					gbc_lblHeight.anchor = GridBagConstraints.EAST;
					gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
					gbc_lblHeight.gridx = 0;
					gbc_lblHeight.gridy = 4;
					panel.add(lblHeight, gbc_lblHeight);
				}
				{
					// MAX/MIN
					GridBagConstraints gbc_spnHeight = new GridBagConstraints();
					gbc_spnHeight.fill = GridBagConstraints.HORIZONTAL;
					gbc_spnHeight.insets = new Insets(0, 0, 5, 0);
					gbc_spnHeight.gridx = 1;
					gbc_spnHeight.gridy = 4;
					spnHeight.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent arg0) {
							Actor previewActor = previewPane.getActor();
							if (previewActor != null) {
								previewActor.setHeight((Integer) spnHeight
										.getValue());
								previewPane.update();
							}
						}
					});
					panel.add(spnHeight, gbc_spnHeight);

					SpinnerNumberModel spnHeightModel = new SpinnerNumberModel(
							Actor.MAX_HEIGHT, Actor.MIN_HEIGHT,
							Actor.MAX_HEIGHT, 1);

					spnHeight.setModel(spnHeightModel);
				}
				{
					JLabel lblWidth = new JLabel("Width:");
					GridBagConstraints gbc_lblWidth = new GridBagConstraints();
					gbc_lblWidth.anchor = GridBagConstraints.EAST;
					gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
					gbc_lblWidth.gridx = 0;
					gbc_lblWidth.gridy = 5;
					panel.add(lblWidth, gbc_lblWidth);
				}
				{
					// MAX/MIN
					GridBagConstraints gbc_spnWidth = new GridBagConstraints();
					gbc_spnWidth.fill = GridBagConstraints.HORIZONTAL;
					gbc_spnWidth.gridx = 1;
					gbc_spnWidth.gridy = 5;
					panel.add(spnWidth, gbc_spnWidth);

					SpinnerNumberModel spnWidthModel = new SpinnerNumberModel(
							Actor.MAX_WIDTH, Actor.MIN_WIDTH, Actor.MAX_WIDTH,
							1);

					spnWidth.setModel(spnWidthModel);

					spnWidth.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent arg0) {
							Actor previewActor = previewPane.getActor();
							if (previewActor != null) {
								previewActor.setWidth((Integer) spnWidth
										.getValue());
								previewPane.update();
							}
						}
					});
				}
			}
			{
				splitPane.setRightComponent(previewPane);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ok();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancel();
					}
				});
			}
		}
	}

	private void ok() {
		okOrCancel = OK_OPTION;
		this.setVisible(false);
	}

	private void cancel() {
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure you want to cancel?") == JOptionPane.OK_OPTION) {
			okOrCancel = CANCEL_OPTION;
			this.setVisible(false);
		}
	}

	private void updateFields(Actor actor) {
		txtName.setText(actor.getName());
		cmbExpression.setSelectedItem(actor.getExpression());
		cmbShirt.setSelectedItem(Actor.getColorName(actor.getShirtColor()));
		cmbPants.setSelectedItem(Actor.getColorName(actor.getPantsColor()));
		spnHeight.setValue(actor.getHeight());
		spnWidth.setValue(actor.getWidth());
	}

	private void updateActor(Actor actor) {
		actor.setName(txtName.getText());
		actor.setExpression((Actor.Expression) cmbExpression.getSelectedItem());
		actor.setShirtColor(Actor.ALLOWED_COLORS.get((String) cmbShirt.getSelectedItem()));
		actor.setPantsColor(Actor.ALLOWED_COLORS.get((String) cmbPants.getSelectedItem()));
		actor.setHeight((Integer) spnHeight.getValue());
		actor.setWidth((Integer) spnWidth.getValue());
	}

	public int showActorWindow(Actor actor) {
		okOrCancel = CANCEL_OPTION;
		updateFields(actor);
		previewPane.setActor(actor);
		this.setVisible(true);
		updateActor(actor);
		return okOrCancel;
	}
}
