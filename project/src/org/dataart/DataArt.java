package org.dataart;

import java.awt.EventQueue;

import org.dataart.model.Data;
import org.dataart.view.MainWindow;

public class DataArt {
	
	/**
	 * Where imported data is stored
	 */
	private static Data data = new Data();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(data);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Data getData()
	{
		return data;
	}
}
