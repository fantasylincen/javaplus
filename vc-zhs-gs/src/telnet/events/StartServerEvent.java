package telnet.events;

import java.io.PrintWriter;


import server.ServerManager;
import telnet.CommandBase;

/**
 * 开启服务器 事件
 * @author DXF
 */
public class StartServerEvent extends CommandBase{
	
	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		out.print( "\r\n" );
		ServerManager.startServer( out );
		out.print( "\r\n" );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
