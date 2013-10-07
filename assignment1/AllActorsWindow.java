/*
 * Mark Albrecht
 * mwa29@drexel.edu
 * CS530:DUI, Assignment 1
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AllActorsWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AllActorsWindow() {
		setTitle("All Actors");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		final DefaultListModel listModel = new DefaultListModel();

		final JList list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		final JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setMinimumSize(new Dimension(150, -1));

		final PreviewPane previewPane = new PreviewPane();

		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				listScrollPane, previewPane);
		contentPane.add(splitPanel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JButton btnNew = new JButton("New");
		panel_1.add(btnNew);

		final JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		panel_1.add(btnEdit);

		final JButton btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		panel_1.add(btnRemove);

		// ########## Setup listeners ##########

		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ActorWindow dialog = new ActorWindow(null);
				dialog.setModal(true);
				
				Actor newActor = new Actor();
				if (dialog.showActorWindow(newActor) == ActorWindow.OK_OPTION) {
					listModel.addElement(newActor);
					list.setSelectedValue(newActor, true);
				}
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex >= 0) {
					ActorWindow dialog = new ActorWindow(null);
					dialog.setModal(true);
					
					Actor selectedActor = (Actor) listModel
							.elementAt(selectedIndex);
					// Clone the selectedActor for editing
					Actor editActor = new Actor(selectedActor);
					if (dialog.showActorWindow(editActor) == ActorWindow.OK_OPTION) {
						// If user clicked OK, update the selected actor
						listModel.setElementAt(editActor, selectedIndex);
						previewPane.setActor(editActor);
						previewPane.update();
					}
				}
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog((Component) arg0.getSource(),
						"Are you sure you want to remove?") == JOptionPane.OK_OPTION) {
					Actor currActor = (Actor) list.getSelectedValue();
					if (currActor != null) {
						listModel.removeElement(currActor);
						adjustButtons(currActor, btnEdit, btnRemove);
						list.setSelectedIndex(0);
					}
				}
			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Actor currActor = (Actor) list.getSelectedValue();
				adjustButtons(currActor, btnEdit, btnRemove);
				previewPane.setActor(currActor);
			}
		});
	}

	private void adjustButtons(Actor currActor, JButton btnEdit,
			JButton btnRemove) {
		if (currActor != null) {
			btnEdit.setEnabled(true);
			btnRemove.setEnabled(true);
		} else {
			btnEdit.setEnabled(false);
			btnRemove.setEnabled(false);
		}
	}
}
