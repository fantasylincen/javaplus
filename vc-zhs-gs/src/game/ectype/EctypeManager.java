package game.ectype;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import manager.DWType;

import config.activity.ActivityTempletCfg;
import config.mission.EctypeType;
import config.mission.InstanceTemplet;
import config.mission.InstanceTempletCfg;
import config.mission.TollgateTemplet;
import config.mission.TollgateTempletCfg;
import config.saward.SAwardType;

import game.activity.ActivityBase;
import game.activity.ActivityManager;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;
import user.UserInfo;
import util.ErrorCode;

/**
 * 玩家副本存储管理
 * @author DXF
 */
public class EctypeManager {
	
	private ConcurrentHashMap<EctypeType,EctypeBase>   	ectypes 	= new ConcurrentHashMap<EctypeType, EctypeBase>();
	private UserInfo									user;
	private final EctypeDataProvider 					db = EctypeDataProvider.getInstance();
	
	public EctypeManager( UserInfo user ) {
		super();
		this.user 	= user;
		ectypes 	= db.get( user );
		
		// 如果还没有副本记录  就默认添加第一个    
		if( ectypes.isEmpty() )
		{
			addFirstEctype( EctypeType.COMMON );
			addFirstEctype( EctypeType.ELITE );
			addFirstEctype( EctypeType.CHALLENGE );
			addFirstEctype( EctypeType.ACTIVITY );
			
			if ( db.add( ectypes, user ) != ErrorCode.SUCCESS ){
				Logs.error( user, "(添加默认副本ID错误) ");
			}
		}

	}
	

	/**
	 * 获取最后一个关卡ID
	 * @param eType  
	 * @return
	 */
	public int getLastPid( EctypeType eType ) {
		return ectypes.get( eType ).getLastPid();
	}
	
	/**
	 * 获得 副本存储信息
	 * @return
	 */
	public Map<EctypeType,EctypeBase> getEctypeInfo() {
		return ectypes;
	}
	
	/**
	 * 根据类型获取 副本
	 * @param et
	 * @return
	 */
	public EctypeBase getEctype( EctypeType et ){
		return ectypes.get(et);
	}
	/**
	 * 根据副本ID 
	 * @param mid
	 * @return
	 */
	public EctypeBase getEctype( short mid ) {
		
		InstanceTemplet instance = InstanceTempletCfg.getTempletById( mid );
		
		return instance != null ? getEctype( instance.getType() ) : null;
	}
	
