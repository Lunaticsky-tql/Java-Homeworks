package EX1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fib2 {

	public static void main(String[] args) {
	ExecutorService exec=Executors.newCachedThreadPool();
	for (int i = 1; i <=5; i++) {
		exec.execute(new EX1(i));
	}
	Thread.yield();
	exec.shutdown();
	exec=Executors.newCachedThreadPool();
	for (int i = 10; i <15; i++) {
		exec.execute(new EX1(i));
	}
	Thread.yield();
	exec.shutdown();
	exec=Executors.newCachedThreadPool();
	for (int i = 7; i <9; i++) {
		exec.execute(new EX1(i));
	}
	Thread.yield();
	exec.shutdown();
	}

}
