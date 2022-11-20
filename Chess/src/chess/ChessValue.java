package chess;

public class ChessValue {
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int row;
	public int col;
	public int value;
	public ChessValue(int row, int col, int value) {
		super();
		this.row = row;
		this.col = col;
		this.value = value;
	}
}
