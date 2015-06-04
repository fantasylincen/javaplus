package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;

import manager.DWType;

import config.mission.InstanceTemplet;
import config.mission.InstanceTempletCfg;
import config.mission.TollgateTemplet;
import config.mission.TollgateTempletCfg;


import user.UserInfo;
import util.ErrorCode;
import game.award.AwardType;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;

@EventDescrip(desc = "副本 战斗开始")
public class FightStartEvent extends EventBase {

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		int assistID				= buf.getInt();
		short ectypeID				= buf.getShort();
		int missionId				= buf.getInt();

		byte ePropertyType			= buf.get();
		
		ByteBuffer response 		= buildEmptyPackage( 16 );
		
		if( user.getEctypeStatus() == ErrorCode.FIGHTER_STATUS )
			return;
		
		ErrorCode code 				= user.getHeroManager().getIsBagFull();
		
		user.setEctypeStatus( ErrorCode.UNKNOW_ERROR );
		// ID检查
		if( checkID( user, ectypeID, missionId  ) ) return;
		
		// 先检测是否能进入这个副本
		if( code == ErrorCode.SUCCESS )
			code 					= user.getEctypeManager().isCanEnter( ectypeID, missionId );
		
		user.ePropertyType 			= -1;
		// 这里看 是否 要提升属性 如果要提升属性 那么先将钱扣掉
		if( code == ErrorCode.SUCCESS && ePropertyType != -1 )
			code = checkEPropertyH( user, ePropertyType );
		
		response.putShort( (short) code.ordinal() );

		if( code == ErrorCode.SUCCESS ){
			
			//
			user.recordCurCTime( 0 );
			
			// 设置成战斗状态
			user.setEctypeStatus( ErrorCode.FIGHTER_STATUS );
			
			user.getTeamManager().setAssistID( assistID );
			
			// 将记录星级  初始为1 
			user.getEctypeReconnectManager().starLevel = 0;
			
			//
			user.getEctypeReconnectManager().remove();
		}
		
		sendPackage( user.getCon(), response );
		
		// 及时刷新一下 避免 出错
		if( code == ErrorCode.SUCCESS )
			user.getTeamManager().updata();
	}

	// 这里看 是否 要提升属性 如果要提升属性 那么先将钱扣掉
	private ErrorCode checkEPropertyH( UserInfo user, byte ePropertyType ) {
		
		// 根据类型 获取需要花费的钱
		int result 		= -1;
		if( ePropertyType == 1 ){
			result		= user.changeAward( AwardType.CASH, -100000, "提升属性花费", DWType.MISCELLANEOUS );
		}else{
			result		= user.changeAward( AwardType.GOLD, -30, "提升属性花费", DWType.MISCELLANEOUS );
		}
		
		if( result != -1 ){
			user.ePropertyType = ePropertyType;
			UpdateManager.instance.update( user, ePropertyType == 1 ? UpdateType.U_3 : UpdateType.U_4  );
		}else
			return ErrorCode.USER_CASH_NOT_ENOUTH;
		return ErrorCode.SUCCESS;
	}

	// 检查 所有ID 是否有错 
	private boolean checkID(UserInfo user, short ectypeID, int missionId ) {
	
		InstanceTemplet instanceTemplet = InstanceTempletCfg.getTempletById( ectypeID );
		
		if( instanceTemplet == null ){
			Logs.error( user, "申请战斗 - 错误的副本 ID=" + ectypeID );
			return true;
		}
		
		if( !instanceTemplet.isHaveID( missionId ) ){
			Logs.error( user, "申请战斗 - 副本ID=" + ectypeID + "没有关卡ID=" + missionId );
			return true;
		}
		
		TollgateTemplet missionTemplet = TollgateTempletCfg.getTempletById( missionId );
		if( missionTemplet == null ){
			Logs.error( user, "申请战斗 - 错误的关卡 ID=" + missionId );
			return true;
		}
		
		return false;
	}
	
}
