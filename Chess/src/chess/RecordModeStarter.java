package chess;

import java.util.ArrayList;

public class RecordModeStarter {

	public static RecordChessPanel rv=null;
	public static RecordPutChess rm=null;
	public static RecordFrame rf=null;
	public RecordModeStarter(ArrayList<ChessPoint> history, int mode, int localColor) {
	
		RecordChessPanel rv=new RecordChessPanel(mode,localColor);
		RecordModeStarter.rv=rv;
		RecordPutChess rm=new RecordPutChess(history);
		RecordModeStarter.rm=rm;
		RecordFrame rf=new RecordFrame();

		RecordModeStarter.rf=rf;
	}

}
