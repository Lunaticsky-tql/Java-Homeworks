package excise8.connection;


public class ConnectionManager{

	private static Connection[] list= new Connection[10];
	private static int numberOfObject=0;
	static {
	
		for (int i = 0; i < list.length; i++) {
			list[i]=new Connection();
		}
	}
	public static Connection getConnection()
	{
		if(numberOfObject<list.length)
		{
			return list[numberOfObject++];
		}
		return null;
	}
}
