package excise14;

class Test{
	void compare(String a,String b)
	{
		System.out.println(a==b);
		System.out.println(a!=b);
		System.out.println(a.equals(b));
	}
}
public class excise14{
	
	public static void main(String[] args) {

		String s1="aaaa";
		String s2="aaaa";
		String s3="aaab";
		Test test=new Test();
		test.compare(s1, s2);
		test.compare(s1, s3);
	}
}