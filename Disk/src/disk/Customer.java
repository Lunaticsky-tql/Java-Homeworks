package disk;

public class Customer {
	int id;
	String name;
	double money;
	private Disk[] diskBorrow=new Disk[1000];
	private Disk[] diskBuy=new Disk[1000];
	DiskBook note=new DiskBook();
	public Customer(int id, String name, int money) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
	}
	public Disk checkBorrowDisk(String cname)
	{
		double max=Double.NEGATIVE_INFINITY;
		Disk targetDisk=null;
		for (Disk disk : diskBorrow) {
			if(disk!=null)
			if(cname.equals(disk.getName()))
			{
					//if more than 1 disk is the same, get the most expensive one
					if(max<disk.getPrice())
					{
						max=disk.getPrice();
						targetDisk=disk;
					}
					
			}
		}
		return targetDisk;
	}

	public void addBorrowDisk(Disk disk)
	{
		diskBorrow[disk.getId()]=disk;
		
	}

	public void setDisk(Disk setDisk)
	{
		diskBorrow[setDisk.getId()]=setDisk;
		
	}
	public void deleteDisk(int id)
	{
		diskBorrow[id]=null;
		
	}
	public void addBuyDisk(Disk disk)
	{
		diskBuy[disk.getId()]=disk;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = (int) (prime * result + money);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		if (money != other.money)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", money=" + money + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double d) {
		this.money = d;
	}
	public DiskBook getNote() {
		return note;
	}
	public void setNote(DiskBook note) {
		this.note = note;
	}

}
