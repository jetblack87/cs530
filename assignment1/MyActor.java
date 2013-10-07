import java.awt.EventQueue;

/*
 * Mark Albrecht
 * mwa29@drexel.edu
 * CS530:DUI, Assignment 1
 */

public class MyActor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllActorsWindow mainWindow = new AllActorsWindow();
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
