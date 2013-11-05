import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ViewWindow extends JPanel {

	private Image img = null;
	
	/**
	 * Create the panel.
	 */
	public ViewWindow() {
		super();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	    // Draw the previously loaded image to Component.
		if(img != null){
			g.drawImage(img, 0, 0, null);
		}
	}

	public void takeScreenShot(){
        try {
        	this.getTopLevelAncestor().setVisible(false);
        	img = getScreenShot(this.getBounds(null));
        	this.update(this.getGraphics());
        	this.getTopLevelAncestor().setVisible(true);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private BufferedImage getScreenShot(Rectangle bounds) throws AWTException {
        Robot robot = new Robot();
        return robot.createScreenCapture(bounds);
	}	
}