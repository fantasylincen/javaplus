package telnet.events;

import java.io.PrintWriter;

import server.ServerManager;
import telnet.CommandBase;

public class StopServerEvent extends CommandBase{
	
	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		out.print( "\r\n" );
		ServerManager.stopServer( out );
		out.print( "\r\n" );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
