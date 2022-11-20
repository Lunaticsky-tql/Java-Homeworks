package chess;

import java.io.Serializable;

public class ChessPoint implements Serializable{
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	private static final long serialVersionUID = 1L;
	public int row;
	public int col;
	public int color;
	public ChessPoint(int row, int col, int color) {
		super();
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
}
