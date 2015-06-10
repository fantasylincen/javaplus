package game.award.system;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import manager.DWType;

import config.saward.SAwardType;
import config.vip.VipInfoTemplet;
import config.vip.VipInfoTempletCfg;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.DefaultCfg;

import user.UserInfo;
import util.ErrorCode;

/**
 * 奖励管理中心
 * @author DXF
 */
public class AwardManager {

	private Map< SAwardType, AwardBase > 				_nAwards;
	private UserInfo									user;
	private final AwardDataProvider 					db 		= AwardDataProvider.getInstance();
	
	public AwardManager( UserInfo user ) {
		super();
		this.user 	= user;
		_nAwards	= db.get( user );
		
		String[] list = DefaultCfg.getByCfg( "system_award", "award_id" ).split(",");
		
		// 这里添加 默认
		if( _nAwards.isEmpty() ){
			for( int i = 0; i < list.length; i++ ){
				SAwardType type 	= SAwardType.fromNumber( i );
				AwardBase temp		= new AwardBase();
				temp.m_nID			= Integer.parseInt( list[i] );
				temp.m_nCondition	= new AwardCondition();
				_nAwards.put( type, temp );
			}
			
			// 一开始是没有的
			get( SAwardType.LAMC ).record( -1 );
			// 一开始是没有的
			get( SAwardType.LAMC1 ).record( -1 );
			// 一开始是没有的
			get( SAwardType.LAMC2 ).record( -1 );
			
			db.add( user, _nAwards );
		}else{
			for( int i = 0; i < list.length; i++ ){
				SAwardType type 	= SAwardType.fromNumber( i );
				AwardBase temp 		= _nAwards.get( type );
				int id				= Integer.parseInt( list[i] );
				if( id != 0 && temp.m_nID == 0 ){
					temp.m_nID		= id;
					temp.m_nCondition= new AwardCondition();
				}
			}
		}
	}
	

	/**
	 * 获取数据列表
	 * @return
	 */
	public List<AwardBase> getList() {
		List<AwardBase> list = new ArrayList<AwardBase>();
		
		for( AwardBase base : _nAwards.values() ){
			if( base.m_nID != 0 && base.m_nCondition.getValue() != -1 ){
				list.add( base );
			}
		}
		
		return list;
	}
	
