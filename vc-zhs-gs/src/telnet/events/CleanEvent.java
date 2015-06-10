package telnet.events;

import java.io.PrintWriter;

import telnet.CommandBase;

public class CleanEvent extends CommandBase {

	@Override
	public void run( PrintWriter out, byte jurisdiction, String... args ) throws Exception {
		
		char clscode[] = {0x1B, 0x5B, 0x48, 0x1B, 0x5B, 0x4A};
		out.print( clscode );
		
	}

	@Override
	public void clear(PrintWriter out) {
	}

}
