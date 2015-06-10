package game.events.all.recharge;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.UtilBase;
import game.events.EventBase;

public class RechargeEvent_lb extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
	}

	public void run( UserInfo user, short code, String tcd, int rmb ) throws IOException{
		
		ByteBuffer response = buildEmptyPackage( 1024 );
		response.putShort( code );
		UtilBase.encodeString(response, tcd);
		response.putInt( rmb );
		sendPackage( user.getCon(), response );
		
//		System.out.println( "user=" + user + ", con=" + user.getCon() + ", 已发送"  );
	}
	
}
