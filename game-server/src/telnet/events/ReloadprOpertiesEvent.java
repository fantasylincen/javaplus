package telnet.events;

import java.io.PrintWriter;

import server.ServerManager;
import telnet.CommandBase;

public class ReloadprOpertiesEvent extends CommandBase{

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		ServerManager.reloadprOperties( out );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
