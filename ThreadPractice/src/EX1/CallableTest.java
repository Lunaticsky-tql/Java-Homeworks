package EX1;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResult implements Callable<String> {

	private int id;
	public TaskWithResult(int id) {
		this.id=id;
	}

	@Override
	public String call() throws Exception {
		return "result:"+id;
	}
	}
public class CallableTest {
	
public static void main(String[] args) {
	ExecutorService exe=Executors.newCachedThreadPool();
	ArrayList<Future<String>> results=new ArrayList<Future<String>>();
	for (int i = 0; i < 10; i++) {
		results.add(exe.submit(new TaskWithResult(i)));
	}
	for (Future<String> future : results) {
		try {
			System.out.println(future.get());
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			exe.shutdown();
		}
	}
	
}
}
