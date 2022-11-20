package EX1;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableClass implements Callable<Integer> {

	private int i;
	public CallableClass(int i) {
		this.i=i;
	}
	@Override
	public Integer call() {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			System.out.println("interruptted");
		}
		return 2*i;
		
	}

	

}
