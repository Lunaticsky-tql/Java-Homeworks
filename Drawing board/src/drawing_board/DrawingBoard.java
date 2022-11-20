package drawing_board;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawingBoard extends JPanel implements MouseListener, MouseMotionListener {
	public static final int TRIANGLE=0;
	public static final int LINE=1;
	public static final int OVAL=2;
	public static final int SQUARE=3;
	public static final int TEXT=4;
	private static final long serialVersionUID = 1L;
	private int tool = LINE;
	public static int cursor = UIFrame.DEFAULT_CURSOR;
	public Shape currentShape;
	public ArrayList<Shape> shapes;
	public ArrayList<Shape> cancelShapes;

	
	public HashMap<Integer, Color> colorMap = new HashMap<Integer, Color>();
	public HashMap<Integer, Float> strokeMap = new HashMap<Integer, Float>();
	public HashMap<Integer, Boolean> fillMap = new HashMap<Integer, Boolean>();
	public float stroke = 10.0F;
	private String textName;
	public String textFace = "微软雅黑";
	public int textStyle = 0;
	public int textSize = 20;
		
	public DrawingBoard() {
		colorMap.put(TRIANGLE,Color.BLACK);
		colorMap.put(LINE,Color.BLACK);
		colorMap.put(OVAL,Color.BLACK);
		colorMap.put(SQUARE,Color.BLACK);
		colorMap.put(TEXT,Color.BLACK);
		strokeMap.put(TRIANGLE, 3.0f);
		strokeMap.put(LINE,3.0f);
		strokeMap.put(OVAL,3.0f);
		strokeMap.put(SQUARE,3.0f);
		fillMap.put(TRIANGLE, false);
		fillMap.put(LINE,false);
		fillMap.put(OVAL,false);
		fillMap.put(SQUARE,false);
		setBackground(Color.WHITE);
		shapes = new ArrayList<Shape>();
		cancelShapes = new ArrayList<Shape>();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;
		if (shapes!=null  && shapes.size() > 0) {
			for (int i = 0; i < shapes.size(); i++) {
				((Shape) shapes.get(i)).draw(graphics2d);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (cursor == 1) {
			if (e.getButton() == 1) {
				switch (tool) {
				case OVAL:
					currentShape = new Oval(colorMap.get(OVAL), strokeMap.get(OVAL), OVAL, e.getX(), e.getY(),fillMap.get(OVAL));
					break;
				case TRIANGLE:
					currentShape = new Triangle(colorMap.get(TRIANGLE), strokeMap.get(TRIANGLE), TRIANGLE, e.getX(), e.getY(),fillMap.get(TRIANGLE));
					break;
				case LINE: 
					currentShape = new Line(colorMap.get(LINE), strokeMap.get(LINE), LINE,e.getX(), e.getY());
					break;
				case SQUARE: 
					currentShape = new Square(colorMap.get(SQUARE), strokeMap.get(SQUARE), SQUARE,e.getX(), e.getY(),fillMap.get(SQUARE));
					break;
				case TEXT:
					currentShape = new Text(colorMap.get(TEXT), TEXT, e.getX(), e.getY(), getTextName(), textFace, textStyle, textSize);
					break;
				default:
					break;
				}
				shapes.add(currentShape);
				repaint();
			}
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
			if (currentShape!= null) {
				currentShape.mouseDragged(e);
				repaint();
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		cursor=UIFrame.MOVE_CURSOR;
		setCursor(new Cursor(UIFrame.DEFAULT_CURSOR));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			if (null != currentShape) {
				currentShape.mouseDragged(e);
				repaint();
			}
		
	}

	public void saveImage() {
		try {
		
			BufferedImage bimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d = bimg.createGraphics();
			paint(graphics2d);
			File file = new File("D:/masterpiece.jpg");
			ImageIO.write(bimg, "jpg", file);
			JOptionPane.showMessageDialog(this, "the image is in D:/masterpiece.jpg");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "error");
			e.printStackTrace();
		}
	}
	
	public void clearBoard() {
		shapes.clear();
		repaint();
	}
	
	public void setTool(int i) {
			tool = i;
	}

	public void setStrokeSize(float size, int type) {
		if ( currentShape==null ) {
			throw new NullPointerException("currentShape is Null!");
		}
		if (size < 0 || size > 10) {
			throw new IllegalArgumentException("Invaild Weight Specified!");
		}
		switch (type) {
		case OVAL:
			strokeMap.replace(OVAL, size);
			break;
		case TRIANGLE:
			strokeMap.replace(TRIANGLE, size);
			break;
		case LINE: 
			strokeMap.replace(LINE, size);
			break;
		case TEXT: 
			break;
		default:
			break;
		}
		currentShape.stroke = size;
		repaint();
	}
	public void setCurrentX(int x) {
		currentShape.currentX = x;
		repaint();
	}

	public void setCurrentY(int y) {
		currentShape.currentY = y;
		repaint();
	}

	public void setCurrentD(int d) {
		currentShape.currentD = d;
		repaint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

}