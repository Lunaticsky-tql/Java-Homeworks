package EX1;

import java.util.Arrays;

public class EX1 implements Runnable{

	private int count;
	private final int n;
	public EX1(int i)  {
		this.n=i;
		
	}

	public static void main(String[] args) {
		for (int i = 1; i <=5 ; i++) {
			new Thread(new EX1(i)).start();
		}
	}

	@Override
	public void run() {
		Integer[] sequence =new Integer[n];
		for (int i = 0; i < n; i++) {
			sequence[i]=next();
		}
		System.out.println("seq of "+n+" "+Arrays.toString(sequence));
	}

	private Integer next() {
		return fib(count++);
	}

	private Integer fib(int i) {
	if(i<2)
		return 1;
	return fib(i-2)+fib(i-1);
	}
}
