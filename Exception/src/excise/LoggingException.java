package excise;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

class Log1 extends Exception {

	private static Logger logger=Logger.getLogger("logname");
	public Log1() {
		StringWriter trace=new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
}

public class LoggingException {

	public static void main(String[] args) {
		try {
			throw new Log1();
		} catch (Log1 e) {
			System.out.println(e);
		}
	}
}
