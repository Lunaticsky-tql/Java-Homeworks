package drawing_board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Square extends Shape {

	private static final long serialVersionUID = 1L;
	private boolean fill;
	public Square(Color color, float stroke, int type, int x, int y,boolean fill) {
		super(color, stroke, type, x, y);
		this.fill=fill;
	}

	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(stroke));
		if (fill) {
			graphics2d.fillRect(currentX, currentY, currentdX, currentdY);
		}
		else {
			graphics2d.drawRect(currentX, currentY, currentdX, currentdY);
		}
		
	}
}
