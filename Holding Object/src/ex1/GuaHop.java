package ex1;

import java.util.ArrayList;
import java.util.Iterator;
class Gua {


	private int num;
	
	 public Gua(int num) {
		 this.num = num;
	}

	public void hop() {
		System.out.printf("Gua%d is hopping", num);
		System.out.println("");
	}
	
}

public class GuaHop {

public static void main(String[] args) {
	ArrayList<Gua> guas=new ArrayList<Gua>();
	for (int i = 0; i < 10; i++) {
		guas.add(new Gua(i));
	}
	for (int i = 0; i < guas.size(); i++) {
		guas.get(i).hop();
	}
	for (Iterator<Gua> iterator = guas.iterator(); iterator.hasNext();iterator.next().hop());
		
		
}
}
