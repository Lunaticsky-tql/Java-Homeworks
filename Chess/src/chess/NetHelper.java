package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;



public class NetHelper {
public static final int PORT=6666;
private Socket s=null;
private ServerSocket ss=null;
protected PrintWriter out;
protected BufferedReader in;
public boolean isStart;
public boolean reachToMax;
public void listen()
{
	new Thread() {
		public void run() {
			try {
				System.out.println("listener");
				ss=new ServerSocket(PORT);
				s=ss.accept();
				out=new PrintWriter(s.getOutputStream(),true);
				in =new BufferedReader(new InputStreamReader(s.getInputStream()));
				startReadThread();
			}catch (Exception e) {
				e.printStackTrace();
			}
		};
		
	}.start();
	isStart=true;
}
public void connect(final String ip) {
	new Thread() {
		public void run() {
			try
			{
				System.out.println("connecter");
				s = new Socket(ip,PORT);
				out=new PrintWriter(s.getOutputStream(),true);
				in =new BufferedReader(new InputStreamReader(s.getInputStream()));
				startReadThread();
				sendConnectMessage("CUSTOMER");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		
		};
	}.start();
	isStart=true;
}
protected void startReadThread() {
	new Thread()
	{
		
		@Override
		public void run() {
			System.out.println("Start read");
			while(true) {
				try {
					String line=in.readLine();
					if(line==null)
						return;
					parseMessage(line);
				} catch (Exception e) {
				}
				
			}
		}
	}.start();

}
protected void parseMessage(String line) {
	StringTokenizer stringTokenizer = new StringTokenizer(
			line, "#");
	String command = stringTokenizer.nextToken();
	if(command.equals("PUT"))
	{
		int row=Integer.parseInt(stringTokenizer.nextToken());
		int col=Integer.parseInt(stringTokenizer.nextToken());
		Vars.c.netPutChess(row,col);
		
	}
	else if(command.equals("CHAT"))
	{
		Vars.c.otherSaySomething(stringTokenizer.nextToken());
	}
	else if(command.equals("CONNECT"))
	{
	
		String sourceString=stringTokenizer.nextToken();
		if(sourceString.equals("CUSTOMER")) {
			if(reachToMax)
			{
				Vars.connect.refused("CUSTOMER");
			}
			else
			{
				Vars.connect.connected("CUSTOMER");
				reachToMax=true;
			}
		}
		else if(sourceString.equals("HOST")) {
			Vars.connect.connected("HOST");	
		}
		else if (sourceString.equals("REFUSED")) {
			Vars.connect.refused("HOST");
		}
		
	}
	else if(command.equals("REPUT"))
	{
		String sourceString=stringTokenizer.nextToken();
		if(sourceString.equals("ASK"))
		{
			Vars.v.showOption("REPUT","Let your friend reput?");
		}
		else if (sourceString.equals("OK")) {
			Vars.c.ensureReput(true);
		}
		else if (sourceString.equals("NO")) {
			Vars.c.ensureReput(false);
		}
	}
	else if(command.equals("REGRET"))
	{
		String sourceString=stringTokenizer.nextToken();
		if(sourceString.equals("ASK"))
		{
			Vars.v.showOption("REGRET","Let your friend regret?");
		}
		else if (sourceString.equals("OK")) {
			Vars.c.ensureRegret(true);
		}
		else if (sourceString.equals("NO")) {
			Vars.c.ensureRegret(false);
		}
	}
	else if(command.equals("REMOVE"))
	{
		String sourceString=stringTokenizer.nextToken();
		if(sourceString.equals("ONCE"))
		{
			Vars.c.netRemoveChess("ONCE");
		}
		else if (sourceString.equals("TWICE")) {
			Vars.c.netRemoveChess("TWICE");
		}
		else if (sourceString.equals("ALL")) {
			Vars.c.netRemoveChess("ALL");
		}
	}
	else if(command.equals("RESTART"))
	{
		String sourceString=stringTokenizer.nextToken();
		if(sourceString.equals("ASK"))
		{
			Vars.v.showOption("RESTART","Your friend want to give up, allow him?");
		}
		else if (sourceString.equals("OK")) {
			Vars.c.ensureRestart(true);
		}
		else if (sourceString.equals("NO")) {
			Vars.c.ensureRestart(false);
		}
	}
	else if(command.equals("WINNER"))
	{
		Vars.v.showMessage("Sorry", "You lose!");
		Vars.c.setOpenDoor(false);
		Vars.v.showOption("RECORD", "generate recording?");
	}
	else if(command.equals("NAME"))
	{
		Vars.connect.rememberName(stringTokenizer.nextToken());
	}
}
public void sendChess(int row, int col) {
	out.println("PUT#"+row+"#"+col);
}
public void sendChatMessage(String text) {
	out.println("CHAT#"+text);
}
public void sendConnectMessage(String text) {
	out.println("CONNECT#"+text);
}
public void sendReputMessage(String text) {

		out.println("REPUT#"+text);
}
public void sendRegretMessage(String text) {
	out.println("REGRET#"+text);
	
}
public void sendRemoveChess(String time) {
	out.println("REMOVE#"+time);
	
}
public void sendWinnerMessage() {
	out.println("WINNER");
	
}
public void sendNameMessage(String text) {
	out.println("NAME#"+text);
	
}
public void sendRestartMessage(String text) {
	out.println("RESTART#"+text);
	
}
public void stop()
{
	if (!isStart) {
		return;
	}
	else {
		if(s!=null)
		{
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(ss!=null)
		{
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	Vars.connect.handleSeverClose();
}





   
}
