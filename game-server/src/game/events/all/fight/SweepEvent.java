package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import manager.DWType;

import config.mission.EctypeType;
import config.mission.TollgateTemplet;
import config.mission.TollgateTempletCfg;

import user.UserInfo;
import util.ErrorCode;
import game.award.AwardInfo;
import game.award.AwardType;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

/**
 * 扫荡
 * @author DXF
 */
@EventDescrip(desc = "申请扫荡")
public class SweepEvent extends EventBase {

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
//		synchronized (user) {
			
//			long begin				= System.nanoTime();
			
			short ectypeID			= buf.getShort();
			int missionId 			= buf.getInt();
			byte sweepCount			= buf.get();
			List<SweepInfo> sweepInfo = null;
			
			// 扫荡前检查
			ErrorCode code			= check( user, ectypeID, missionId, sweepCount );
			
			do{
				
				if( code != ErrorCode.SUCCESS ) break;
				
				// 开始扫荡
				sweepInfo = startSweep( missionId, sweepCount );
				
			}while(false);
			
//			System.out.println("扫荡" + sweepCount + "次   战斗逻辑耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒");
			
			
			SweepInfo award			= null;
			ByteBuffer buffer 		= buildEmptyPackage( 4089 );
			buffer.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				award = putBuffer( sweepInfo, buffer );
			sendPackage( user.getCon(), buffer );
			
			// 开始发放奖励
			if( award != null ){
				user.changeAward( AwardType.CASH, award.cash, "扫荡奖励", DWType.MISCELLANEOUS );
				user.changeAward( AwardType.EXP, award.exp, "扫荡奖励", DWType.MISCELLANEOUS );
				user.changeAward( AwardType.TROPHY, award.trophy, "扫荡奖励", DWType.MISCELLANEOUS );
				// 发送准备奖励
				for( AwardInfo a : award.content )
					user.changeAward( a, "扫荡奖励", DWType.MISCELLANEOUS );
				
				UpdateManager.instance.update( user, UpdateType.U_3 );
				UpdateManager.instance.update( user, UpdateType.U_1 );
				UpdateManager.instance.update( user, UpdateType.U_29 );
			}
			
//		}
	}

	private SweepInfo putBuffer( List<SweepInfo> sweepInfo, ByteBuffer buffer ) {
		
		SweepInfo reslut 	= new SweepInfo();
		reslut.content 		= new ArrayList<AwardInfo>();
		
		buffer.put( (byte) sweepInfo.size() );
		for( SweepInfo s : sweepInfo ){
			
//			buffer.putInt( s.cash );
//			buffer.putInt( s.exp );
			buffer.put( s.trophy );
			buffer.put( (byte) (s.content == null ? 0 : s.content.size()) );
			if( s.content != null )
				for ( AwardInfo a : s.content ){
					if( a.getAward() == AwardType.GOLD )
						continue;
					buffer.put( a.getAward().toNumber() );
					if( a.getAward() == AwardType.HERO ){
						byte colour = 0;
						byte level	= 0;
						if( a.getArguments() != null && a.getArguments().length >= 3 ){
							colour	= (byte) a.getArguments()[1];
							level	= (byte) a.getArguments()[2];
						}
						buffer.put( colour );
						buffer.put( level );
					}
					buffer.putInt( a.getPropId() );
					buffer.putInt( a.getNumber() );
				}
			
			
			reslut.cash 	+= s.cash;
			reslut.exp 		+= s.exp;
			reslut.trophy 	+= s.trophy;
			if( s.content != null )
				reslut.content.addAll( s.content );
		}
		
		return reslut;
	}

	private List<SweepInfo> startSweep( int missionId, byte sweepCount ) {
		
		TollgateTemplet 	missionTemplet	= TollgateTempletCfg.getTempletById( missionId );
		
		List<SweepInfo>	reslut				= new ArrayList<SweepInfo>();
		
		for( int o = 0; o < sweepCount; o++ ){
			SweepInfo sweep	= new SweepInfo();
			sweep.cash		= missionTemplet.getAwardGold();
			sweep.exp		= missionTemplet.getAwardPlayerExp();
			sweep.trophy	= missionTemplet.getTrophy();
			sweep.content	= new ArrayList<AwardInfo>();
			for( int i = 0; i < missionTemplet.getThelvNum(); i++ ){
				List<AwardInfo> a	= missionTemplet.getSweepAward( i );
				sweep.content.addAll( a );
			}
			
			reslut.add(sweep);
		}
		
		return reslut;
	}

	/**
	 * 扫地前检查 是否合法
	 * @param user
	 * @param missionId
	 * @param missionId 
	 * @param sweepCount
	 * @return
	 */
	private ErrorCode check( UserInfo user, short id, int missionId, byte sweepCount ) {
		
		// 是否VIP2
		if( user.getVipLevel() < 2 )
			return ErrorCode.UNKNOW_ERROR;
		
		// 是否3星
		if( user.getEctypeStartLevel().get(missionId) != 3 )
			return ErrorCode.UNKNOW_ERROR;
		
		// 背包是否够
		if( user.getHeroManager().getIsBagFull() != ErrorCode.SUCCESS )
			return ErrorCode.BAG_IS_FULL;
		
		// 体力是否够
		TollgateTemplet missionTemplet = TollgateTempletCfg.getTempletById( missionId );
		short tili = (short) (missionTemplet.getNeedStrength() * sweepCount);
		if( user.getStrength() < tili )
			return ErrorCode.ECTYPE_NOT_STRENGTH;
		
		// 看是否精英副本
		if( missionTemplet.getType() == EctypeType.ELITE ){
			// 在这里直接扣
			if( user.getEliteEctypeCountManager().update( id, missionId, sweepCount ) )
				return ErrorCode.ECTYPE_NOT_COUNT;
		}
		
		// 开始执行扣体力
		user.changeStrength( -tili, "扫荡执行扣体力" );
		
		// 刷新一下体力
		UpdateManager.instance.update( user, UpdateType.U_2 );
		
		return ErrorCode.SUCCESS;
	}
	

}

/**
 * 每一条 扫荡数据结构
 * @author DXF
 *
 */
class SweepInfo{
	public int   	cash	= 0;
	
	public int 		exp		= 0;
	
	public byte		trophy	= 0;
	
	public List<AwardInfo>	content = null;
}

