package excise19;
class Test{
	void OverloadingVarage(String...args)
	{
for(String s:args) {
	System.out.print(s);
	System.out.print(" ");
	}
System.out.println();
	}
	
}
public class excise19{
	
	public static void main(String[] args) {
		String s1="java";
		     String s2="javascript";
		String[] s3= {"C","C#","C++"};
		Test test=new Test();
		test.OverloadingVarage(s1);
		test.OverloadingVarage(s1,s2);
		test.OverloadingVarage(s3);
		
	}
}