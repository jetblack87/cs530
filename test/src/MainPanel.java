import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class MainPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		final ViewWindow viewWindow = new ViewWindow();
		contentPane.add(viewWindow, BorderLayout.CENTER);
		
		JButton btnTakescreenshot = new JButton("takeScreenShot");
		btnTakescreenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewWindow.takeScreenShot();
			}
		});
		contentPane.add(btnTakescreenshot, BorderLayout.SOUTH);
	}

}
