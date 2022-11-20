package chess;


import java.util.Arrays;

public class SituationEvaluate {
	public static final int BLACK=1;
	public static final int WHITE=-1;
	public static final int WIDTH=15;
	public static final int SPACE=0;
	public static final int INVALIDSPACE = 2;
	public static final int CP =4;//current position
	public static final int LB =9;//left boundary
	public static final int RB =10;//right boundary
	public static final int ROW=100;
	public static final int COL=200;
	public static final int R_DIA =300;
	public static final int L_DIA =400;
	public static final int WIN5 =10;//0->5连珠
	public static final int ALIVE4 =9;//1->活4
	public static final int DIE4 =8;//2->死4
	public static final int LOWDIE4 =7;//3->死4的低级版本
	public static final int ALIVE3 =6;//3->活3
	public static final int TIAO3 =5;//5->跳3
	public static final int DIE3 =4;//6->死3
	public static final int ALIVE2 =3;//7->活2
	public static final int LOWALIVE2 =2;//8->低级活2
	public static final int DIE2 =1;//9->死2
	public static final int NOTHING =0;

	//situation cases
	
	private int[][] data = new int[WIDTH+1][WIDTH+1];
	private int lc=SPACE;
	private int oc=SPACE;
	public int[] getCurrentLine(int cx,int cy,int direction)
	{
		int[] line=new int[11];
		Arrays.fill(line,INVALIDSPACE);
		line[LB]=CP+1;line[RB]=CP;
		switch (direction) {
		case ROW:
			for (int d =0; d <=4; d++) {
				int temp=data[cx-d][cy];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP-d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[LB]--;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			for (int d =1; d <=4; d++) {
				int temp=data[cx+d][cy];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP+d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[RB]++;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			break;
		case COL:
			for (int d =0; d <=4; d++) {
				int temp=data[cx][cy-d];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP-d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[LB]--;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			for (int d =1; d <=4; d++) {
				int temp=data[cx][cy+d];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP+d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[RB]++;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			break;
		case L_DIA:
			for (int d =0; d <=4; d++) {
				int temp=data[cx-d][cy-d];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP-d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[LB]--;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			for (int d =1; d <=4; d++) {
				int temp=data[cx+d][cy+d];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP+d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[RB]++;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			break;
		case R_DIA:
			for (int d =0; d <=4; d++) {
				int temp=data[cx-d][cy+d];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP-d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[LB]--;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			for (int d =1; d <=4; d++) {
				int temp=data[cx+d][cy-d];
				boolean isEnd=false;
				if(temp!=INVALIDSPACE) {
					line[CP+d]=temp;
					if(temp==lc||isEnd==false)
					{
						line[RB]++;
					}
					else {
						isEnd=true;
					}
				}
				else 
					break;
			}
			break;
		}
		return line;
	}
	
	public int analyseSituation(int[] line)
	{
		int count=line[RB]-line[LB]+1;
		if (count >= 5)
	        return WIN5;
		int colorleft=line[LB-1];
		int colorright=line[RB+1];
		boolean lnlc=(colorleft==oc||colorleft==INVALIDSPACE);//left not local color
		boolean rnlc=(colorright==oc||colorright==INVALIDSPACE);//right not local color

	    if (count == 4)
	    {
	        if (colorleft == SPACE && colorright == SPACE)
	            return ALIVE4;
	        else if (lnlc && rnlc)
	            return NOTHING;
	        else if (colorleft == SPACE || colorright == SPACE)
	            return DIE4;
	    }

	    if (count == 3) {
	        int colorleft1 = line[LB-2];
	        int colorright1 = line[RB-2];
	        boolean l1nlc=(colorleft1==oc||colorleft1==INVALIDSPACE);//left not local color
			boolean r1nlc=(colorright1==oc||colorright1==INVALIDSPACE);//right not local color
	        if (colorleft == SPACE && colorright == SPACE)
	        {

	            if (l1nlc&& r1nlc)
	                return DIE3;
	            else if (colorleft1 == lc || colorright1 == lc)
	                return LOWDIE4;
	            else if (colorleft1 == SPACE || colorright1 == SPACE)
	                return ALIVE3;

	        }
	        else if (lnlc && rnlc)
	        {
	            return NOTHING;
	        }
	        else if (colorleft == SPACE || colorright == SPACE)
	        {

	            if (lnlc) {
	                if (r1nlc)
	                    return NOTHING;
	                if (colorright1 == SPACE)
	                    return DIE3;
	                if (colorright1 == lc)
	                    return LOWDIE4;

	            }
	            if (rnlc) {
	                if (colorleft1 == oc)
	                    return NOTHING;
	                if (colorleft1 == SPACE)
	                    return DIE3;
	                if (colorleft1 == lc)
	                    return LOWDIE4;
	            }
	        }
	    }

	    if (count == 2) {
	        int colorleft1 = line[LB-2];
	        int colorright1 = line[RB-2];
	        int colorleft2 = line[LB-3];
	        int colorright2 = line[RB-3];
	        boolean l1nlc=(colorleft1==oc||colorleft1==INVALIDSPACE);//left not local color
			boolean r1nlc=(colorright1==oc||colorright1==INVALIDSPACE);//right not local color
			boolean l2nlc=(colorleft2==oc||colorleft2==INVALIDSPACE);//right not local color
			boolean r2nlc=(colorright2==oc||colorright2==INVALIDSPACE);//right not local color
	        if (colorleft == SPACE && colorright == SPACE)
	        {
	            if ((colorright1 == SPACE && colorright2 == lc) ||
	                (colorleft1 == SPACE && colorleft2 == lc))
	                return DIE3;
	            else if (colorleft1 == SPACE && colorright1 == SPACE)
	                return ALIVE2;

	            if ((colorright1 == lc && r2nlc) ||
	                (colorleft1 == lc && l2nlc))
	                return DIE3;
	             
	            if ((colorright1 == lc && colorright2 == lc) ||
	                (colorleft1 == lc && colorleft2 == lc))
	                return LOWDIE4;

	            if ((colorright1 == lc && colorright2 == SPACE) ||
	                (colorleft1 == lc && colorleft2 == SPACE))
	                return TIAO3;

	        }
	        else if (lnlc && rnlc)
	        {
	            return NOTHING;
	        }
	        else if (colorleft == SPACE || colorright == SPACE)
	        {
	            if (lnlc) {
	                if (r1nlc || r2nlc) {
	                    return NOTHING;
	                }
	                else if (colorright1 == SPACE && colorright2 == SPACE) {
	                    return DIE2;
	                }
	                else if (colorright1 == lc && colorright2 == lc) {
	                    return LOWDIE4;
	                }
	                else if (colorright1 == lc || colorright2 == lc) {
	                    return DIE3;
	                }
	            }
	            if (rnlc) {
	                if (l1nlc|| l2nlc) {
	                    return NOTHING;
	                }
	                else if (colorleft1 == SPACE && colorleft2 == SPACE) {
	                    return DIE2;
	                }
	                else if (colorleft1 == lc && colorleft2 == lc) {
	                    return LOWDIE4;
	                }
	                else if (colorleft1 == lc || colorleft2 == lc) {
	                    return DIE3;
	                }
	            }
	        }
	    }
	        
	    if (count == 1) {
	    	 int colorleft1 = line[LB-2];
		        int colorright1 = line[RB-2];
		        int colorleft2 = line[LB-3];
		        int colorright2 = line[RB-3];
		        int colorleft3=line[LB-4];
		        int colorright3=line[RB-4];
				boolean l3nlc=(colorleft3==oc||colorleft3==INVALIDSPACE);//right not local color
				boolean r3nlc=(colorright3==oc||colorright3==INVALIDSPACE);//right not local color
	        if (colorleft == SPACE && colorleft1 == lc && 
	            colorleft2 == lc && colorleft3 == lc)
	            return LOWDIE4;
	        if (colorright == SPACE && colorright1 == lc && 
	            colorright2 == lc && colorright3 == lc)
	            return LOWDIE4;

	        if (colorleft == SPACE && colorleft1 == lc && 
	            colorleft2 == lc && colorleft3 == SPACE && colorright == SPACE)
	            return TIAO3;
	        if (colorright == SPACE && colorright1 == lc &&
	            colorright2 == lc && colorright3 == SPACE && colorleft == SPACE)
	            return TIAO3;

	        if (colorleft == SPACE && colorleft1 == lc &&
	            colorleft2 == lc && l3nlc && colorright == SPACE)
	            return DIE3;
	        if (colorright == SPACE && colorright1 == lc &&
	            colorright2 == lc && r3nlc && colorleft == SPACE)
	            return DIE3;

	        if (colorleft == SPACE && colorleft1 == SPACE &&
	            colorleft2 == lc && colorleft3 == lc)
	            return DIE3;
	        if (colorright == SPACE && colorright1 == SPACE &&
	            colorright2 == lc && colorright3 == lc)
	            return DIE3;

	        if (colorleft == SPACE && colorleft1 == lc &&
	            colorleft2 == SPACE && colorleft3 == lc)
	            return DIE3;
	        if (colorright == SPACE && colorright1 == lc &&
	            colorright2 == SPACE && colorright3 == lc)
	            return DIE3;

	        if (colorleft == SPACE && colorleft1 == lc &&
	            colorleft2 == SPACE && colorleft3 == SPACE && colorright == SPACE)
	            return LOWALIVE2;
	        if (colorright == SPACE && colorright1 == lc &&
	            colorright2 == SPACE && colorright3 == SPACE && colorleft == SPACE)
	            return LOWALIVE2;

	        if (colorleft == SPACE && colorleft1 == SPACE &&
	            colorleft2 == lc && colorleft3 == SPACE && colorright == SPACE)
	            return LOWALIVE2;
	        if (colorright == SPACE && colorright1 == SPACE &&
	            colorright2 == lc && colorright3 == SPACE && colorleft == SPACE)
	            return LOWALIVE2;
	    }
	    return NOTHING;	
	}

	public int getSituation(int cx,int cy,int color) {
		this.lc=color;
		this.oc=-color;
		int situation=NOTHING;
		for (int i = ROW; i <=R_DIA; i+=100) {
			int currentSituation= analyseSituation(getCurrentLine(cx, cy, i));
			if(currentSituation>situation)
				situation=currentSituation;
		}
		return situation;
	} 
}
