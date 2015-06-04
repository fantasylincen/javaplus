package telnet.events;

import java.io.PrintWriter;

import server.ServerManager;
import sololive.ip.IPRecord;
import sololive.ip.IPSeeker;
import telnet.CommandBase;
import user.UserManager;

/**
 * 生成玩家当前所在地区
 * @author DXF
 */
public class PlayerCurrentRegionEvent extends CommandBase{

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		
		if( !ServerManager.isOpen() ){
			out.print( "请先开启服务器!\r\n" );
			return;
		}
		
		if( UserManager.getInstance().getIPRecord().isEmpty() ){
			out.print( "没有找到玩家数据!\r\n" );
			return;
		}
		
		out.print( "\r\n" );
		for( IPRecord ipr : UserManager.getInstance().getIPRecord().values() )
		{
			
			String content 	= ipr.name + "\t------";
			
			content			+= IPSeeker.getInstance().getAddress( ipr.ip );
		
			out.print( content + "\r\n" );
		}		
		out.print( "\r\n" );
		out.print( "生成完毕！\r\n" );
		
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
