package org.dataart.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import org.dataart.view.imprt.ImportDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mnFile.add(mntmNewProject);
		
		JMenuItem mntmOpenProject = new JMenuItem("Open Project");
		mnFile.add(mntmOpenProject);
		
		JMenuItem mntmSaveProject = new JMenuItem("Save Project");
		mnFile.add(mntmSaveProject);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmImport = new JMenuItem("Import...");
		mntmImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openImportDialog();
			}
		});
		mnFile.add(mntmImport);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeMainWindow(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnPlay = new JMenu("Play");
		menuBar.add(mnPlay);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}
	
	
	private void closeMainWindow(int exitCode) {
		System.exit(exitCode);
	}
	
	private void openImportDialog() {
		ImportDialog dialog = new ImportDialog();
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	private void loadTabs() {
		
	}
}
