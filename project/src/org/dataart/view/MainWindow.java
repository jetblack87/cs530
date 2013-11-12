package org.dataart.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import org.dataart.model.Data;
import org.dataart.view.data.DataPanel;
import org.dataart.view.imprt.ImportDialog;
import org.dataart.view.output.AudioSubpanel;
import org.dataart.view.output.GamesSubpanel;
import org.dataart.view.output.VisualSubpanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	
	private Data data;

	/**
	 * Create the frame.
	 */
	public MainWindow(Data data) {
		this.data = data;
		
		setTitle(org.dataart.DataArt.class.getSimpleName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(MAXIMIZED_BOTH);
		
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

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		
		JMenuItem mntmImport = new JMenuItem("Import...");
		mntmImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openImportDialog(tabbedPane);
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
		
		tabbedPane.setEnabled(false);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		loadTabs(tabbedPane, mnPlay);
	}
	
	
	private void closeMainWindow(int exitCode) {
		System.exit(exitCode);
	}
	
	private void openImportDialog(JTabbedPane tabbedPane) {
		ImportDialog dialog = new ImportDialog(data);
		if(dialog.showImportDialog(this) == JOptionPane.OK_OPTION && data.size() > 0) {
			tabbedPane.setEnabled(true);
			
			for (Component subpanel : tabbedPane.getComponents()) {
				((ASubpanel)subpanel).setData(data);
			}
		}
	}
	
	private void loadTabs(JTabbedPane tabbedPane, JMenu playMenu) {
		new DataPanel().addThisPanelToTabbedPaneAndJMenu(tabbedPane, playMenu);
		new VisualSubpanel().addThisPanelToTabbedPaneAndJMenu(tabbedPane, playMenu);
		new AudioSubpanel().addThisPanelToTabbedPaneAndJMenu(tabbedPane, playMenu);
		new GamesSubpanel().addThisPanelToTabbedPaneAndJMenu(tabbedPane, playMenu);
	}
}
