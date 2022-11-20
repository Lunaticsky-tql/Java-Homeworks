package excise8;
import excise8.connection.*;

public class excise8{
	public static void main(String args[]) {
		Connection c=ConnectionManager.getConnection();
		while(c!=null) {
			System.out.println(c);
			c=ConnectionManager.getConnection();
		}
		}
}