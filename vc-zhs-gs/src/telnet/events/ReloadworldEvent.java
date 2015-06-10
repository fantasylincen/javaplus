package telnet.events;

import java.io.PrintWriter;

import server.ServerManager;
import telnet.CommandBase;

public class ReloadworldEvent extends CommandBase {

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		ServerManager.reloadworld( out );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
