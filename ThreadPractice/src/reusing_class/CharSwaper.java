package reusing_class;

public class CharSwaper {

	public static String swap(String s) {
	
		StringBuilder sb=new StringBuilder(s);
		for (int i = 0; i < sb.length()-1; i+=2) {
			char a=sb.charAt(i);
			char b=sb.charAt(i+1);
			sb.setCharAt(i, b);
			sb.setCharAt(i+1, a);
		}
		return sb.toString();
	}
}
