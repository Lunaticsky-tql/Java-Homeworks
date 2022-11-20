package excise8;

class StaticTest
{
	static int i=47;

}
public class excise8 {
	
	public static void main(String[]args) {
		StaticTest st1=new StaticTest();
		StaticTest st2=new StaticTest();
		StaticTest st3=new StaticTest();
		System.out.println(st1.i);
		System.out.println(StaticTest.i);
		StaticTest.i++;
		System.out.println(st1.i);
		System.out.println(st2.i);
		System.out.println(st3.i);
		System.out.println(StaticTest.i);

	}
}
