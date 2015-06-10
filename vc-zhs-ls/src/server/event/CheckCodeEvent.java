package server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import server.ServerManager;
import util.UtilBase;

import events.EventBase;

/**
 * 核对验证码
 * @author DXF
 */
public class CheckCodeEvent extends EventBase{

	@Override
	public void run(INonBlockingConnection con, String... args) throws IOException {
		
	}

	@Override
	public void run(INonBlockingConnection con, ByteBuffer buf) throws IOException {
		
		byte serverid	= buf.get();
		int index		= buf.getInt();
		String key		= UtilBase.decodeString(buf);
		
		// 检查 是否有这个验证码
		/*byte code		= */ServerManager.getInstance().checkCode( serverid, index, key );
		
//		ByteBuffer response  = buildEmptyPackage( 256 );
//		response.put( code );
//		response.putInt( index );
//		UtilBase.encodeString( response, UserManager.getInstance().getUID(index) );
//		sendPackage( con, response );
		
	}

}
