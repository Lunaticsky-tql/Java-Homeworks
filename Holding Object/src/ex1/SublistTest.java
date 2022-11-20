package ex1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MyClass {
	private static int counter;
	private int count = counter++;

	@Override
	public String toString() {

		return "MyClass" + count;
	}
}

public class SublistTest {

	public static void main(String[] args) {
		MyClass[] array = new MyClass[10];
		for (int i = 0; i < array.length; i++) {
			array[i] = new MyClass();
		}
		List<MyClass> list = new ArrayList<MyClass>(Arrays.asList(array));
		System.out.println("list=" + list);
		List<MyClass> sublist = list.subList(5, 8);
		System.out.println("sublist=" + sublist);
		sublist.clear();
		System.out.println("list=" + list);
	}

}
