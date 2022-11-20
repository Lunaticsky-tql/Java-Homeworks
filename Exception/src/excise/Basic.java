package excise;

public class Basic {
public static void main(String[] args) {
	try {
		throw new Exception("an exception in main");
	} catch (Exception e) {
		System.out.println("e.getmessage():"+e.getMessage());
	}
	finally {
		System.out.println("finally");
	}
}
}
