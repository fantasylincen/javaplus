package game.events.all.invitingfriends;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "好友邀请系统  ")
public class InvitingFriendsEvent extends EventBase {

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		byte type 							= buf.get();
		
		InvitingFriendsEventHandle handle 	= InvitingFriendsEventHandle.fromNumber( type );
		
		if( handle == null ) {
			Logs.error( user, "好友邀请系统  没有找到二级报号[" + type + "]" );
			return;
		}
		
		ByteBuffer buffer 					= buildEmptyPackage( 10240 );
		
		buffer.put( type );
		
		if( handle.run( user, buf, buffer ) ) return;
		
		sendPackage( user.getCon(), buffer );
		
	}
	
	public void run( UserInfo user, InvitingFriendsEventHandle handle ) throws IOException{
		
		ByteBuffer buffer 					= buildEmptyPackage( 10240 );
		
		buffer.put( handle.toNumber() );
		
		if( handle.run( user, null, buffer ) ) return;
		
		sendPackage( user.getCon(), buffer );
	}

}
