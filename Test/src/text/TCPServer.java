package text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {

	public static void main(String[] args) throws Exception{
		ServerSocket ss=new ServerSocket(8010);
		System.out.println("begin listen");
		Socket s=ss.accept();
		System.out.println("receive client:"+s);
		BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter out =new PrintWriter(s.getOutputStream(),true);
		String line=in.readLine();
		System.out.println(line);
		in.close();
		out.close();
		s.close();
		ss.close();
		}
}
