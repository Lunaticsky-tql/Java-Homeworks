package excise4;
class MyException extends Exception
{
	String messageString;
	public MyException(String msg)
	{
		this.messageString=msg;
	}
	void print()
	{
		System.out.println("message:"+messageString);
	}
}
public class excise4
{
	public static void main(String[] args) {
		try {
			throw new MyException("message want to print");
			
		} catch (MyException e) {
		
			e.print();		
			}
	}
}