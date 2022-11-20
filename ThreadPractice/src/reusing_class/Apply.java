package reusing_class;

class Apply {
	public static void use(Processor charSwaperAdapter, Object string) {
		System.out.println(charSwaperAdapter.name());
		System.out.println(charSwaperAdapter.process(string));
	}
}
