package gobangGame;
import java.util.Stack;

public class Model {
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int SPACE = 0;
	public static final int INVALIDSPACE = 2;
	public static final int WIDTH = 16;//using width[1]width[15],width[0]and width[16] is "invalid place"
	public static final int WHITEWIN1 = -15;
	public static final int WHITEWIN2 = -14;
	public static final int WHITEWIN3 = -13;
	public static final int WHITEWIN4 = -12;
	public static final int WHITEWIN5 = -11;
	public static final int BLACKWIN1 = 11;
	public static final int BLACKWIN2 = 12;
	public static final int BLACKWIN3 = 13;
	public static final int BLACKWIN4 = 14;
	public static final int BLACKWIN5 = 15;
	//indicating winner's chess position

	private int[][] data = new int[WIDTH+1][WIDTH+1];
	class Step
	{
		int sx;
		int sy;
		int scolor;
		Step(int x,int y,int color)
		{
			sx=x;
			sy=y;
			scolor=color;
		}
	}
	class WinStatus{
		int caseWin;
		int dx;
		int dy;
		WinStatus(int c,int x,int y)
		{
			caseWin=c;
			dx=x;
			dy=y;
		}
	}
	Stack<WinStatus> winNote=new Stack<WinStatus>();
	Stack<Step> stepStack=new Stack<Step>();
	//using stack to store steps
	private int nowRow,nowCol,nowColor;
	private Model() {}
	private static Model instance;
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}
	public boolean putChess(int row, int col, int color) {
		if(row>0&&row<WIDTH&&col>0&&col<WIDTH&&(color==BLACK||color==WHITE)) {
			if(data[row][col]==SPACE) {
				data[row][col] = color;
				nowRow = row;
				nowCol = col;
				nowColor = color;
				Step nowStep=new Step(row, col, color);
				stepStack.push(nowStep);
				return true;
			}
		}
		return false;
	}
	public int whoWin() {
		final int RESET= 1;//reset count,1 is because it includes the chess just put
		int dx=0,dy=0;
		//"d" means detect
		int count=RESET;
		int x=nowRow,y=nowCol;//simplify variable name
		//judging vertically
		for(dx=x-1;dx>0;dx--)
		{
			if(data[dx][y]==nowColor)
			{
				count++;
				if(count==5)
				{
					winNote.push(new WinStatus(1,dx,y));
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
		}
		for(dx=x+1;dx<WIDTH;dx++)
		{
			if(data[dx][y]==nowColor)
			{
				count++;
				if(count==5)
				{
					winNote.push(new WinStatus(2,dx,y));
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
	
		}
		
		//judging horizontally
		count=RESET;
		//reset counter
		for(dy=y-1;dy>0;dy--)
		{
			if(data[x][dy]==nowColor)
			{
				count++;
				if(count==5)
				{
					winNote.push(new WinStatus(3,x,dy));
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
		}
		for(dy=y+1;dy<WIDTH;dy++)
		{
			if(data[x][dy]==nowColor)
			{
				count++;
				if(count==5)
				{
					winNote.push(new WinStatus(4,x,dy));
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
		}
		//judging in diagonals
		count=1;
		//reset counter
		for(dx=x-1,dy=y-1;dx>0&&dy>0;dx--,dy--)
		{
			if(data[dx][dy]==nowColor)
			{
				count++;
				if(count==5)
				{
					winNote.push(new WinStatus(5,dx,dy));
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
		}
		for(dx=x+1,dy=y+1;dx<WIDTH&&dy<WIDTH;dx++,dy++)
		{
			if(data[dx][dy]==nowColor)
			{
				count++;
				if(count==5)
				{
					winNote.push(new WinStatus(6,dx,dy));		
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
		}
		count=RESET;
		//reset counter
		for(dx=x+1,dy=y-1;dx<WIDTH&&dy>0;dx++,dy--)
		{
			if(data[dx][dy]==nowColor)
			{
				count++;
				if(count==5)
				{
					winNote.push(new WinStatus(7,dx,dy));
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
		}
		for(dx=x-1,dy=y+1;dx>0&&dy<WIDTH;dx--,dy++)
		{
			if(data[dx][dy]==nowColor)
			{
				count++;
				if(count==5)
				{

					winNote.push(new WinStatus(8,dx,dy));
					count=RESET;
					break;
				}
			}
			else {
				break;
			}
		}

		//collect the win signal and mark the winning place with numbers
		if(!winNote.empty())
		{
			while(!winNote.empty())
			{
				markWinnerChessPlace(winNote.pop());
			}
			return nowColor;
		}
		else {
			return SPACE;
		}
		
	}

	private void markWinnerChessPlace(WinStatus winNote) {
		int dx=winNote.dx,dy=winNote.dy;
		//marking starts from the last place detected
		int tagBeginx,tagBeginy,tagStart=nowColor==BLACK?BLACKWIN1-1:WHITEWIN1-1;
		switch (winNote.caseWin) {
		case 1:
			for(tagBeginx=dx;tagBeginx<dx+5;tagBeginx++)
				data[tagBeginx][dy]=++tagStart;
			break;
		case 2:
			for(tagBeginx=dx;tagBeginx>dx-5;tagBeginx--)
				data[tagBeginx][dy]=++tagStart;
			break;
		case 3:
			for(tagBeginy=dy;tagBeginy<dy+5;tagBeginy++)
				data[dx][tagBeginy]=++tagStart;
			break;
		case 4:
			for(tagBeginy=dy;tagBeginy>dy-5;tagBeginy--)
				data[dx][tagBeginy]=++tagStart;
			break;
		case 5:
			for(tagBeginx=dx,tagBeginy=dy;tagBeginx<dx+5&&tagBeginy<dy+5;tagBeginx++,tagBeginy++)
				data[tagBeginx][tagBeginy]=++tagStart;
			break;
		case 6:
			for(tagBeginx=dx,tagBeginy=dy;tagBeginx>dx-5&&tagBeginy>dy-5;tagBeginx--,tagBeginy--)
				data[tagBeginx][tagBeginy]=++tagStart;
			break;
		case 7:

			for(tagBeginx=dx,tagBeginy=dy;tagBeginx>dx-5&&tagBeginy<dy+5;tagBeginx--,tagBeginy++)
				data[tagBeginx][tagBeginy]=++tagStart;
			break;
		case 8:

			for(tagBeginx=dx,tagBeginy=dy;tagBeginx<dx+5&&tagBeginy>dy-5;tagBeginx++,tagBeginy--)
				data[tagBeginx][tagBeginy]=++tagStart;
			break;
		}
		
	}
	public int whoWillWin() {
		final int RESET= 1;//reset count,1 is because it includes the chess just put
		int dx=0,dy=0;
		int count=RESET;
		int x=nowRow,y=nowCol;//simplify variable name
		int spaceIndicator=RESET;//indicate the space near the detecting place
		int willWin=SPACE;
		if(stepStack.size()>6) {
			//if the step is less than 6,none of color will win
			//judging whether the color now will win
			//if the case looks like ·● ● ● ●· black will win in his next turn, so let the white regret
			//judging vertically
			for(dx=x-1;dx>0;dx--)
			{
				if(data[dx][y]==nowColor)
				{
					count++;
				}
				else {
					break;
				}
			}
			if(data[dx][y]==SPACE)
				spaceIndicator--;
			for(dx=x+1;dx<WIDTH;dx++)
			{
				if(data[dx][y]==nowColor)
				{
					count++;	
				}
				else {
					break;
				}
		
			}
			if(data[dx][y]==SPACE)
				spaceIndicator--;
			if(count==4&&spaceIndicator<RESET-1)
				willWin=nowColor;
			//judging horizontally
			count=RESET;
			spaceIndicator=RESET;
			//reset counter
			for(dy=y-1;dy>0;dy--)
			{
				if(data[x][dy]==nowColor)
				{
					count++;
				}
				else {
					break;
				}
			}
			if(data[x][dy]==SPACE)
				spaceIndicator--;
			for(dy=y+1;dy<WIDTH;dy++)
			{
				if(data[x][dy]==nowColor)
				{
					count++;
				}
				else {
				
					break;
				}
			}
			if(data[x][dy]==SPACE)
				spaceIndicator--;
			if(count==4&&spaceIndicator<RESET-1)
				willWin=nowColor;
			//judging in diagonals
			count=RESET;
			spaceIndicator=RESET;
			//reset counter
			for(dx=x-1,dy=y-1;dx>0&&dy>0;dx--,dy--)
			{
				if(data[dx][dy]==nowColor)
				{
					count++;

				}
				else {
					break;
				}
			}
			if(data[dx][dy]==SPACE)
				spaceIndicator--;
			for(dx=x+1,dy=y+1;dx<WIDTH&&dy<WIDTH;dx++,dy++)
			{
				if(data[dx][dy]==nowColor)
				{
					count++;

				}
				else {
					break;
				}
			}
			if(data[dx][dy]==SPACE)
				spaceIndicator--;
			if(count==4&&spaceIndicator<RESET-1)
				willWin=nowColor;
			count=RESET;
			spaceIndicator=RESET;
			//reset counter
			for(dx=x+1,dy=y-1;dx<WIDTH&&dy>0;dx++,dy--)
			{
				if(data[dx][dy]==nowColor)
				{
					count++;

				}
				else {
					break;
				}
			}
			if(data[dx][dy]==SPACE)
				spaceIndicator--;
			for(dx=x-1,dy=y+1;dx>0&&dy<WIDTH;dx--,dy++)
			{
				if(data[dx][dy]==nowColor)
				{
					count++;

				}
				else {
					break;
				}
			}

			if(data[dx][dy]==SPACE)
				spaceIndicator--;
			if(count==4&&spaceIndicator<RESET-1)
				willWin=nowColor;

			
			//*-*-*-*-*-*
			
			
			//judging whether the opponent now will win
			//if ignoring a space the opponent makes 5-in-a-row,the opponent will win in the next turn, so let the color now regret
			count=RESET;
			spaceIndicator=RESET;
			//reset counter
			Step nowStep= stepStack.pop();
			Step theLastStep= stepStack.peek();
			stepStack.push(nowStep);
			x=theLastStep.sx;
			y=theLastStep.sy;
			//judging vertically
			for(dx=x-1;dx>0;dx--)
			{
				if(data[dx][y]==-nowColor||(data[dx][y]==SPACE&&spaceIndicator==RESET))
				{
					count++;
					if(data[dx][y]==SPACE)
						spaceIndicator--;
				}
				else {
					break;
				}
			}
			for(dx=x+1;dx<WIDTH;dx++)
			{
				if(data[dx][y]==-nowColor||(data[dx][y]==SPACE&&spaceIndicator==RESET))
				{
					count++;	
					if(data[dx][y]==SPACE)
						spaceIndicator--;
				}
				else {
					break;
				}
		
			}

			if(count==5)
				willWin=-nowColor;
			//judging horizontally
			count=RESET;
			spaceIndicator=RESET;
			//reset counter
			for(dy=y-1;dy>0;dy--)
			{
				if(data[x][dy]==-nowColor||(data[x][dy]==SPACE&&spaceIndicator==RESET))
				{
					count++;
					if(data[x][dy]==SPACE)
						spaceIndicator--;
				}
				else {
					break;
				}
			}
			for(dy=y+1;dy<WIDTH;dy++)
			{
				if(data[x][dy]==-nowColor||(data[x][dy]==SPACE&&spaceIndicator==RESET))
				{
					count++;
					if(data[x][dy]==SPACE)
						spaceIndicator--;
				}
				else {
					break;
				}
			}

			if(count==5)
				willWin=-nowColor;
			//judging in diagonals
			count=RESET;
			spaceIndicator=RESET;
			//reset counter
			for(dx=x-1,dy=y-1;dx>0&&dy>0;dx--,dy--)
			{
				if(data[dx][dy]==-nowColor||(data[dx][dy]==SPACE&&spaceIndicator==RESET))
				{
					count++;
					if(data[dx][dy]==SPACE)
						spaceIndicator--;

				}
				else {
					break;
				}
			}

			for(dx=x+1,dy=y+1;dx<WIDTH&&dy<WIDTH;dx++,dy++)
			{
				if(data[dx][dy]==-nowColor||(data[dx][dy]==SPACE&&spaceIndicator==RESET))
				{
					count++;
					if(data[dx][dy]==SPACE)
						spaceIndicator--;

				}
				else {
					break;
				}
			}

			if(count==5)
				willWin=-nowColor;
			count=RESET;
			spaceIndicator=RESET;
			//reset counter
			for(dx=x+1,dy=y-1;dx<WIDTH&&dy>0;dx++,dy--)
			{
				if(data[dx][dy]==-nowColor||(data[dx][dy]==SPACE&&spaceIndicator==RESET))
				{
					count++;
					if(data[dx][dy]==SPACE)
						spaceIndicator--;

				}
				else {
					break;
				}
			}

			for(dx=x-1,dy=y+1;dx>0&&dy<WIDTH;dx--,dy++)
			{
				if(data[dx][dy]==-nowColor||(data[dx][dy]==SPACE&&spaceIndicator==RESET))
				{
					count++;
					if(data[dx][dy]==SPACE)
						spaceIndicator--;

				}
				else {
					break;
				}
			}

			if(count==5)
				willWin=-nowColor;
			
		}

		
		if(willWin!=0)
		{
			return willWin;
		}
		else {
			return SPACE;
		}
		
	}
	public int getChess(int row, int col) {
		if(row>0&&row<WIDTH&&col>0&&col<WIDTH) {
			return data[row][col];
		}
		return SPACE;
	}
	public void goBack(int willWin) {
		if(willWin==nowColor) {
			Step nowStep= stepStack.pop();
			Step theLastStep= stepStack.pop();
			data[nowStep.sx][nowStep.sy]=SPACE;
			data[theLastStep.sx][theLastStep.sy]=SPACE;
		}
		else {
			Step nowStep= stepStack.pop();
			data[nowStep.sx][nowStep.sy]=SPACE;
		}

		View.getInstance().outputChess();
	}
	public void remake() {
		for (int row = 1; row < WIDTH; row++) {
			for (int col = 1; col < WIDTH; col++) {
				data[row][col]=SPACE;
			}
		}
		View.getInstance().outputChess();
	}
	//initialize the game,including invalid space to solve the side condition
	public void initialize() {
		for (int i = 0; i < WIDTH+1; i++) {
			data[0][i]=INVALIDSPACE;
			data[WIDTH][i]=INVALIDSPACE;
			data[i][0]=INVALIDSPACE;
			data[i][WIDTH]=INVALIDSPACE;
		}
		
	}

}

