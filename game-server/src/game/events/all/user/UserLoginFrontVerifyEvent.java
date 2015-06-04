package game.events.all.user;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import recharge.lb.LbVerifyPurchase;

import user.UserInfo;
import util.UtilBase;
import game.events.EventBase;

public class UserLoginFrontVerifyEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		String sdk 	= UtilBase.decodeString(buf);
		String app 	= UtilBase.decodeString(buf);
		String uin  = UtilBase.decodeString(buf);
		String sess = UtilBase.decodeString(buf);
		
		boolean ret = LbVerifyPurchase.loginVerify( sdk, app, uin, sess );
		
		ByteBuffer buffer 	= buildEmptyPackage( 32 );
		buffer.putShort( (short) (ret ? 0 : -1) );
		sendPackage( user.getCon(), buffer );
	}

	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException{
		String sdk 	= UtilBase.decodeString(buf);
		String app 	= UtilBase.decodeString(buf);
		String uin  = UtilBase.decodeString(buf);
		String sess = UtilBase.decodeString(buf);
		
		boolean ret = LbVerifyPurchase.loginVerify( sdk, app, uin, sess );
		
		ByteBuffer buffer 	= buildEmptyPackage( 32 );
		buffer.putShort( (short) (ret ? 0 : -1) );
		sendPackage( con, buffer );
	}
	
}
