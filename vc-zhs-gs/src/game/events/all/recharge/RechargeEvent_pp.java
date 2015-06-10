package game.events.all.recharge;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.UtilBase;
import game.events.EventBase;

public class RechargeEvent_pp extends EventBase {

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
	}

	public void run( UserInfo user, short code, String transaction_id ) throws IOException {
		if( user == null || !user.isOnline() ) return;
//		System.out.println( "进入" );
		ByteBuffer response = buildEmptyPackage( 1024 );
		response.putShort( code );
		UtilBase.encodeString( response, transaction_id );
		sendPackage( user.getCon(), response );
	}

}
