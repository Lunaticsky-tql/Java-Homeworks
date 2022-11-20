package EX3_Applications;

import java.util.concurrent.TimeUnit;

class Station extends Thread{

	private static int ticket=20;
	private static Object key=0;
	public Station(String string) {
		super(string);
		
	}
	public void run()
	{
		while (ticket>0) {
			synchronized (key) {
					System.out.println(getName()+" sold the no"+ticket--+" ticket");
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}

public class SellTicket {

	public static void main(String[] args) {
		Station st1=new Station("station1");
		Station st2=new Station("station2");
		Station st3=new Station("station3");
		st1.start();
		st2.start();
		st3.start();
	}
}
