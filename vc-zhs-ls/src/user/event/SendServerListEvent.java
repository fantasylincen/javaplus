package user.event;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.xsocket.connection.INonBlockingConnection;

import server.ServerInfo;
import server.ServerManager;
import user.UserManager;
import util.UtilBase;

import events.EventBase;
import events.EventDescrip;

@EventDescrip(desc = "申请服务器列表信息" )
public class SendServerListEvent extends EventBase{

	@Override
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		
		int uid					= buf.getInt();
		
		List<ServerInfo> lists	= ServerManager.getInstance().getStartServer( uid );
		
		// 上次 登陆的区
		short lastLoginID		= UserManager.getInstance().getByDistrictID( uid );
		
		
		ByteBuffer buffer 		= buildEmptyPackage( 512 );
		buffer.put( (byte) lastLoginID );
		
//		区ID		：Byte
//		区名字	：String
//		状态		：Byte（ 0，流畅  1，良好  2，拥挤  3，爆满 ）
//		IP		：String
//		Port	：Int
		buffer.put( (byte) lists.size() );
		
		for( ServerInfo server : lists ){
			buffer.put( server.getServerID() );
			UtilBase.encodeString( buffer, server.getServerName() );
			buffer.put( server.getStatus().toNum() );
			UtilBase.encodeString( buffer, server.getIp() );
			buffer.putInt( server.getPort() );
		}
		sendPackage( con, buffer );
	}

	@Override
	public void run(INonBlockingConnection con, String... args) throws IOException {
		
	}

}
