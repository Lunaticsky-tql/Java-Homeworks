package EX1;

import java.util.concurrent.TimeUnit;

public class ADamon implements Runnable{

	public static void main(String[] args) {
		Thread t=new Thread(new ADamon());
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Starting ADamon");
			
		} catch (InterruptedException e) {
			System.out.println("intetrupped");
		}
		finally {
			System.out.println("finally");
		}
	}
}
