package game.events.all.user;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;

import game.events.*;

@EventDescrip(desc = "玩家退出包")
public class UserExitEvent extends EventBase {


	@Override
	public void run ( UserInfo user, ByteBuffer buf ) throws IOException {
		System.out.println( this.getClass().getName() );
		ByteBuffer buff = ByteBuffer.allocate( 1024 );
		super.sendPackage( user.getCon(), buff );

	}


}
