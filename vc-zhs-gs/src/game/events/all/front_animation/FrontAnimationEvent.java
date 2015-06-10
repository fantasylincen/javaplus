package game.events.all.front_animation;


import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import user.UserInfo;
import frontanimation.FrontAnimation;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "开头动画 ")
public class FrontAnimationEvent extends EventBase{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		
	}

	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException{
		
		byte at = buf.get();
		
		ByteBuffer response = buildEmptyPackage( 10240 );
		
		ByteBuffer content 	= FrontAnimation.getInstance().get( at ).asReadOnlyBuffer();
		response.put( content );
		
		sendPackage( con, response );
	}
	
}
