package login.server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import server.ServerManager;
import user.UserInfo;
import user.UserManager;
import util.UtilBase;

public class GetUserdataEvent extends ILEvent{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		int uid = buf.getInt();
		
		UserInfo u = UserManager.getInstance().getByName(uid);
		
		ByteBuffer buffer = buildEmptyPackage( 1024 );
		buffer.putInt( uid );
		UtilBase.encodeString( buffer, u.getNickName() );
		buffer.putShort( u.getLevel() );
		sendPackage( ServerManager.getLoginCon(), buffer );
		
	}

}
