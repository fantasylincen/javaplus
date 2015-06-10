package telnet.events;

import java.io.PrintWriter;


import server.ServerManager;
import telnet.CommandBase;

public class PrugeDatabaseEvent extends CommandBase{

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		
		if( !ServerManager.isClose() ){
			out.print( "请先关闭服务器!\r\n" );
			return;
		}
		
//		DatabaseUtil.prugeAll();
		
		out.print( "数据库清空完毕!\r\n" );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
