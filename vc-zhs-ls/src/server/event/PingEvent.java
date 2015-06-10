package server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import server.ServerManager;

import events.EventBase;

/**
 * Ping
 * @author DXF
 */
public class PingEvent extends EventBase{

	@Override
	public void run( INonBlockingConnection con, String... args ) throws IOException {
		
		int t = Integer.parseInt( args[0] );
		
		ByteBuffer respond = buildEmptyPackage( 14 );
		respond.putInt( t );
		sendPackage( con, respond );
	}

	@Override
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		byte serverid			= buf.get();
		int usernum				= buf.getInt(); // 当前在线人数
		ServerManager.getInstance().set( serverid, usernum );
		
//		ByteBuffer response  = buildEmptyPackage( 18 );
//		response.putInt( code == 1 ? SystemTimer.currentTimeSecond() : -1 );
//		sendPackage( con, response );
	}

}
