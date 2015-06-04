package game.events.all.pvp;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "申请匹配信息")
public class PvpMateAppInfoEvent extends EventBase{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		ByteBuffer response 				= buildEmptyPackage( 32 );
		
		user.getDanGradingManager().putData( response );
		
		sendPackage( user.getCon(), response );
	}

}
