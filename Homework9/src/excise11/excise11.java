package excise11;
import excise11.interfaceprocess.*;

class CharacterSwapper{
	static java.lang.String swap(String s)
	{
		StringBuilder stringBuilder=new StringBuilder(s);
		for (int i = 0; i < stringBuilder.length()-1; i+=2) {
			char c1=stringBuilder.charAt(i);
			char c2=stringBuilder.charAt(i+1);
			stringBuilder.setCharAt(i, c2);
			stringBuilder.setCharAt(i+1, c1);
		}
		return stringBuilder.toString();
	}
}
class SwapperAdapter implements Processer
{

	@Override
	public String name() {
		return CharacterSwapper.class.getSimpleName();
	}

	@Override
	public Object process(Object input) {
		return CharacterSwapper.swap((String)input);
	}
	
}
public class excise11
{

	public static void main(String[] args) {
		Apply.process(new SwapperAdapter(), "123456");
		Apply.process(new SwapperAdapter(), "abcdefg");
	}
}