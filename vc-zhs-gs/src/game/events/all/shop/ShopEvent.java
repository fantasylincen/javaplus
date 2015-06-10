package game.events.all.shop;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "商城系统  ")
public class ShopEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		byte type 				= buf.get();
		
		ShopEventHandle handle 	= ShopEventHandle.fromNumber( type );
		
		if( handle == null ) {
			Logs.error( user, "商城系统  没有找到二级报号[" + type + "]" );
			return;
		}
		
		ByteBuffer buffer 		= buildEmptyPackage( 2048 );
		
		buffer.put( type );
		
		if( handle.run( user, buf, buffer ) ) return;
		
		sendPackage( user.getCon(), buffer );		
	}

	
}
