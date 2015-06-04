package game.events.all.award;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "系统奖励")
public class SystemAwardEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		byte type = buf.get();
		
		SystemAwardEventHandle handle = SystemAwardEventHandle.fromNumber( type );
		
		if( handle == null ) {
			Logs.error( user, "系统奖励  没有找到二级报号[" + type + "]" );
			return;
		}
		
		ByteBuffer buffer = buildEmptyPackage( 1024 );
		
		buffer.put( type );
		
		if( handle.run( user, buf, buffer ) ) return;
		
		sendPackage( user.getCon(), buffer );
	}

}
