package gobangGame;


public class Control {
	private int color = Model.BLACK;
	private int winner= Model.SPACE;
	private int willWin= Model.SPACE;
	
	private Control() {}
	private static Control instance;
	public static Control getInstance() {
		if (instance == null) {
			instance =  new Control();
		}
		return instance;
	}
	public static void main(String[] args) {
		System.out.println("Gobang Simulator");
		System.out.println("Enter /remake to restart at any time");
		Model.getInstance().initialize();
		View.getInstance().outputChess();
		while(getInstance().getWinner()==Model.SPACE) {
			View.getInstance().input();
		}
}
	public void localUserPutChess(int row, int col) {
		boolean success = 
				Model.getInstance().putChess(row,col,color);
		if(success) {

			winner = Model.getInstance().whoWin();
			View.getInstance().outputChess();
			switch(winner) {
			case Model.BLACK:
				View.getInstance().outputWarning("black win");
				break;
			case Model.WHITE:
				View.getInstance().outputWarning("white win");
				break;
			case Model.SPACE:
				//judging whether one player will win in the next turn,if it happens, the loser can regret  
				willWin=Model.getInstance().whoWillWin();
				if(View.getInstance().regret(willWin)!=color)
				color=-color;
				break;
			}
		}else {
			View.getInstance().outputWarning("invalid input");
		}
		
		
	}
	public int getColor() {
		return color;
	}
	public int getWinner() {
		return winner;
	}
	public void remake() {
		switch(color) {
		case Model.BLACK:
			View.getInstance().outputWarning("white win");
			break;
		case Model.WHITE:
			View.getInstance().outputWarning("black win");
			break;
		case Model.SPACE:
			break;
		}
		Model.getInstance().remake();
		color=Model.BLACK;
		View.getInstance().outputWarning("rermake sussessfully");
	}
}

