package game.events.all.base;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import user.UserInfo;
import game.award.AwardInfo;
import game.events.EventBase;
import game.events.EventDescrip;

/**
 * 告知玩家获得了什么奖励，通常是由服务器主动发出
 * @author liukun
 *
 */

@EventDescrip(desc = "告知玩家获得了什么奖励，通常是由服务器主动发出")
public class SendAwardEvent extends EventBase{

	private static final int PACK_LEN = 1024;
	
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 确保在外层加过锁
	 * @param user
	 * @param awards
	 * @throws IOException
	 */
	public void run( UserInfo user, List<AwardInfo> awards ) throws IOException{
		ByteBuffer response = buildEmptyPackage( PACK_LEN );
		for( AwardInfo award : awards ){
			award.buildTransformStream( response );
		}
		sendPackage( user.getCon(), response );
	}

}
