package EX2;

import java.util.concurrent.TimeUnit;
class NonTask {

	 static void longMethod() throws InterruptedException
	 {
		 TimeUnit.SECONDS.sleep(100);
	 }
}

class Task implements Runnable {

	@Override
	public void run() {
		try {
			NonTask.longMethod();
		} catch (Exception e) {
//			System.out.println(e.toString());
			System.out.println("interruptted");
		}

	}

}
public class Interrupt {



	public static void main(String[] args) throws Exception {
		Thread t=new Thread(new Task());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		t.interrupt();
	}
}
