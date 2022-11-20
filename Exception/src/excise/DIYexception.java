package excise;

class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String s;
	public MyException(String string) {
		s=string;
	}

	public void printMsg() {
		System.out.println("mes=="+s);
	}
}

public class DIYexception {

	public static void main(String[] args) {
		try {
			throw new MyException("message");
		} catch (MyException e) {
			e.printMsg();
		}
	}
}
