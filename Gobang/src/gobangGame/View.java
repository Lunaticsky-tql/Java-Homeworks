package gobangGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
	public void input() {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in));
		String s = null;
		char s1,s2,s3;
		try {

			int c=Control.getInstance().getColor();
			if(c==Model.BLACK)
				System.out.println("black's turn");
			if(c==Model.WHITE)
				System.out.println("white's turn");
			System.out.println("input your chess position");
			System.out.println("eg:1A");
			//processing the input string
			s = in.readLine();
			if(s.equals("/remake"))
			{
				Control.getInstance().remake();
			}
			else if(s.length()>3||s.length()<2)
			{
				View.getInstance().outputWarning("invalid input");
			}
			else if(s.length()==2){
			s1=s.charAt(0);
			int row = (int) s1-48;//transfer char to integer according to ASCII
			s2=s.charAt(1);
			int col = (int) s2-64;//transfer char to integer according to ASCII
			Control.getInstance().localUserPutChess(row,col);
			}
			else {
				s1=s.charAt(0);				
				s2=s.charAt(1);
				s3=s.charAt(2);
				int row = 10*((int) s1-48)+((int) s2-48);//transfer char to integer according to ASCII
				int col = (int) s3-64;//transfer char to integer according to ASCII
				Control.getInstance().localUserPutChess(row,col);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private View() {}
	private static View instance;
	public static View getInstance() {
		if (instance==null) {
			instance = new View();
		}
		return instance;
	}
	public void outputWarning(String string) {
		System.out.println(string);
	}
	public void outputChess() {
		for (int row = 1; row < Model.WIDTH; row++) {
			if(row<10)
			    System.out.print(row+"  ");
			else
				System.out.print(row+" ");
			for (int col = 1; col < Model.WIDTH; col++) {
				int chess = Model.getInstance().getChess(row,col);
				switch(chess){
					case Model.BLACK:
						System.out.print("● ");
						break;
					case Model.WHITE:
						System.out.print("○ ");
						break;
					case Model.SPACE:
						System.out.print("· ");
						break;
					case Model.BLACKWIN1:
						System.out.print("❶ ");
						break;
					case Model.BLACKWIN2:
						System.out.print("❷ ");
						break;
					case Model.BLACKWIN3:
						System.out.print("❸ ");
						break;
					case Model.BLACKWIN4:
						System.out.print("❹ ");
						break;
					case Model.BLACKWIN5:
						System.out.print("❺ ");
						break;
					case Model.WHITEWIN1:
						System.out.print("① ");
						break;
					case Model.WHITEWIN2:
						System.out.print("② ");
						break;
					case Model.WHITEWIN3:
						System.out.print("③ ");
						break;
					case Model.WHITEWIN4:
						System.out.print("④ ");
						break;
					case Model.WHITEWIN5:
						System.out.print("⑤ ");
						break;
						
				}
			}
			System.out.println();
			
		}
		System.out.print("  ");
		for (int i = 1; i <Model.WIDTH; i++) {
			System.out.print(" "+(char)(i+64));
		}
		System.out.println();
	}

	public int regret(int color) {
		int result=Model.SPACE;
		switch(color) {
		case Model.BLACK:
			View.getInstance().outputWarning("black will win");
			result= regretEnsure(Model.BLACK);
		    break;
		case Model.WHITE:
			View.getInstance().outputWarning("white will win");
			result= regretEnsure(Model.WHITE);
			break;
		case Model.SPACE:
			result= Model.SPACE;
			break;
		}
		return result;
	}
	private int regretEnsure(int willWin) {
		String goBack = null;
		int back;
		int result=Model.SPACE;
		BufferedReader get = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			if(willWin==Control.getInstance().getColor())
			{
				
				View.getInstance().outputWarning("Allow your opponent to regret?");
				View.getInstance().outputWarning("Enter 1 to go back, 0 to continue");
			}
			else 
			{
				View.getInstance().outputWarning("Want to regret?");
				View.getInstance().outputWarning("Enter 1 to go back, 0 to continue");
			}

			goBack = get.readLine();
			back=Integer.parseInt(goBack);
			if(back==0)
			{
				outputWarning("Continue");
				result=Model.SPACE;
			}
			else if (back==1) {
				Model.getInstance().goBack(willWin);
				outputWarning("Regret successfully");
				result=-willWin;
			}
			else {
				outputWarning("Invalid input,continue automatically");
				result=Model.SPACE;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
