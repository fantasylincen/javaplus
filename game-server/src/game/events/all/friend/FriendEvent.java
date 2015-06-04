package game.events.all.friend;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "好友系统   1：申请好友列表      2：赠送体力    3：领取体力")
public class FriendEvent extends EventBase{

	
	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		byte type = buf.get();
		
		FriendEventHandle handle = FriendEventHandle.fromNumber( type );
		
		if( handle == null ) {
			Logs.error( user, "邮件系统  没有找到二级报号[" + type + "]" );
			return;
		}
		
		ByteBuffer buffer = buildEmptyPackage( 4096 );
		
		buffer.put( type );
		
		if( handle.run( user, buf, buffer ) ) return;
		
		sendPackage( user.getCon(), buffer );
	}
	
}
