package chess;

import java.awt.Cursor;

public class Controller {

	public static final int SELF_PLAY=1;
	public static final int AI_PLAY=2;
	public static final int ONLINE_PLAY=3;
	public int mode=AI_PLAY;
	boolean openDoor=false;
	int localColor=Model.BLACK;
	int otherColor=Model.WHITE;
	int situation=SituationEvaluate.NOTHING;

	public void localPutChess(int row,int col) {
		if(!openDoor)
		{
			return;
		}
		boolean success=Vars.m.putChess(row,col,localColor);
		if(success) {

			Vars.b.repaint();
			int winner=Vars.m.whoWin(row,col,localColor);
			if(winner==localColor)
			{
				switch (mode) {
				case SELF_PLAY: {
					String winNote=localColor+" win!";
					Vars.v.showMessage("Message",winNote);
					Vars.v.showOption("RECORD", "generate record?");
					openDoor=false;
					break;
				}
				case AI_PLAY:
				{
//					callAI();
//					break;
				}
				case ONLINE_PLAY:
				{				
					
					Vars.v.showMessage("Congratulation","You win!");
				    Vars.n.sendWinnerMessage();
				    Vars.v.showOption("RECORD", "generate record?");
				}
					
				default:
					break;
				}
				
			}
			else {
				switch (mode) {
				case SELF_PLAY: {
					localColor=-localColor;
					otherColor=-otherColor;
					break;
				}
				case AI_PLAY:
				{
					callAI();
					break;
				}
				case ONLINE_PLAY:
				{
					Vars.n.sendChess(row, col);
					Vars.b.setCursor(new Cursor(UIFrame.DEFAULT_CURSOR));
					Vars.v.changeRegretBtnState(false);
					Vars.v.changeReputBtnState(true);
					openDoor=false;
					break;
				}
					
				default:
					break;
				}
			}
	}
	}
	private void callAI() {
		Vars.m.giveAIInformation();
	}
	public void netPutChess(int row,int col) {
		boolean success=Vars.m.putChess(row,col,otherColor);
		if(success) {
			Vars.b.repaint();
		}
		Vars.b.setCursor(new Cursor(UIFrame.CROSSHAIR_CURSOR));
		if(Vars.m.getHistoryStep()>1)
		{
			Vars.v.changeRegretBtnState(true);
			Vars.v.changeReputBtnState(false);
		}
		openDoor=true;
	}
	public void AIPutChess(int row,int col) {
		boolean success=Vars.m.putChess(row,col,otherColor);
		if(success) {
			Vars.b.repaint();
			int winner=Vars.m.whoWin(row,col,otherColor);
			if(winner==Model.BLACK)
			{
				
			}
		}
		openDoor=true;

	}
	public void sendChatMessage(String text, String me) {
		Vars.n.sendChatMessage(text);
		Vars.chat.addChatMessage(me,text);
	}
	public void otherSaySomething(String text) {
		Vars.chat.addChatMessage(Vars.chat.getFriend(),text);
	}
	
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		judgeOpenDoor(mode);
		this.mode = mode;
	}
	private void judgeOpenDoor(int mode) {
	if(mode==SELF_PLAY||mode==AI_PLAY)
	{
		openDoor=true;
	}
	else {
		openDoor=false;
	}
	}
	public int getLocalColor() {
		return localColor;
	}

	public int getOtherColor() {
		return otherColor;
	}
	public void setOtherColor(int otherColor) {
		this.otherColor = otherColor;
	}

	public void handleResrart() {
		if(mode==AI_PLAY||mode==SELF_PLAY)
		{
			ensureRestart(true);
		}
		else if(mode==ONLINE_PLAY){
			Vars.n.sendRestartMessage("ASK");
		}
		
	}
	public void ensureRestart(boolean b) {
		if(mode==SELF_PLAY||mode==AI_PLAY)
		{
			
			Vars.m.removeHistory();
			Vars.b.repaintChessPosition("RESTART");
			if(mode==AI_PLAY)
			{
				openDoor=localColor==Model.BLACK?true:false;
			}
			else {
				openDoor=true;
			}
		}
		else if(mode==ONLINE_PLAY){
			if(b==true)
			{
				Vars.m.tellNetRemoveChess("ALL");
				Vars.m.removeHistory();
				openDoor=localColor==Model.BLACK?true:false;
				Vars.b.repaintChessPosition("RESTART");
				Vars.v.changeRegretBtnState(false);
				Vars.v.changeReputBtnState(false);
			}
			else if (b==false)
			{
				Vars.v.showWarning("Your friend refused your request");
			}
		}
	}
	public void setFirstHand() {
		localColor=Model.BLACK;
		otherColor=Model.WHITE;
		Vars.m.initialize();
		Vars.b.setCursor(new Cursor(UIFrame.CROSSHAIR_CURSOR));
		openDoor=true;
	}
	public void setGoteHand() {
		localColor=Model.WHITE;
		otherColor=Model.BLACK;
		Vars.m.initialize();
		Vars.b.setCursor(new Cursor(UIFrame.DEFAULT_CURSOR));
		openDoor=false;
	}
	public void handleGoBack() {
		if(mode==AI_PLAY)
		{
			ensureReput(true);
		}
		else if(mode==ONLINE_PLAY){
			Vars.n.sendRegretMessage("ASK");
		}
	}
	public void handleReput() {
			if(mode==SELF_PLAY)
			{
				ensureReput(true);
			}
			else if(mode==ONLINE_PLAY){
				Vars.n.sendReputMessage("ASK");
			}
	}
	public void ensureReput(boolean b) {
		if(mode==SELF_PLAY)
		{
			
			Vars.m.removeChessJustPut();
			localColor=-localColor;
			otherColor=-otherColor;
			Vars.b.repaintChessPosition("REPUT");
			Vars.v.changeReputBtnState(true);
		}
		else if(mode==ONLINE_PLAY){
			if(b==true)
			{
				Vars.m.tellNetRemoveChess("ONCE");
				Vars.m.removeChessJustPut();
				openDoor=true;
				Vars.b.repaintChessPosition("REPUT");
				Vars.v.changeRegretBtnState(true);
				Vars.v.changeReputBtnState(false);
			}
			else if (b==false)
			{
				Vars.v.showWarning("Your friend refused your request");
				Vars.v.changeReputBtnState(true);
			}
		}
	}
	public void netRemoveChess(String string) {
		Vars.m.removeChessJustPut();
		openDoor=false;
		if(string.equals("TWICE"))
		{
			Vars.m.removeChessJustPut();
		}
		else if (string.equals("ALL")) {
			Vars.m.removeHistory();
			openDoor=localColor==Model.BLACK?true:false;
			Vars.v.changeRegretBtnState(false);
			Vars.v.changeReputBtnState(false);
		}
		Vars.b.repaint();

	}
	public void ensureRegret(boolean b) {
		if(mode==AI_PLAY)
		{
			Vars.m.removeChessJustPut();
			Vars.m.removeChessJustPut();
			Vars.b.repaintChessPosition("REGRET");
		}
		else if(mode==ONLINE_PLAY){
			if(b==true)
			{
				Vars.m.tellNetRemoveChess("TWICE");
				Vars.m.removeChessJustPut();
				Vars.m.removeChessJustPut();
				Vars.b.repaintChessPosition("REGRET");
				Vars.v.changeRegretBtnState(true);
				Vars.v.changeReputBtnState(false);
			}
			else if (b==false)
			{
				Vars.v.showWarning("Your friend refused your request");
				Vars.v.changeRegretBtnState(true);
			}
		}
	}
	public void setOpenDoor(boolean b) {
			openDoor=b;
	}
}
