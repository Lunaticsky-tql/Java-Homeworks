package practice;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ConverseTXT {

	public static void main(String[] args) throws IOException {
		List<String> list=read("C:\\Users\\LENOVO\\Desktop\\新建文本文档.txt");
		for (ListIterator<String> it = list.listIterator(list.size()); it.hasPrevious();) {
			System.out.println(it.previous());
			
		}
	}

	private static List<String> read(String string) throws IOException {
		BufferedReader in=new BufferedReader(new FileReader(string));
		String s;
		List<String> list =new LinkedList<String>();
		while ((s=in.readLine())!=null) {
			list.add(s);
			
		}
		in.close();
		return list;
	}
}
