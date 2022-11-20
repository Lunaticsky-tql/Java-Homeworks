package chess;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;




public class ChessPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public int mode=Controller.AI_PLAY;
	int width=0,height=0;
	int sx=0,sy=0,unit=0,gap=0;//start x,position of the 1A point
	String chatText=" ";
	public ChessPanel()
	{
		
		sizeManage();
		putChess();
		Vars.m.initialize();
		setCursor(new Cursor(UIFrame.CROSSHAIR_CURSOR));
	}

	private void getCurrentMode() {
		this.mode=Vars.c.getMode();
		
	}
	private void putChess() {
		addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			int row=(e.getY()-sy)/unit+1;
			int col=(e.getX()-sx)/unit+1;
			if((e.getY()-sy)%unit>unit/2)
			{
				row++;
			}
			if((e.getX()-sx)%unit>unit/2)
			{
				col++;
			}
			Vars.c.localPutChess(row,col);
		}});
	}
	private void sizeManage() {
		addComponentListener(new ComponentAdapter() {
		@Override
		public void componentResized(ComponentEvent e) {
			width=getWidth();
			height=getHeight();
			int max=width>height?width:height;
			gap=max/5;
			unit=(max-gap*2)/(Model.WIDTH-1);
			sx=(width-unit*(Model.WIDTH-2))/2+3;
			sy=(height-unit*(Model.WIDTH-2))/2;
			repaint();
		}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		getCurrentMode();
		UIManage(g);
		drawChessPanel(g);
		drawChess(g);
		drawOppoentPanel(g);
		drawSelfPanel(g);
		drawChatMessageMaker(g);
	}
private void UIManage(Graphics g) {
	g.setColor(WelcomePanel.BG_ALL);
	g.fillRect(0, 0, 400, 600);
	g.setColor(new Color(204,169,140));
	g.fillRect(sx-unit*3/2, sy-unit, unit*17, unit*17);
	}

private void drawOppoentPanel(Graphics g) {
	int color =Vars.c.getOtherColor();
	int opponentWidth=width,opponentHeight=gap/2;
	int stringStartX=opponentWidth/6;
	String text=" ";
	String modeIndicatorString=" ";
	if(color==Model.BLACK)
	{
		g.setColor(Color.BLACK);
	}
	else if(color==Model.WHITE) {
		g.setColor(Color.WHITE);
	}
	
	switch (mode) {
	case Controller.SELF_PLAY: {
		text="Next";
		modeIndicatorString="Play yourself";
		break;
	}
	case Controller.AI_PLAY: {
		text="AI";
		stringStartX=opponentWidth/4;
		modeIndicatorString="Play with AI";
		break;
	}
	case Controller.ONLINE_PLAY: {
		text="Friend";
		stringStartX=opponentWidth/7;
		modeIndicatorString="Play online";
		break;
	}
	}
	g.fillOval(opponentWidth/3, 3*opponentHeight/2-18, 2*opponentHeight/5, 2*opponentHeight/5);
	g.setColor(Color.BLACK);
	g.setFont(new Font("Arial", Font.PLAIN, 15+opponentHeight/10));
	g.drawString(text, stringStartX, 3*opponentHeight/2);
	g.setFont(new Font("Arial", Font.BOLD, 16+opponentHeight/9));
	g.drawString(modeIndicatorString, opponentWidth/2, 3*opponentHeight/2);
	}

private void drawSelfPanel(Graphics g) {
	int color =Vars.c.getLocalColor();
	int selfWidth=width,selfHeight=gap/2,selfPosition=height-selfHeight;
	String text=" ";
	if(color==Model.BLACK)
	{
		g.setColor(Color.BLACK);
		text="Black";
	}
	else if(color==Model.WHITE) {
		g.setColor(Color.WHITE);
		text="White";
	}
	g.fillOval(2*selfWidth/3, selfPosition-18, 2*selfHeight/5, 2*selfHeight/5);
	g.setColor(Color.BLACK);
	g.setFont(new Font("Arial", Font.PLAIN, 15+selfHeight/10));
	switch (mode) {
	case Controller.SELF_PLAY: {
		text="Now";
		break;
	}
	case Controller.AI_PLAY: {
		text="You";
		break;
	}
	case Controller.ONLINE_PLAY: {
		text="You";
		break;
	}
	}
	g.drawString(text, 3*selfWidth/4, selfPosition);
	}
private void drawChess(Graphics g) {

	for (int row = 1; row < Model.WIDTH; row++) {
		
		for (int col = 1; col < Model.WIDTH; col++) {
			int color=Vars.m.getChess(row, col);

			if(color==Model.BLACK)
			{
				g.setColor(Color.BLACK);
				g.fillOval(sx+unit*(col-1)-unit/2, sy+unit*(row-1)-unit/2, unit, unit);
			}
			else if(color==Model.WHITE)
			{			
				g.setColor(Color.WHITE);
				g.fillOval(sx+unit*(col-1)-unit/2, sy+unit*(row-1)-unit/2, unit, unit);
			}
			
		}
		
	}

}

private void drawChessPanel(Graphics g) {
	g.setColor(Color.BLACK);
	for (int i = 0; i < Model.WIDTH-1; i++) {
		drawNoter(g, i);
		g.drawLine(sx, sy+unit*i, sx+unit*(Model.WIDTH-2), sy+unit*i);
		g.drawLine(sx+unit*i, sy, sx+unit*i, sy+unit*(Model.WIDTH-2));
	}
}
private void drawChatMessageMaker(Graphics g) {
	g.setColor(Color.BLACK);
	g.setFont(new Font("MV Boli", Font.BOLD, 7*unit/5));
	g.drawString(chatText, 2*unit, 2*unit);
}
private void drawNoter(Graphics g, int i) {
	g.setColor(Color.BLACK);
	if(i<9)
	{
		g.drawString(Integer.toString(i+1), sx-unit+8, sy+i*unit+7);
	}
	else
	{
		g.drawString(Integer.toString(i+1), sx-unit+5, sy+i*unit+3);
	}
	g.drawString(Character.toString(i+65), sx+i*unit-4, sy+15*unit);
	g.fillRect(sx+unit*3-unit/8, sy+unit*3-unit/8, unit/4, unit/4);
	g.fillRect(sx+unit*11-unit/8, sy+unit*3-unit/8, unit/4, unit/4);
	g.fillRect(sx+unit*3-unit/8, sy+unit*11-unit/8, unit/4, unit/5);
	g.fillRect(sx+unit*11-unit/8, sy+unit*11-unit/8, unit/4,unit/4);
}

public void repaintChessPosition(String string) {
	if(string.equals("REPUT"))
	setCursor(new Cursor(UIFrame.MOVE_CURSOR));
	else {
		repaint();
	}
}

public void drawChatMessage(String nameFrom, String text) {
	chatText=nameFrom+": "+text;
	
}

}