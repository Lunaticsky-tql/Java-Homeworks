package chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
public class serverStream implements Runnable
{ 
      public static List<String> messages = new ArrayList<String>();
      BufferedReader in;
      PrintWriter out;
      public serverStream(Socket socket)
      {
      	 super();
      	try {
			in = new BufferedReader(new InputStreamReader( 
			 	    socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
      }  
      public void run()
      {	  
      	try
      	{	
      	  while (true) {
              String message = in.readLine();
              if (message == null) {
                  break;
              }
               messages.add(message + "   \n");
      	    }
      	}
      	catch(IOException e)
      	{
      		e.printStackTrace();
      	}
      }
}
