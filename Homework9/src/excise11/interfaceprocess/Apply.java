package excise11.interfaceprocess;

public class Apply {

	public static void process(Processer p,Object s)
	{
		System.out.println("Using Processor "+p.name());
		System.out.println(p.process(s));
	}
}
