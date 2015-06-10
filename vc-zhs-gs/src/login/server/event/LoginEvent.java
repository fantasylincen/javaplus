package login.server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import define.SystemCfg;

import user.UserInfo;
import util.UtilBase;
import game.events.EventDescrip;

@EventDescrip(desc = "登陆消息" )
public class LoginEvent extends ILEvent{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		
		
	}

	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		ByteBuffer buffer = buildEmptyPackage( 125 );
		
		buffer.put( SystemCfg.GAME_DISTRICT );
		UtilBase.encodeString( buffer, SystemCfg.SERVER_NAME );
		UtilBase.encodeString( buffer, SystemCfg.SERVER_ADDRESS );
		buffer.putInt( SystemCfg.PORT );
		
		sendPackage( con, buffer );
	}
	
}
