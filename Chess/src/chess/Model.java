package chess;

import java.util.ArrayList;

public class Model {


	public static final int BLACK=1;
	public static final int WHITE=-1;
	public static final int WIDTH = 16;//using width[1]width[15],width[0]and width[16] is "invalid place"
	public static final int SPACE=0;
	public static final int INVALIDSPACE = 2;
	private ArrayList<ChessPoint> history =new ArrayList<ChessPoint>();
	private int[][] data =new int [WIDTH+1][WIDTH+1];
	public ArrayList<ChessPoint> getHistory() {
		return history;
	}
	public boolean putChess(int row,int col,int color)
	{
		if(canPut(row, col, color)) {
			if(data[row][col]==SPACE) {
				data[row][col] = color;
				history.add(new ChessPoint(row, col, color));
				System.out.println(row+","+col);
				return true;
			}
		}
		return false;
	}
	private boolean canPut(int row, int col, int color) {
		return row>0&&row<WIDTH&&col>0&&col<WIDTH&&(color==BLACK||color==WHITE);
	}
	public int getChess(int row, int col) {
		return data[row][col];
	}
//	public int whoWin(int situation,int color)
//	{
//		System.out.println(situation);
//		return situation==10?color:SPACE;
//	}
	public int getChessCount() {
		return history.size();
	}
	public ChessPoint getChessPoint(int index) {
		return history.get(index);
	}
	public void giveAIInformation() {
	//	Vars.ai.listen(data,Vars.c.getOtherColor());
		Vars.ai.PutChess(data,Vars.c.getOtherColor());
	}
	public void initialize() {
		for (int i = 0; i < WIDTH+1; i++) {
			data[0][i]=INVALIDSPACE;
			data[WIDTH][i]=INVALIDSPACE;
			data[i][0]=INVALIDSPACE;
			data[i][WIDTH]=INVALIDSPACE;
		}
	}
	public int getCurrentStepNum() {
		return history.size();
	}
	public boolean removeChess(int row,int col,int color)
	{
		if(canPut(row, col, color)) {
			data[row][col]=SPACE;
				history.remove(history.size()-1);
				return true;
		}
		return false;
	}
	public void removeChessJustPut() {
		ChessPoint rightNow=history.get(history.size()-1);
		data[rightNow.getRow()][rightNow.getCol()]=SPACE;
		history.remove(history.size()-1);
	}
	public void tellNetRemoveChess(String time) {
		Vars.n.sendRemoveChess(time);
	}
	public void clearBoard()
	{
		for (int i = 1; i < WIDTH; i++) {
			for (int j = 1; j < WIDTH; j++) {
				data[i][j]=SPACE;
			}
		}
	}
	public int getHistoryStep()
	{
		return history.size();
	}
	public int whoWin(int x, int y, int color) {
		int dx[]= {1,0,1,1};
		int dy[]= {0,1,1,-1};
		for (int i = 0; i <4; i++) {
			int sum=1;
			int tx=x+dx[i];
			int ty=y+dy[i];
			while (tx>0&&tx<WIDTH&&ty>0&&ty<WIDTH&&data[tx][ty]==color) {
				tx+=dx[i];
				ty+=dy[i];
				++sum;
			}
			tx=x-dx[i];
			ty=y-dy[i];
			while (tx>0&&tx<WIDTH&&ty>0&&ty<WIDTH&&data[tx][ty]==color) {
				tx-=dx[i];
				ty-=dy[i];
				++sum;
			}
			if(sum>=5)
				return color;
			
			}
		return SPACE;
	}
	public void removeHistory() {
		clearBoard();
		history.clear();
	}
	
}
