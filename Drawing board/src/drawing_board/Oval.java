package drawing_board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Oval extends Shape {

	private static final long serialVersionUID = 1L;
	private boolean fill=false;

	public Oval(Color color, float stroke, int type, int x, int y,boolean fill) {
		super(color, stroke, type, x, y);
		this.fill=fill;
	}

	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(stroke));
		if (fill) {
			graphics2d.fillOval(currentX, currentY, currentdX, currentdY);
		}
		else {
			graphics2d.drawOval(currentX, currentY, currentdX, currentdY);
		}
	}
}