	/**
	 * 当前副本被通关 通过后要解锁后续副本
	 * @param missionId 
	 * @throws IOException 
	 */
	public void throughEctype( short id, int missionId )
	{
		InstanceTemplet instance = InstanceTempletCfg.getTempletById( id );
		TollgateTemplet tollgate = TollgateTempletCfg.getTempletById( missionId );
		
		// 这里做 副本通过奖励
		user.changeAward( AwardType.CASH, tollgate.getAwardGold(), "副本通过后 获得金币", DWType.ATLASLOOT );
		user.changeAward( AwardType.EXP, tollgate.getAwardPlayerExp(), "副本通过后 获得召唤师经验", DWType.ATLASLOOT );
//		user.changeAward( AwardType.HERO_EXP, tollgate.getAwardHeroExp(), "副本通过后 获得英雄经验", DWType.ATLASLOOT );
		
		// 更新前端金币
		UpdateManager.instance.update( user, UpdateType.U_3 );
		
		// 最后看下 是否 英雄副本  如果是 那么就要减掉当前次数
		if( instance.getType() == EctypeType.ELITE )
			user.getEliteEctypeCountManager().update( id, missionId );
		
		// 如果是活动那么下面就不结算了 只是记录一下 就行了
		if( instance.getType() == EctypeType.ACTIVITY ){
			
			// 先根据 副本ID  获取 活动ID
			short nId 			= ActivityTempletCfg.getMissionToId( id );
			if( nId == 0 ) return;
			// 然后根据活动ID 获取对应信息
			int index 			= instance.indexOf(missionId);
			
			// 这里写死 必须是小龙才 刷新
			if( (nId / 100) == 1 )
				user.getEliteEctypeCountManager().update( (int)nId, index );
			
			// 这里是特殊活动副本才 
			if( (nId / 100) == 4 )
				--user.christmasTimes;
			
			return;
		}
		
		// 然后下面做 通过记录 
		EctypeBase ectype 		= ectypes.get( instance.getType() );
		
		// 如果 玩家挑战滴 前面已经通关滴副本  那么就不解锁副本
		if( id < ectype.getLastId() ) return;
		if( missionId < ectype.getLastPid() ) return;
		
		// 先找出 关卡位置
		int i					= instance.indexOf( missionId );
		if( i == -1 ) {
			Logs.error( user, "关卡通关后 出现错误 当前副本=" + id + " 没有" + missionId + "这个关卡ID" );
			return;
		}
		
		// 说明是这个副本的最后一个关卡  那么就开启新的副本
		if( ++i >= instance.getTollgate().size() ){
			
			// 所有副本ID 都是顺序排列的 
			InstanceTemplet newInstance 	= InstanceTempletCfg.getNext( id );
			// 如果没有 或者类型不一样   那说明说有副本都打完了 直接返回
			if( newInstance == null ) return;
			if( newInstance.getType() != instance.getType() ) return;
			// 取第一个 关卡ID
			int mid							= newInstance.getTollgate().get(0);
			
			ectype.addFeeder( newInstance.getId(), mid );
			
			// 记录英雄副本次数
			user.getEliteEctypeCountManager().add( newInstance.getId(), mid );
			
			// 通过后  这里记录 系统奖励
			switch( instance.getType() ){
			case COMMON:
				user.getAwardManager().record( SAwardType.AFC, id );
				break;
			case ELITE:
				user.getAwardManager().record( SAwardType.AFE, id );
				break;
			default:
				break;
			}
			
		}else{// 找出下一个关卡  然后直接覆盖 
			
			int nextId = instance.getTollgate().get( i );
			ectype.setLastTollgateid( nextId );
			
			// 记录英雄副本次数
			user.getEliteEctypeCountManager().add( id, nextId );
		}
		
		// 通过后 把这个ID 记录到数据库  这里不用考虑ID是否合格 因为前面一定判断了
		db.update( instance.getType(), ectype, user );
	}
	
