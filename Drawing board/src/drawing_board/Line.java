package drawing_board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Line extends Shape {

	private static final long serialVersionUID = 1L;


	protected Line(Color color1, float stroke1, int type1, int x, int y) {
		super(color1, stroke1, type1, x, y);
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(stroke));
		graphics2d.drawLine(startX, startY, endX, endY);
	}
	public void mouseDragged(MouseEvent mouseEvent) {
		if (DrawingBoard.cursor == UIFrame.CROSSHAIR_CURSOR) {
			endX = mouseEvent.getX();
			endY=mouseEvent.getY();
			//considering direction
			currentdX = startX - endX;
			currentdY = startY - endY;
		} else {
			startX = mouseEvent.getX()-currentdX/2;
			startY = mouseEvent.getY()-currentdY/2;
			endX = mouseEvent.getX()+currentdX/2;
			endY = mouseEvent.getY()+currentdY/2;
		}
	}
	public int getLineStartX() {
		return startX;
	}


	public int getLineStartY() {
		return startY;
	}

	public int getLineEndX() {
		return endX;
	}

	public int getLineEndY() {
		return endY;
	}
}
