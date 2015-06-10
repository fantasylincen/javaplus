package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import user.UserInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.fightoffriend.FightOfFriendBase;
import game.fightoffriend.FightOfFriendManager;

@EventDescrip(desc = "副本战斗前 申请邀请好友列表")
public class FightOfFriendEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		byte page 						= buf.get();
		
		FightOfFriendManager fManager	= user.getFightOfFriendManager();
		
		// 先刷新一下
		fManager.updata();
		
		List<FightOfFriendBase> list 	= fManager.get(page);
//		list.clear();
		
		ByteBuffer response 			= buildEmptyPackage( 1024 );
		
		response.put( fManager.getCurPage() );
		response.put( fManager.getMaxPage() );
		response.putShort( fManager.getCurNum() );
		response.putShort( (short)40 );
		
		response.put( (byte) list.size() );
		for( FightOfFriendBase f : list )
			f.putData( response, user.getTeamManager().getCaptain().getUID() );

		sendPackage( user.getCon(), response );
	}

}
