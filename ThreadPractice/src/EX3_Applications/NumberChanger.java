package EX3_Applications;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class Changer implements Runnable {

	private static int sum;
	private static Object key="key";
	private int i;
	private boolean b;
	public Changer(boolean b, int i) {
		this.i=i;
		this.b=b;
	}

	@Override
	public void run() {
		synchronized(key) {
			sum=b==true?sum+i:sum-i;
			System.out.println(sum);	
		}
	}

}

public class NumberChanger {

	public static void main(String[] args) {
		ExecutorService exe=Executors.newCachedThreadPool();
		for (int i = 0; i < 8; i++) {
			boolean b=i%2==0?true:false;
			exe.execute(new Changer(b,i));
			
		}
	}
}
