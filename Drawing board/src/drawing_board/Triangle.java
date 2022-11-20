package drawing_board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Triangle extends Shape {
	
	private static final long serialVersionUID = 1L;
	private boolean fill;
	
	private int xS[] = null;
    private int yS[] = null;

    public Triangle(Color color, float stroke, int type, int x, int y,boolean fill) {
        super(color, stroke, type, x, y);
        xS = new int[3];
        yS = new int[3];
        this.fill=fill;
    }

    public void draw(Graphics2D graphics2d) {
        xS[0] = currentX;
        yS[0] = currentY+currentdY;
        xS[1] = currentX + currentdX/2;
        yS[1] = currentY;
        xS[2] = currentX+ currentdX;
        yS[2] = currentY+currentdY;

		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(stroke));
        graphics2d.drawPolygon(xS, yS, 3);
        if (fill) {
        	  graphics2d.fillPolygon(xS, yS, 3);
		}
        else {
        	graphics2d.drawPolygon(xS, yS, 3);
		}
    }

}
