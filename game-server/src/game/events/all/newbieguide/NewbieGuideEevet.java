package game.events.all.newbieguide;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "新手引导")
public class NewbieGuideEevet extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		short ngid = buf.getShort();
		user.setNewbieGuideID(ngid);
		
		ByteBuffer buffer = buildEmptyPackage( 10 );
		sendPackage( user.getCon(), buffer );
	}

}
