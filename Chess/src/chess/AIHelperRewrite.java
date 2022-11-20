package chess;

public class AIHelperRewrite {
	public static final int WIDTH=16;
	public static int[][] chess=new int [WIDTH+1][WIDTH+1];
	public static int lastrow;
	public static int lastcol;
	private static int lastColor;
	private static int aiColor=Model.SPACE;

	public void PutChess(int[][] data,int otherColor) {
		// TODO Auto-generated method stub
		for (int i = 0; i <=WIDTH; i++) {
			for (int j = 0; j <=WIDTH; j++) {
				chess[i][j]=data[i][j];
			}
		}
		aiColor=otherColor;
		
			int[][] score=new int[WIDTH][WIDTH];
			int[] bestpositon={-1,-1,0};
			for (int i = 1; i < WIDTH; i++) {
				for (int j = 1; j < WIDTH; j++) {
					int color=chess[i][j];
					score[i][j]= getScore(i,j,color);
					if (bestpositon[2]<score[i][j]) {
						bestpositon[0]=i;
						bestpositon[1]=j;
						bestpositon[2]=score[i][j];
					}
				}
			}
		Vars.c.AIPutChess(bestpositon[0], bestpositon[1]);
	}

	private int getScore(int i, int j, int color) {
		// TODO Auto-generated method stub
		int point=0;
		if (color==Model.SPACE) {
			lastrow=i;
			lastcol=j;
			color=aiColor;
			point+=(myPoint(i,j,color)+rivalPoint(i,j,color));
		}
		return point;
	}

	private int rivalPoint(int i, int j, int color) {
		// TODO Auto-generated method stub
		int point=0;
		lastColor=-color;
		chess[i][j]=lastColor;
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if (judgediagDown(num)||judgediagUp(num)||judgeport(num)||judgetrans(num)) {
				while (count--!=0) {
					num=num*5;
				}
				if(num==5)
					point*=100000;
				point+=num*10;
			}
		}
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if ((judgediagDown(num)&&judgediagUp(num))
					||(judgediagDown(num)&&judgeport(num))
						||(judgeport(num)&&judgediagUp(num))
							||(judgetrans(num)&&judgediagUp(num))
								||(judgediagDown(num)&&judgetrans(num))
									||(judgeport(num)&&judgetrans(num))) {
				while (count--!=0) {
					num=num*5;
				}
				if(num==5)
					point*=100000;
				point+=num*100;
			}
		}
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if ((judgediagDown(num)&&judgediagUp(num)&&judgeport(num))
					||(judgediagUp(num)&&judgediagDown(num)&&judgetrans(num))
						||(judgeport(num)&&judgediagDown(num)&&judgetrans(num))
							||(judgetrans(num)&&judgediagUp(num)&&judgeport(num))) {
				while (count--!=0) {
					num=num*100;
				}
				if(num==5)
					point*=100000;
				point+=num*200;
			}
		}
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if (judgediagDown(num)&&judgediagUp(num)&&judgeport(num)&&judgetrans(num)) {
				while (count--!=0) {
					num=num*100;
				}
				if(num==5)
					point*=100000;
				point+=num*1000;
			}
		}
		chess[i][j]=Model.SPACE;
		return point;
	}

	private int myPoint(int i, int j, int color) {
		// TODO Auto-generated method stub
		chess[i][j]=color;
		int point=0;
		lastColor=color;
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if (judgediagDown(num)||judgediagUp(num)||judgeport(num)||judgetrans(num)) {
				while (count--!=0) {
					num=num*2;
				}
				if(num==5)
					point*=100000;
				point+=num*2;
			}
		}
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if ((judgediagDown(num)&&judgediagUp(num))
					||(judgediagDown(num)&&judgeport(num))
						||(judgeport(num)&&judgediagUp(num))
							||(judgetrans(num)&&judgediagUp(num))
								||(judgediagDown(num)&&judgetrans(num))
									||(judgeport(num)&&judgetrans(num))) {
				while (count--!=0) {
					num=num*3;
				}
				point+=num*5;
			}
		}
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if ((judgediagDown(num)&&judgediagUp(num)&&judgeport(num))
					||(judgediagUp(num)&&judgediagDown(num)&&judgetrans(num))
						||(judgeport(num)&&judgediagDown(num)&&judgetrans(num))
							||(judgetrans(num)&&judgediagUp(num)&&judgeport(num))) {
				while (count--!=0) {
					num=num*10;
				}
				point+=num*5;
			}
		}
		for (int k = 0; k < 5; k++) {
			int num=k+1;
			int count=num;
			if (judgediagDown(num)&&judgediagUp(num)&&judgeport(num)&&judgetrans(num)) {
				while (count--!=0) {
					num=num*15;
				}
				point+=num*5;
			}
		}
		chess[i][j]=Model.SPACE;
		return point;
	}

	public static boolean judgetrans(int num) {
		int amount=1;
		int currow=lastrow;
		int curcol=lastcol;
		while(++currow<WIDTH&&chess[currow][curcol]==lastColor)
			amount++;
		currow=lastrow;
		curcol=lastcol;
		while(--currow>0&&chess[currow][curcol]==lastColor)
			amount++;
		if(amount>=num)
			return true;
		else {
			return false;
		}
	}
	public static boolean judgeport(int num) {
		int amount=1;
		int currow=lastrow;
		int curcol=lastcol;
		while(++curcol<WIDTH&&chess[currow][curcol]==lastColor)
			amount++;
		currow=lastrow;
		curcol=lastcol;
		while(--curcol>0&&chess[currow][curcol]==lastColor)
			amount++;
		if(amount>=num)
			return true;
		else {
			return false;
		}
	}
	public static boolean judgediagUp(int num) {
		int amount=1;
		int currow=lastrow;
		int curcol=lastcol;
		while(++currow<WIDTH&&++curcol<WIDTH&&chess[currow][curcol]==lastColor)
			amount++;
		currow=lastrow;
		curcol=lastcol;
		while(--currow>0&&--curcol>0&&chess[currow][curcol]==lastColor)
			amount++;
		if(amount>=num)
			return true;
		else {
			return false;
		}
	}
	public static boolean judgediagDown(int num) {
		int amount=1;
		int currow=lastrow;
		int curcol=lastcol;
		while(++currow<WIDTH&&--curcol>0&&chess[currow][curcol]==lastColor)
			amount++;
		currow=lastrow;
		curcol=lastcol;
		while(--currow>0&&++curcol<WIDTH&&chess[currow][curcol]==lastColor)
			amount++;
		if(amount>=num)
			return true;
		else {
			return false;
		}
	}
}