	public List<AwardBase> getList( byte type ) {
		List<AwardBase> ret = new ArrayList<AwardBase>();
		
		for( AwardBase base : _nAwards.values() ){
			if( base.m_nID != 0 && base.m_nCondition.getValue() != -1 ){
				
				if( base.isEveryday() && base.m_nID != 100001 && type == 1 ){// 每日
					ret.add( base );
				}else if( (!base.isEveryday() || base.m_nID == 100001) && type == 2 ){// 任务
					ret.add( base );
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * 通过类型 获得 奖励信息
	 * @param ci
	 */
	public AwardBase get( SAwardType type ) {
		return _nAwards.get( type );
	}
	
	/**
	 * 奖励 记录条件信息
	 * @param type(类型)
	 * @param value(条件)
	 */
	public void record( SAwardType type, int value ){
		AwardBase award = _nAwards.get( type );
		if( award == null ){
			Logs.error( user, "在记录条件信息 出错  type=" + type );
			return;
		}
		if( award.m_nID == 0 ) return;
		if( award.m_nCondition.getValue() == -1 ) return;
		
		award.record( value );
		
		// 更新奖励提示
		runReminder();
	}
	
	public void resetRecord( SAwardType type, int value ){
		AwardBase award = _nAwards.get( type );
		if( award == null ) return;
		award.record( value );
		// 更新奖励提示
		runReminder();
	}
	
	// 记录普通副本
	public void recordC( ){
		AwardBase award = _nAwards.get( SAwardType.CCE1 );
		if( award.m_nCondition.getValue() == -1 ) return;
		award.record();
//		award = _nAwards.get( SAwardType.CCE2 );
//		award.record();
//		award = _nAwards.get( SAwardType.CCE3 );
//		award.record();
//		award = _nAwards.get( SAwardType.CCE4 );
//		award.record();
	}
	
	// 记录精英副本
	public void recordE( ){
		AwardBase award = _nAwards.get( SAwardType.CEE1 );
		if( award.m_nCondition.getValue() == -1 ) return;
		award.record();
//		award = _nAwards.get( SAwardType.CEE2 );
//		award.record();
//		award = _nAwards.get( SAwardType.CEE3 );
//		award.record();
//		award = _nAwards.get( SAwardType.CEE4 );
//		award.record();
	}
	
	/**
	 * 记录邀请好友相关信息
	 * @param level
	 */
	public void recordInvting( short level ) {
		
		// 先获得剩余 的记录信息
		AwardBase award = getResidueFriend( level );
		
		if( award != null )
			award.record();
	}
	
	private AwardBase getResidueFriend( short level ) {
		
		level = (short) ((level / 10) * 10);
		
		for( int i = 10; i <= 16; i++ ){
			AwardBase award = _nAwards.get( SAwardType.fromNumber(i) );
			short compare	= (short) (i == 16 ? 100 : ((i - 10) * 10 + 30));
			if( award.m_nID != 0 && level == compare )
				return award ;
		}
		
		return null;
	}


	/**
	 * 领取奖励
	 * @param id
	 */
	public ErrorCode execute( SAwardType type ){
		AwardBase award = _nAwards.get( type );
		if( award == null ){
			Logs.error( user, "在执行发送奖励 出错  type=" + type );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		// 检查 是否满足 条件
		if( !award.isFulfil() ){
			Logs.error( user, "在执行发送奖励 玩家不满足条件 所有不发送奖励!" );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		// 获取 奖励信息
		List<AwardInfo> lists = award.getAwards();
		if( lists == null ) {
			Logs.error( user, "在执行发送奖励 奖励信息为NULL!" );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		int gold = 0;
		// 发放奖励
		for( AwardInfo a : lists ){
			
			// 这里写死 如果是每日领取水晶和金币 那么要根据VIP等级来
			if( (type == SAwardType.LAG && a.getAward() == AwardType.GOLD) || (type == SAwardType.LAM && a.getAward() == AwardType.CASH) ){
				VipInfoTemplet v = VipInfoTempletCfg.get( user.getVipLevel() );
				if( v == null ) break;
				a.setNumber( a.getAward() == AwardType.CASH ? v.getTodayCash() : v.getTodayGold() );
			}
			if( a.getAward() == AwardType.GOLD )
				gold = a.getNumber();
			user.changeAward( a, "系统发送奖励  类型：" + a.getAward(), DWType.SYSTEM_IS_PRESENTED );
		}
		
		// 记录日志
		if( type == SAwardType.LAG )
			DataLogDataProvider.getInstance().add( user, ConsumelogF.DISTRIBUTED_SYSTEM, gold );
		if( type == SAwardType.UG )
			DataLogDataProvider.getInstance().add( user, ConsumelogF.LEVEL_REWARD, gold );
		if( type == SAwardType.LAMC || type == SAwardType.LAMC1 || type == SAwardType.LAMC2 )
			DataLogDataProvider.getInstance().add( user, ConsumelogF.PREPAID_PHONE, gold );
		if( type == SAwardType.CI )
			DataLogDataProvider.getInstance().add( user, ConsumelogF.DISTRIBUTED_SYSTEM, gold );
		if( type == SAwardType.AFC || type == SAwardType.AFE )
			DataLogDataProvider.getInstance().add( user, ConsumelogF.CUSTOMS_CLEARANCE, gold );
		if( type == SAwardType.BDA )
			DataLogDataProvider.getInstance().add( user, ConsumelogF.DISTRIBUTED_SYSTEM, gold );
		
		// 领取完后 记得 切换下一个
		award.changeNext();
		
		// 跟新提示
		runReminder();
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 解除
	 * @param bda
	 */
	public void relieve( SAwardType type ) {
		AwardBase award = _nAwards.get( type );
		if( award == null ) return ;
		if( award.isFulfil() ) return ;
		award.m_nID		= 0;
	}
	
	/** 刷新数据库 */
	public void updata(){
		db.updata( user, _nAwards );
	}

	private byte reminder 	= 0;
	private byte reminder1 	= 0;
	/**
	 * 提示前端
	 * @return
	 */
	public void runReminder() {
		byte temp 			= 0;
		byte temp1 			= 0;
		for( AwardBase a : _nAwards.values() ) {
			if( a.m_nID == 100001 )
				continue;
			if( a.isFulfil() ){
				if( a.isEveryday() )
					temp	= 1;
				else
					temp1	= 1;
			}
			if( temp == 1 && temp1 == 1 )
				break;
		}
		
		if( reminder != temp ){
			reminder	= temp;
			UpdateManager.instance.update( user, UpdateType.U_21 );
		}
		if( reminder1 != temp1 ){
			reminder1	= temp1;
			UpdateManager.instance.update( user, UpdateType.U_21 );
		}
	}

	public byte reminder(){
		return reminder;
	}
	public byte reminder1(){
		return reminder1;
	}
	public void reminder(byte b) {
		this.reminder = b;
	}


	
}

