package telnet.events;

import java.io.PrintWriter;


import server.ServerManager;
import telnet.CommandBase;

public class CommandsEvent extends CommandBase {

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		ServerManager.printCommands( out, jurisdiction );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
