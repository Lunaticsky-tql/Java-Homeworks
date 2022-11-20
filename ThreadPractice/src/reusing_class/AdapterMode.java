package reusing_class;

public class AdapterMode {

	public static void main(String[] args) {
		Apply.use(new CharSwaperAdapter(),"1234");
		Apply.use(new CharSwaperAdapter(),"abcd");
	}
}
