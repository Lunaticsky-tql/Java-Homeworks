package excise5;
class Dog{
	String name;
	String says;
}
public class excise5{
	
	public static void main(String[] args) {

		Dog dog1 =new Dog();
		Dog dog2 =new Dog();
		dog1.name="spot";
		dog2.name="scruffy";
		dog1.says="Ruff";
		dog2.says="Wurf";
		System.out.println(dog1.name+" "+dog1.says);
		System.out.println(dog2.name+" "+dog2.says);
	}
}