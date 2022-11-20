package reusing_class;

class CharSwaperAdapter implements Processor{

	@Override
	public String name() {
		return CharSwaper.class.getSimpleName();
	}

	@Override
	public String process(Object input) {
		return CharSwaper.swap((String)input);
	}

}
