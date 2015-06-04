package login.server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import server.ServerManager;
import user.UserInfo;
import util.UtilBase;

public class AccounBindEvent extends ILEvent{
	
	
	public void run( String account, String password ) throws IOException {
		ByteBuffer buffer = buildEmptyPackage( 256 );
		UtilBase.encodeString( buffer, account );
		UtilBase.encodeString( buffer, password );
		sendPackage( ServerManager.getLoginCon(), buffer );
	}

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
	}
	
}
