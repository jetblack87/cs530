/*
 * Mark Albrecht
 * mwa29@drexel.edu
 * CS530:DUI, Assignment 1
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PreviewPane extends JPanel {

	private Actor actor;
	private static final Dimension PREFFERED_SIZE = new Dimension(175, 500);

	private static final int CENTER = 125;
	private static final int Y_ORIGIN = 25;

	private static final double HEAD_HEIGHT_ADJUST = 0.333333333;
	private static final double HEAD_WIDTH_ADJUST = 0.333333333;
	private static final double HEAD_Y_ADJUST = 0;

	private static final double SHIRT_WIDTH_ADJUST = 1;
	private static final double SHIRT_Y_ADJUST = 0.333333333;

	private static final double PANTS_Y_ADJUST = 0.666666667;

	private static final int STROKE_THICKNESS = 3;

	/**
	 * Create the panel.
	 */
	public PreviewPane() {
		super();
		this.setPreferredSize(PREFFERED_SIZE);
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
		update();
	}

	public void update() {
		paint(this.getGraphics());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (g != null && actor != null) {
			Graphics2D g2 = (Graphics2D) g;
			paintHead(g2);
			paintShirt(g2);
			paintPants(g2);
		}
	}

	private void paintHead(Graphics2D g2) {
		// HEAD
		// Stroke with a solid color.
		double headW = actor.getWidth() * HEAD_WIDTH_ADJUST;
		double headH = actor.getHeight() * HEAD_HEIGHT_ADJUST;
		double headX = getLeftXFromCenter(CENTER, headW);
		double headY = Y_ORIGIN + HEAD_Y_ADJUST;
		Ellipse2D e = new Ellipse2D.Double(headX, headY, headW, headH);
		e.setFrame(headX, headY, headW, headH);
		g2.setPaint(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.draw(e);


		// LEFT EYE
		double eyeWidth = actor.getWidth() * .1;
		double eyeHeight = actor.getHeight() * .1;
		double eyeXAdjust = eyeWidth * 2.5;
		double eyeYAdjust = eyeHeight * .7;

		double x = getLeftXFromCenter(CENTER, headW - eyeXAdjust) - eyeWidth;
		double y = Y_ORIGIN + eyeYAdjust;
		e = new Ellipse2D.Double(x, y, eyeWidth, eyeHeight);
		e.setFrame(x, y, eyeWidth, eyeHeight);
		g2.setPaint(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.draw(e);

		// RIGHT EYE
		x = getRightXFromCenter(CENTER, headW - eyeXAdjust);
		y = Y_ORIGIN + eyeYAdjust;
		e = new Ellipse2D.Double(x, y, eyeWidth, eyeHeight);
		e.setFrame(x, y, eyeWidth, eyeHeight);
		g2.setPaint(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.draw(e);

		// MOUTH
		paintMouth(g2, actor.getExpression(), headY);
	}

	private void paintMouth(Graphics2D g2, Actor.Expression expression,
			double headY) {
		double mouthYAdjust = actor.getHeight() * .1;
		int mouthWidth = actor.getWidth() / 5;
		int mouthHeight = actor.getHeight() / 5;

		int leftX = (int) getLeftXFromCenter(CENTER, mouthWidth);
		int rightX = (int) getRightXFromCenter(CENTER, mouthWidth);

		if (expression.equals(Actor.Expression.HAPPY)) {
			mouthYAdjust -= mouthYAdjust * .1;
			Arc2D smile = new Arc2D.Float(leftX, (int) (headY + mouthYAdjust),
					mouthWidth, mouthHeight, 180, 180, Arc2D.PIE);
			g2.setPaint(Color.BLACK);
			g2.setStroke(new BasicStroke(STROKE_THICKNESS));
			g2.draw(smile);
		} else if (expression.equals(Actor.Expression.NEUTRAL)) {
			mouthYAdjust += mouthYAdjust * 1.5;
			g2.setPaint(Color.BLACK);
			g2.setStroke(new BasicStroke(STROKE_THICKNESS));
			g2.drawLine(leftX, (int) (headY + mouthYAdjust), rightX,
					(int) (headY + mouthYAdjust));
		} else if (expression.equals(Actor.Expression.SAD)) {
			mouthYAdjust += mouthYAdjust * .7;
			Arc2D frown = new Arc2D.Float(leftX, (int) (headY + mouthYAdjust),
					mouthWidth, mouthHeight, 0, 180, Arc2D.PIE);
			g2.draw(frown);
		}
	}

	private void paintShirt(Graphics2D g2) {
		g2.setPaint(actor.getShirtColor());
		g2.setStroke(new BasicStroke(STROKE_THICKNESS));

		GeneralPath gp = new GeneralPath();
		
		double actorHeight = actor.getHeight();
		double actorWidth = actor.getWidth();
		
		double shirtTop = Y_ORIGIN + (actorHeight * SHIRT_Y_ADJUST);
		double shirtBottom = Y_ORIGIN + (actorHeight * PANTS_Y_ADJUST);
		double sleeveBottom = shirtTop + ((shirtBottom - shirtTop) / 2);
		double wasteWidth = actor.getWidth() / 2;
		
		double sleeveLeftX = getLeftXFromCenter(CENTER, actorWidth * SHIRT_WIDTH_ADJUST);
		double sleeveRightX = getRightXFromCenter(CENTER, actorWidth * SHIRT_WIDTH_ADJUST);

		double wasteLeftX = getLeftXFromCenter(CENTER, wasteWidth);
		double wasteRightX = getRightXFromCenter(CENTER, wasteWidth);

		
		gp.moveTo(sleeveLeftX, shirtTop);
		gp.lineTo(sleeveRightX, shirtTop);
		gp.lineTo(sleeveRightX, sleeveBottom);
		gp.lineTo(wasteRightX, sleeveBottom);
		gp.lineTo(wasteRightX, shirtBottom);
		gp.lineTo(wasteLeftX, shirtBottom);
		gp.lineTo(wasteLeftX, sleeveBottom);
		gp.lineTo(sleeveLeftX, sleeveBottom);
		gp.closePath();

		g2.fill(gp);
	}

	private void paintPants(Graphics2D g2) {
		g2.setPaint(actor.getPantsColor());
		g2.setStroke(new BasicStroke(STROKE_THICKNESS));

		GeneralPath gp = new GeneralPath();
		
		double actorHeight = actor.getHeight();
		double actorWidth = actor.getWidth();
		
		double pantsTop = Y_ORIGIN + (actorHeight * PANTS_Y_ADJUST);
		double pantsBottom = Y_ORIGIN + actorHeight;
		double crotch = pantsTop + ((pantsBottom - pantsTop) / 2);
		double wasteWidth = actor.getWidth() / 2;
		
		double wasteLeftX = getLeftXFromCenter(CENTER, wasteWidth);
		double wasteRightX = getRightXFromCenter(CENTER, wasteWidth);

		double leftOuterX = getLeftXFromCenter(CENTER, actorWidth);
		double leftInnerX = getLeftXFromCenter(CENTER, actorWidth * .33333);

		double rightInnerX = getRightXFromCenter(CENTER, actorWidth * .33333);
		double rightOuterX = getRightXFromCenter(CENTER, actorWidth);
		
		gp.moveTo(wasteLeftX, pantsTop);
		gp.lineTo(wasteRightX, pantsTop);
		gp.lineTo(rightOuterX, pantsBottom);
		gp.lineTo(rightInnerX, pantsBottom);
		gp.lineTo(CENTER, crotch);
		gp.lineTo(leftInnerX, pantsBottom);
		gp.lineTo(leftOuterX, pantsBottom);
		gp.closePath();

		g2.fill(gp);
	}

	private double getLeftXFromCenter(double center, double width) {
		return center - (width / 2);
	}

	private double getRightXFromCenter(double center, double width) {
		return center + (width / 2);
	}
}
