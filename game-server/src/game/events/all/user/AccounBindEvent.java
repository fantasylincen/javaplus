package game.events.all.user;

import game.events.EventBase;
import game.events.EventDescrip;

import java.io.IOException;
import java.nio.ByteBuffer;

import config.saward.SAwardType;


import user.UserInfo;

@EventDescrip(desc = "账号绑定" )
public class AccounBindEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		user.getAwardManager().record( SAwardType.BDA, 1 );
		
		ByteBuffer buffer = buildEmptyPackage( 10 );
		sendPackage( user.getCon(), buffer );
	}

}
