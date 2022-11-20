package excise10;

class Father
{
	public void method1() {
		System.out.println("method1 calling method2");
		method2();
	}
	public void method2() {
		System.out.println("method2 in father");

	}
}
class Son extends Father{
	@Override
	public void method2() {
		System.out.println("method2 in son");
	}
}
public class excise10{
	public static void main(String args[]) {
		Father x=new Son();
		x.method1();
	}
}