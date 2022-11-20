package drawing_board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Text extends Shape {

	private static final long serialVersionUID = 1L;
	private String textName;
	private int lineStartX = 0;
	private int lineStartY = 0;
	
	private String textFace;
	private int textStyle = 0;
	private int textSize = 20;

	protected Text(Color color1, int type1, int x, int y, String context, String face, int style, int size) {
		super(color1, 0, type1, x, y);// no stroke
		this.textName = context;
		this.textFace = face;
		this.textStyle = style;
		this.textSize = size;
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(color);
		graphics2d.setFont(new Font(textFace, textStyle, textSize));
		graphics2d.drawString(textName, currentX, currentY);
		lineStartX = currentX;
		lineStartY = currentY;
	}
	public void mouseDragged(MouseEvent mouseEvent) {
		if (DrawingBoard.cursor == UIFrame.CROSSHAIR_CURSOR) {
			endX = mouseEvent.getX();
			endY = mouseEvent.getY();
			if (startX > endX) {
				currentX = endX;
			}
			if (startY > endY) {
				currentY = endY;
			}
		} else {
			currentX = mouseEvent.getX();
			currentY = mouseEvent.getY();
		}
	}

	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public int getLineStartX() {
		return lineStartX;
	}

	public void setLineStartX(int lineStartX) {
		this.lineStartX = lineStartX;
	}

	public int getLineStartY() {
		return lineStartY;
	}

	public void setLineStartY(int lineStartY) {
		this.lineStartY = lineStartY;
	}

	public String getTextFace() {
		return textFace;
	}

	public void setTextFace(String textFace) {
		this.textFace = textFace;
	}

	public int getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(int textStyle) {
		this.textStyle = textStyle;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
}
