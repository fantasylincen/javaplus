package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import manager.DWType;

import config.activity.ActivityTempletCfg;

import user.UserInfo;
import util.ErrorCode;
import game.activity.ActivityManager;
import game.award.AwardInfo;
import game.award.AwardType;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;
import game.util.DragonAwardManager;

@EventDescrip(desc = "申请退出副本")
public class ExitEctypeEvents extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {

		short id		= buf.getShort();
		int guangkID	= buf.getInt();
		
		ByteBuffer buff = buildEmptyPackage( 256 );
		buff.putShort( user.getLevel() );
		buff.put( user.getEctypeStartLevel().get(guangkID) );
		
		// 如果是大龙
		short nId 			= ActivityTempletCfg.getMissionToId( id );
		List<AwardInfo> list= null;
		if( nId / 100 == 2 ){
			
			list 			= DragonAwardManager.getSesult();
			buff.put( (byte)list.size() );
			for( AwardInfo a : list )
				a.buildTransformStream( buff);
			
			// 关闭鼓舞
			ActivityManager.getInstance().getDragon().closeInspire( user.getUID() );
		}
		
		// 这里结算如果是邀请了高级玩家 的 花费
		int needmoney = user.getTeamManager().expertNeedMoney();
		if( needmoney != 0 ){
			user.changeAward( AwardType.CASH, -needmoney, "邀请高级玩家消耗金币", DWType.MISCELLANEOUS );
			UpdateManager.instance.update( user, UpdateType.U_3 );
		}
		
		//
		user.recordCurCTime( 0 );
		
		sendPackage( user.getCon(), buff );
		
		if( nId / 100 == 2 ){
			for( AwardInfo a : list )
				user.changeAward( a, "挑战大龙 退出奖励", DWType.MISCELLANEOUS );
		}
		
		if( user.getEctypeStatus() == ErrorCode.FIGHTER_STATUS ){
			Logs.error( user, "出现还没打完副本 就申请退出副本!" );
//			return;
		}
		
		user.ePropertyType 	= -1;
		// 这里就简单的 清空就行了
		user.getEctypeReconnectManager().remove();
		// 这里把 协助好友 清除掉
		user.getTeamManager().claerAssist();
		user.getTeamManager().updata();
	}

}
