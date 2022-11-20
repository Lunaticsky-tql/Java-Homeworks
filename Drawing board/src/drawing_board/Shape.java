package drawing_board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;


public abstract class Shape extends JLabel implements MouseMotionListener {

	private static final long serialVersionUID = 1L;

	protected float stroke;
	protected Color color;
	protected int type;

	protected int startX = 0;
	protected int startY = 0;
	protected int endX = 0;
	protected int endY = 0;
	protected int currentX = 0;
	protected int currentY = 0;
	protected int currentD = 0;
	protected int currentdX = 0;
	protected int currentdY = 0;

	protected Shape(Color color1, float stroke1, int type1, int x, int y) {
		type = type1;
		color = color1;
		stroke = stroke1;
		startX = endX = currentX = x;
		startY = endY = currentY = y;

		addMouseMotionListener(this);
	}

	public void mouseDragged(MouseEvent mouseEvent) {
		if (DrawingBoard.cursor == UIFrame.CROSSHAIR_CURSOR) {
			endX = mouseEvent.getX();
			endY = mouseEvent.getY();
			currentdX = Math.abs(startX - endX);
			currentdY = Math.abs(startY - endY);
			if (startX > endX) {
				currentX = endX;
			}
			if (startY > endY) {
				currentY = endY;
			}
		} else {
			currentX = mouseEvent.getX()-currentdX/2;
			currentY = mouseEvent.getY()-currentdY/2;
		}
	}

	public void mouseMoved(MouseEvent mouseevent) {
	}

	
	public abstract void draw(Graphics2D graphics2d);

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
