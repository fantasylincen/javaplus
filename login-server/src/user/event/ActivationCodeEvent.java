package user.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import server.ServerManager;
import user.UserManager;
import user.activationcode.ActivationCodeManager;
import util.SystemTimer;
import util.UtilBase;
import util.md5.MD5;

import events.EventBase;
import events.EventDescrip;

@EventDescrip(desc = "激活账号" )
public class ActivationCodeEvent extends EventBase{

	@Override
	public void run(INonBlockingConnection con, String... args) throws IOException {
		
	}

	@Override
	public void run(INonBlockingConnection con, ByteBuffer buf) throws IOException {
		
		int uid		= buf.getInt();
		String ac	= UtilBase.decodeString(buf);
		
		byte code 	= ActivationCodeManager.getInstance().get( ac );
		
		ByteBuffer buffer 	= buildEmptyPackage( 125 );
		buffer.put( code );
		
		if( code == 0 ){
			// 生成验证码
			String key			= MD5.md5( SystemTimer.currentTimeMillis() + uid + "vc2013" );
			String identifying 	= UserManager.getInstance().getUID(uid);
			// 记录验证码
			ServerManager.getInstance().addKey( uid, key, identifying );
			
			UtilBase.encodeString( buffer, key );
		}
		
		sendPackage( con, buffer );
	}

}
