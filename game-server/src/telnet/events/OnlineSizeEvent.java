package telnet.events;

import java.io.PrintWriter;


import server.ServerManager;
import telnet.CommandBase;
import user.UserManager;

public class OnlineSizeEvent extends CommandBase{

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		
		if( !ServerManager.isOpen() ){
			out.print( "服务器尚未开启!\r\n" );
			return;
		}
		
		int size = UserManager.getInstance().getMaps().size();
		
		out.print( "当前服务器在线人数：" + size + "\r\n" );
		
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
