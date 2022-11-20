package reusing_class;

class Cleanser
{
	private StringBuilder s=new StringBuilder("Cleanser");
	public void append(String a)
	{
		s.append(a);
	}
	public void f1() {
		append("f1()");

	}
	public void f2() {
		append("f2()");

	}
	@Override
	public String toString() {
		return s.toString();
	}
	public static void main(String[] args) {
		Cleanser cleanser=new Cleanser();
		cleanser.f1();
		cleanser.f2();System.out.println(cleanser);
	}
	
}
public class Detergent extends Cleanser{

	@Override
	public void f2() {
		append("Detergent.f2()");
		super.f2();
	}
	
	public void f3() {
		append("f3()");

	}
	public static void main(String[] args) {
		Detergent x=new Detergent();
		x.f1();
		x.f2();
		x.f3();
		System.out.println(x);
		System.out.println("testing the base");
		Cleanser.main(args);
	}
}
