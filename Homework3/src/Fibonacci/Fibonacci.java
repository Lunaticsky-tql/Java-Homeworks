package Fibonacci;

class Test{
	void fib(int a)
	{
		System.out.print("1 1 ");
	int c=1,d=1;
		for(int i=1;i<=a-2;++i) {
			Integer sum=c+d;
			c=d;
			d=sum;
			System.out.print(sum+" ");
		}
	}
}
public class Fibonacci{
	
	public static void main(String[] args) {


		int a=10;
		Test test=new Test();
		test.fib(a);
	}
}