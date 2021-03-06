package application;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
/**
 * Sword Class
 * @author Andreas Alvear, David Lu, Joey Zhong, Nicholas Ting
 *
 */
public class Sword extends GameObject {

	private Image[] state;
	private BlockDir blockDirection;
	
	/**
	 * Constructor
	 */
	public Sword() {
		super(new ImageView());
		setX(263);
		setY(347);

		this.state = new Image[2];
		state[0] = new Image("/Images/Sword.png", 40, 5, true, false);
		state[1] = new Image("/Images/Sword2.png", 40, 5, true, false);
		this.blockDirection = BlockDir.rest;
	}
	
	/**
	 * Changes BlockDir enum
	 * @param b new block direction
	 */
	public void setBlockDir(BlockDir b) {
		blockDirection = b;
	}
	
	/**
	 * Gets BlockDir enum
	 * @return direction of Block
	 */
	public BlockDir getBlockDir() {
		return blockDirection;
	}
	
	/**
	 * Updates state based on BlockDir enum
	 */
	public void switchState() {
		switch (blockDirection) {
		case left:
			getView().setImage(state[0]);
			getView().setRotate(0);
			setX(263);
			setY(347);
			break;
		case right:
			getView().setImage(state[1]);
			getView().setRotate(0);
			setX(370);
			setY(347);
			break;
		case upLeft:
			getView().setImage(state[0]);
			getView().setRotate(25);
			setX(263);
			setY(322);
			break;
		case upRight:
			getView().setImage(state[1]);
			getView().setRotate(-25);
			setX(368);
			setY(322);
			break;
		case downLeft:
			getView().setImage(state[0]);
			getView().setRotate(-30);
			setX(266);
			setY(355);
			break;
		case downRight:
			getView().setImage(state[1]);
			getView().setRotate(30);
			setX(368);
			setY(355);
			break;
		case up:
			getView().setImage(state[1]);
			getView().setRotate(-25);
			setX(330);
			setY(332);
			break;
		case rest:
			getView().setImage(state[0]);
			getView().setRotate(0);
			setX(275);
			setY(355);
			break;
		}
	}
	
	/**
	 * Checks if collision with projectile objects have occured
	 * @param gc graphics context 2D used to update visuals
	 * @param p projectile with which to collide
	 * @return whether a collision has occurred or not
	 */
	public boolean Collide(GraphicsContext gc, Projectile p) {

		Bounds n = getView().getBoundsInParent();
		double x1 = n.getMinX();
		double y1 = n.getMinY();
		double x2 = n.getMaxX();
		double y2 = n.getMaxY();

		if (blockDirection == BlockDir.up || blockDirection == BlockDir.downLeft
				|| blockDirection == BlockDir.upRight) {
			double tmp = y1;
			y1 = y2;
			y2 = tmp;
		}

		double m = (y2 - y1) / (x2 - x1);
		double b = y1 - m * x1;

		gc.setFill(Color.RED);
		for (double x = x1 + 2; x <= x2 - 2; x++) {
			double y = m * x + b;
			//gc.fillOval(x + 1, y + 1, 2, 2);
			if(p.getBounds().contains(x + 1, y + 3)){
				return true;
			}
		}
		return false;
	}
}
