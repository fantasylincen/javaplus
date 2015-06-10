package game.events.all.tickling;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.UtilBase;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "反馈信息")
public class TicklingEvent extends EventBase {

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		String content = UtilBase.decodeString(buf);
		
		TicklingDB.put( content, user );
		
		ByteBuffer buffer = buildEmptyPackage( 12 );
		buffer.putShort( (short) 0 );
		sendPackage( user.getCon(), buffer );
	}

}
