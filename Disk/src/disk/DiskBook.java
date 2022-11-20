package disk;

public class DiskBook {
	private Disk[] data = new Disk[1000];
	public void addNewDisk(Disk d) {
		int id = d.getId();
		Disk disk = findDisk(id);
		if(disk==null) {
			data[id]=d;
			System.out.println("Add successfully");
		}else {
			System.out.println("This id already linked to a disk");
		}
		
	}
	public void print() {
		for (Disk disk : data) {
			System.out.println(disk);
		}
	}
	public void removeDisk(int id) {
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
	public Disk findDisk(int id) {
		return data[id];
	}
	@Override
	public String toString() {
		String result = "";
		for (Disk disk : data) {
			if(disk!=null) {
				result += disk+"\n";
			}
		}
		return result;
//		return "DiskBook [data=" + Arrays.toString(data) + "]";
	}
	public void supplyDisk(int id,int number) {
		Disk disk = findDisk(id);
		if(disk==null) {
			System.out.println("Please choose add new disk");
		}else {
			int num = disk.getNum()+number;
			disk.setNum(num);
			System.out.println("Supply successfully");
		}

	}
	public void peekDiskId(String name,int price) {
		int min=Integer.MAX_VALUE;
		int id=-1;
		for (Disk disk : data) {
			if(disk!=null)
			if(name.equals(disk.getName())&&(double)price==disk.getPrice())
			{
					//if more than 1 disk is the same, show the less one
					if(min>disk.getNum())
					{
						min=disk.getNum();
						id=disk.getId();
					}
					
			}
		}
		if(id==-1)
		{
			System.out.println("This disk does not exist");
		}
		else {
			System.out.println("This disk's id is "+id);
		}
		
	}
	public int getDiskId(String name) {
		double min=Double.POSITIVE_INFINITY;
		int id=-1;
		for (Disk disk : data) {
			if(disk!=null)
			   if(name.equals(disk.getName()))
			   {
					//if more than 1 disk is the same, get the cheapest one
					if(min>disk.getPrice())
					{
						min=disk.getPrice();
						id=disk.getId();
					}
					
			}
		}
		return id;
	}
	public void setDisk(Disk disk)
	{
		data[disk.getId()]=disk;
	}

}
