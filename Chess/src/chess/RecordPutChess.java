package chess;

import java.util.ArrayList;

public class RecordPutChess {
	public static final int BLACK=1;
	public static final int WHITE=-1;
	public static final int WIDTH = 16;//using width[1]width[15],width[0]and width[16] is "invalid place"
	public static final int SPACE=0;
	private int[][] chessRecord =new int [WIDTH+1][WIDTH+1];
	private ArrayList<ChessPoint> history=null;
	private int iterator=0;
	public RecordPutChess(ArrayList<ChessPoint> history) {
		this.history=history;
	}

	public int getChess(int row, int col) {
		
		return chessRecord[row][col];
	}
	public void setChess(int row, int col,int color) {
		
		 chessRecord[row][col]=color;
	}
	
	public void paintNextStep() {
		RecordModeStarter.rf.changeLastBtnState(true);		
		System.out.println(iterator);
		ChessPoint currentPoint=history.get(iterator);
		if(iterator==history.size()-1)
		{
			RecordModeStarter.rf.changeNextBtnState(false);
		}
		iterator++;
		int x=currentPoint.getRow();
		int y=currentPoint.getCol();
		int color=currentPoint.getColor();
		setChess(x, y, color);
		RecordModeStarter.rv.repaint();
	}
	public void paintLastStep() {
		RecordModeStarter.rf.changeNextBtnState(true);
		iterator--;
		System.out.println(iterator);
		if(iterator==0)
		{
			RecordModeStarter.rf.changeLastBtnState(false);
		}
		ChessPoint currentPoint=history.get(iterator);
		int x=currentPoint.getRow();
		int y=currentPoint.getCol();
		removeChess(x, y);
		RecordModeStarter.rv.repaint();
	}

	private void removeChess(int x, int y) {
		chessRecord[x][y]=Model.SPACE;
		
	}

}
