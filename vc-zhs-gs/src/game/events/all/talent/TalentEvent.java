package game.events.all.talent;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "天赋系统  ")
public class TalentEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		byte type = buf.get();
		
		ByteBuffer buffer = buildEmptyPackage( 1024 );
		
		buffer.put( type );
		
		TalentEventHandle handle = TalentEventHandle.fromNumber( type );
		
		if( handle == null ) {
			Logs.error( user, "天赋系统  没有找到二级报号[" + type + "]" );
			return;
		}
		
		if( handle.run( user, buf, buffer ) ) return;
		
		sendPackage( user.getCon(), buffer );
		
	}

}
