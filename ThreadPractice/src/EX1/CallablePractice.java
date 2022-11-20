package EX1;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePractice {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exe=Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> result=new ArrayList<Future<Integer>>();
		for (int i = 0; i < 10; i++) {
		result.add(exe.submit(new CallableClass(i)));
		}
		for (Future<Integer> future : result) {
			System.out.println(future.get());
			exe.shutdownNow();
		}


	}
}
