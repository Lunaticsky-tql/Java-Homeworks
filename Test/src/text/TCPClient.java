package text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
public static void main(String[] args) throws Exception{
	Socket s= new Socket("localhost",8010);
	BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	PrintWriter out =new PrintWriter(s.getOutputStream(),true);
	out.println("hello");
	in.close();
	out.close();
	s.close();
}
}
