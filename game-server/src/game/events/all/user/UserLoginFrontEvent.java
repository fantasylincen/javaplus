package game.events.all.user;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.xsocket.connection.INonBlockingConnection;

import user.UserInfo;
import user.UserManager;
import util.UtilBase;
import game.events.EventBase;

public class UserLoginFrontEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
	}

	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		int uid = buf.getInt();
		
		List<UserInfo> list = UserManager.getInstance().getUserList( uid );
		
		ByteBuffer buffer 	= buildEmptyPackage( 1024 );
		buffer.put( (byte)list.size() );
		for( UserInfo user : list ){
			buffer.putInt( user.getUID() );
			UtilBase.encodeString( buffer, user.getNickName() );
			buffer.putShort( user.getLevel() );
		}
		sendPackage( con, buffer );
	}
	
}
