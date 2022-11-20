package disk;

public class CustomerBook {
	private Customer[] data = new Customer[1000];
	public boolean addCustomer(Customer u) {
		if(data[u.id]!=null)
		{
			System.out.println("This id already linked to a customer");
			return false;
		}
		else
		{
			data[u.id] = u;
			System.out.println("Add successfully");
			return true;
		}
	
	}
	public Customer findCustomer(int id) {

			return data[id];

	}
	public void removeCustomer(int id) {
		if(data[id]!=null)
		{
			data[id]=null;
			System.out.println("remove successfully");

		}
		else
		{
			System.out.println("this customer does not exist");
		}

	}
	public void print() {
		
	}
	@Override
	public String toString() {
		String result = "";
		for (Customer customer : data) {
			result += customer+"\n";
		}
		return result;
	}
	public void peekCustomerId(String name) {
		boolean match=false;
		for (Customer customer : data) {
			if(customer!=null)
			if(name.equals(customer.getName()))
			{
				match=true;
					System.out.println("this customer's id is"+customer.id);
			}
		}
		if(match==false)
		{
			System.out.println("this customer does not exist");
		}
	}
	public int getCustomerId(String name) {
		int id=-1;
		for (Customer customer : data) {
			if(customer!=null)
			if(name.equals(customer.getName()))
			{
				id=customer.getId();
		
			}
		}
			return id;
	}
	public void setCustomer(Customer customer)
	{
		data[customer.getId()]=customer;
	}
}
