package telnet;

import java.io.PrintWriter;

public abstract class CommandBase {

	public abstract void run( PrintWriter out, byte jurisdiction, String ... args ) throws Exception;

	public abstract void clear(PrintWriter out) ;
}
