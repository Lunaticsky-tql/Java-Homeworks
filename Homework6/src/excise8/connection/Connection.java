package excise8.connection;


public class Connection
{
	private static int numberofobject=0;
	private int id=numberofobject++;
	 Connection() {}
	public String toString() 
	{
		return "Connection"+id;
	}
}
