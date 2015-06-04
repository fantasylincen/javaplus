package console.handle;

import java.io.PrintWriter;

import console.ICEven;

import server.ServerManager;

public class StopServerEvent extends ICEven{
	
	@Override
	public void run(PrintWriter out, String[] args) throws Exception {
		out.print( "\r\n" );
		ServerManager.stopServer( out );
		out.print( "\r\n" );
	}
}
