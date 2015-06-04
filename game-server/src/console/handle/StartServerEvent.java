package console.handle;

import java.io.PrintWriter;

import console.ICEven;


import server.ServerManager;

/**
 * 开启服务器 事件
 * @author DXF
 */
public class StartServerEvent extends ICEven{
	
	@Override
	public void run(PrintWriter out, String[] args) throws Exception {
		out.print( "\r\n" );
		ServerManager.startServer( out );
		out.print( "\r\n" );
	}

}
