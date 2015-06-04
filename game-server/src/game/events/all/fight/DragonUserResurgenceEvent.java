package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.ErrorCode;
import game.activity.ActivityManager;
import game.activity.DragonBase;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "挑战大龙 - 玩家复活")
public class DragonUserResurgenceEvent extends EventBase {

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		byte type 			= buf.get(); // 0，时间到了 1，水晶复活 2，金币复活
		
		DragonBase dragon 	= ActivityManager.getInstance().getDragon();
		
		ErrorCode code 		= dragon.resurgence( user, type );
		
		ByteBuffer response = buildEmptyPackage( 32 );
		response.putShort( (short) code.ordinal() );
		if( code == ErrorCode.CHALLENGE_THE_DRAGON_RESURGENCE )
			response.putInt( dragon.get( user.getUID() ).getResidueTime() );
		sendPackage( user.getCon(), response );
		
	}

}
