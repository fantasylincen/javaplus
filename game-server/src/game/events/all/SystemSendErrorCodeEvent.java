package game.events.all;

import game.events.EventBase;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import user.UserInfo;
import util.ErrorCode;

/**
 * 主动传送一个错误代码到客户端，目前只用于用例调试
 * @author liukun
 * 2012-8-31 上午10:58:21
 */
public class SystemSendErrorCodeEvent extends EventBase {

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		ByteBuffer buffer = buildEmptyPackage( 1024 );
		buffer.put(buf);
		sendPackage( user.getCon(), buffer );


	}
	public void run( INonBlockingConnection con, ErrorCode code ) throws IOException {
		


	}

}
