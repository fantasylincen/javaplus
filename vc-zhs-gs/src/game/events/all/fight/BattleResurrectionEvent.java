package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.ErrorCode;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

@EventDescrip(desc = "战斗复活信息")
public class BattleResurrectionEvent extends EventBase{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		int  uid	= buf.getInt();
		
//		if( user.getHeroManager().getHero(uid) == null ){
//			CLog.error( user, "申请战斗复活 出错  找不到UID=" + uid );
//			return;
//		}
		
		ErrorCode code 	= user.getTeamManager().resirrection( uid );
		
		ByteBuffer data = buildEmptyPackage( 32 );
		data.putShort( (short) code.ordinal() );
		data.putInt( uid );
		sendPackage( user.getCon(), data );
		
		UpdateManager.instance.update( user, UpdateType.U_3 );
	}
	
}