	/**
	 *  判断是否能进入 副本 
	 *  根据当前记录滴主线和支线 来做比较
	 * @param id
	 * @param missionId 
	 * @return
	 */
	public ErrorCode isCanEnter( short id, int missionId )
	{
		InstanceTemplet templet = InstanceTempletCfg.getTempletById( id );
		ActivityManager activityManager = ActivityManager.getInstance();
		
		// 先换算一下体力
		user.getStrReplyTimeToSecond( 1 );
		
		// 如果是精英副本
		if( templet.getType() == EctypeType.ELITE ){
			// 获取普通副本记录列表
			EctypeBase common 	= ectypes.get( EctypeType.COMMON );
			// 判断 这个副本是否通过
			if( !templet.isEliteHaveThrough( common.getLastId() ) )
				return ErrorCode.ECTYPE_NOT_THROUGH;
			// 然后判断 次数 是否足够
			if( !user.getEliteEctypeCountManager().checkCount( id, missionId ) )
				return ErrorCode.ECTYPE_NOT_COUNT;
		}
		
		// 如果是活动副本  现在 在这出现的 暂时可能就只有小龙了 
		if( templet.getType() == EctypeType.ACTIVITY ){
			// 先根据 副本ID  获取 活动ID
			short nId = ActivityTempletCfg.getMissionToId( id );
			if( nId == 0 ) return ErrorCode.UNKNOW_ERROR;
			// 然后根据活动ID 获取对应信息
			ActivityBase abase 	= activityManager.get(nId);
			int index 			= templet.indexOf(missionId);
			if( !abase.isOpen() )
				return ErrorCode.ACTIVITY_NOT_OPEN;
			
			// 这里写死  是小龙才检查
			if( !user.getEliteEctypeCountManager().checkDragonetCount( nId, index ) )
				return ErrorCode.ACTIVITY_ECTYPE_NOT_COUNT;
			
			// 在看等级
//			if( user.getLevel() < templet.getNeedLv() )
//				return ErrorCode.ECTYPE_NOT_LEVEL;
			
			// 看体力
			TollgateTemplet toll = TollgateTempletCfg.getTempletById( missionId );
			if( user.getStrength() < toll.getNeedStrength() )
				return ErrorCode.ECTYPE_NOT_STRENGTH;
			
			// 大龙
			if( nId/100 == 2 ){
//				if( ActivityManager.getInstance().getDragon().getCount( user.getUID() ) >= 1 )
//					return ErrorCode.ACTIVITY_ECTYPE_NOT_COUNT;
				if( activityManager.getDragon().isDie() )
					return ErrorCode.CHALLENGE_THE_DRAGON_DIE;
				
				// 大龙单独处理  这里只有第一次参加才扣体力
				if( !activityManager.getDragon( ).enterCombat( user ) ) {
					user.changeStrength( -toll.getNeedStrength(), "大龙副本 消耗" );
					// 体力扣了就开始 算参与
					activityManager.update();
					UpdateManager.instance.update( user, UpdateType.U_2 );
				}
			}
			
			// 特殊活动副本
			if( nId/100 == 4 ){
				if( user.christmasTimes <= 0 ){
					Logs.error( user, "挑战特殊活动副本次数不够" );
					return ErrorCode.ACTIVITY_ECTYPE_NOT_COUNT;
				}
			}
			
			// 活动都是固定的 所以直接返回
			return ErrorCode.SUCCESS;
		}
		
		EctypeBase ectype 		= ectypes.get( templet.getType() );
		
		// 先看副本ID
		if( ectype.getLastId() < id )
			return ErrorCode.ECTYPE_NOT_THROUGH;
		
		// 看关卡ID
		if( missionId > ectype.getLastPid() )
			return ErrorCode.ECTYPE_NOT_POINTS;
		
		// 在看等级
//		if( user.getLevel() < templet.getNeedLv() )
//			return ErrorCode.ECTYPE_NOT_LEVEL;
		
		// 看体力
		TollgateTemplet toll = TollgateTempletCfg.getTempletById( missionId );
		if( user.getStrength() < toll.getNeedStrength() )
			return ErrorCode.ECTYPE_NOT_STRENGTH;
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 是否第一次进入这个关卡
	 * @param missionId
	 * @return
	 */
	public boolean isOneEnter( short id, int missionId ) {
		
		InstanceTemplet templet = InstanceTempletCfg.getTempletById( id );
		
		EctypeBase ectype 		= ectypes.get( templet.getType() );
		
		return ectype.getLastPid() == missionId;
	}
	
	
	/** 添加第一个副本关卡ID 
	 * @param ectypeType  */
	private void addFirstEctype( EctypeType ectypeType )
	{
		short id[] 	= InstanceTempletCfg.FIRST_POINTS_ID;
		
		short eid 	= id[ ectypeType.toNumber() - 1 ];
		
		InstanceTemplet templet = InstanceTempletCfg.getTempletById( eid );
		
		EctypeBase value 		= new EctypeBase();
		
		if( templet != null ) {
			value.addFeeder( templet.getId(), templet.getTollgate().get(0) );
			// 这里如果是英雄副本 那么要记录他的次数
			if( ectypeType == EctypeType.ELITE ){
				user.getEliteEctypeCountManager().add( templet.getId(), templet.getTollgate().get(0) );
			}
		}
		
		ectypes.putIfAbsent( ectypeType, value);
	}

	
}
