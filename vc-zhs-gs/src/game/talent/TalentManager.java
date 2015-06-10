package game.talent;

import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import manager.DWType;
import config.talent.TalentTemplet;
import config.talent.TalentTempletCfg;
import config.talent.TalentValueTemplet;
import config.talent.TalentValueTempletCfg;
import define.DefaultCfg;
import define.SystemCfg;
import user.UserInfo;
import util.ErrorCode;
import util.SystemTimer;

/**
 * 天赋 管理
 * @author DXF
 *
 */
public class TalentManager {
	
	// 点击立即完成 消耗水晶 系数
	private static final byte 							NEED_GOLD_FACTOR;
	static{
		NEED_GOLD_FACTOR = Byte.parseByte( DefaultCfg.getByCfg("talent", "need_gold_factor") );
	}
	
	private UserInfo									user;
	private final TalentDataProvider 					db 				= TalentDataProvider.getInstance();	
	
	/** 玩家天赋属性列表 */
	private ConcurrentHashMap<TalentType,TalentBase> 	talents			= new ConcurrentHashMap<TalentType, TalentBase>();
	
	
	public TalentManager( UserInfo user ) {
		super();
		this.user 			= user;
		talents				= db.get( this.user );
	}
	
	public ConcurrentHashMap<TalentType,TalentBase> getTalents(){
		filtrate();
		return this.talents;
	}
	/** 过滤一下 */
	private void filtrate(){
		for( TalentBase talent : talents.values() ){
			if( talent.getTime() != 0) if( talent.getRecordingTime() == 0 )
			{
				talent.updataLevel();
				talent.setRecordingTime( 0 );
				// 刷新数据库
				db.updata( user, talent );
			}
		}
	}
	

	/**
	 * 根据类型获得 单条数据
	 * @param type
	 * @return
	 */
	public TalentBase get( TalentType type ) {
		return talents.get( type );
	}

	/**
	 * 升级
	 * @param type
	 * @return
	 */
	public ErrorCode upgrade( TalentType type ) {
		
		// 取得当前的天赋信息
		TalentBase curTalent 	= talents.get(type);
		
		// 检测条件
		ErrorCode code 			= isDetectingCondition( curTalent );
		
		if( code == ErrorCode.SUCCESS ){
			
			upgrade( curTalent );
		}
		
		if( code == ErrorCode.TALENT_UPGRADE_TIME ){
			
			curTalent.setRecordingTime( SystemTimer.currentTimeSecond() );
			
			// 刷新数据库
			db.updata( user, curTalent );
		}
		
		return code;
	}
	private void upgrade( TalentBase curTalent ){
		
		curTalent.updataLevel();
		curTalent.setRecordingTime( 0 );
		
		// 刷新玩家英雄数据  如果可以更新那么就更新
		if( curTalent.getType().isTimelyUpdate() )
			user.getHeroManager().updataPropertyToTalent( );
		
		// 刷新数据库
		db.updata( user, curTalent );
		
		// 更新战斗力
		UpdateManager.instance.update( user, UpdateType.U_5 );
		
		// 更新提示
		runReminder();
	}
	
	/**
	 * 一键升级
	 * @param list
	 * @return
	 */
	public ErrorCode toUpgrade( List<TalentType> list ) {
		
		int needCash				= 0;
		
		for( TalentBase t : talents.values() ){
			if( t.getLevel() < user.getLevel() && t.getTime() == 0 ){
				
				// 获取下一级表格数据
				TalentValueTemplet templet 	= TalentValueTempletCfg.getTempletById( t.getLevel() + 1 );
				if( templet == null ) continue;
				
				needCash					+= templet.getNeedMoney();
				
				list.add( t.getType() );
			}
		}
		
		if( needCash == 0 )
			return ErrorCode.TALENT_NOT_UPGRADE;
		
		// 判断金币  这个放到最后  因为这一步其实已经扣除金币了
		if( user.changeAward( AwardType.CASH, -needCash, "升级天赋 金币消耗", DWType.MISCELLANEOUS ) == -1 )
			return ErrorCode.TALENT_NOT_CASH;
		
		boolean isTimely 				= false;
		for( TalentType type : list ){
			
			TalentBase t 				= get( type );
			// 获取下一级表格数据
			TalentValueTemplet templet 	= TalentValueTempletCfg.getTempletById( t.getLevel() + 1 );
				
			// 在看 是否需要时间
			if( templet.getNeedTime() != 0 ){
				t.setRecordingTime( SystemTimer.currentTimeSecond() );
			}else{
				t.updataLevel();
				t.setRecordingTime( 0 );
				// 刷新玩家英雄数据  如果可以更新那么就更新
				isTimely = t.getType().isTimelyUpdate();
			}
			
			// 刷新数据库
			db.updata( user, t );
		}
		
		if( isTimely )
			user.getHeroManager().updataPropertyToTalent( );
		
		if( !list.isEmpty() ){
			// 更新战斗力
			UpdateManager.instance.update( user, UpdateType.U_5 );
			// 刷新前端
			UpdateManager.instance.update( user, UpdateType.U_3 );
			// 更新提示
			runReminder();
		}
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 立即完成 主要是检测扣除水晶
	 * @param type
	 * @return
	 */
	public ErrorCode otsComplete(TalentType type) 
	{
		// 取得当前的天赋信息
		TalentBase curTalent 	= talents.get(type);
		
		// 检测下时间  为玩家着想  如果时间已经完了 那么就不用 扣水晶了
		if( curTalent.getRecordingTime() == 0 ){
			upgrade( curTalent );
			return ErrorCode.TALENT_TIME_COMPLETE;
		}
		
		// 算出剩余时间  
		int rTime				= curTalent.getRecordingTime();
		
		// 算出需要水晶数 = 每分钟*系数
		int needGold			= rTime / 60 * NEED_GOLD_FACTOR;
		// 只要还剩1秒 都要算一分钟
		if( rTime % 60 != 0 )
			needGold += 1;
		
		// 开始扣钱
		if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold *= DefaultCfg.DZ_CONSUMPTION_RATIO;
		if( user.changeAward( AwardType.GOLD, -needGold, "天赋加速消耗", DWType.TALENT_TO_ACCELERATE ) == -1 )
			return ErrorCode.TALENT_NOT_GOLD;
		
		upgrade( curTalent );
		
		UpdateManager.instance.update( user, UpdateType.U_4 );
		
		return ErrorCode.SUCCESS;
	}
	
	
	/**
	 * 时间轴跑完
	 * @param type
	 * @return
	 */
	public ErrorCode timerShaft( TalentType type ) 
	{
		// 取得当前的天赋信息
		TalentBase curTalent 	= talents.get(type);
		
		// 看是不是 提前申请的
		if( curTalent.getRecordingTime() - 1 > 0 )
			return ErrorCode.TALENT_DELAY;
		
		// 这里看是不是 已经升级了  如果已经升级了 那么就不升了
		if( curTalent.getTime() == 0 )
			return ErrorCode.SUCCESS;
			
		upgrade( curTalent );
		
		return ErrorCode.SUCCESS;
	}

	
	/**
	 * 根据天赋信息 获取表格数据来检测 是否合法
	 * @param curTalent
	 * @return
	 */
	private ErrorCode isDetectingCondition( TalentBase curTalent ) {
		
		// 先看时间  如果正在升级 那么就不能升级
		if( curTalent.getRecordingTime() != 0 )
			return ErrorCode.UNKNOW_ERROR;
		
		// 判断等级
		if( curTalent.getLevel() >= user.getLevel() )
			return ErrorCode.TALENT_NOT_LEVEL;
		
		// 获取下一级表格数据
		TalentValueTemplet templet 	= TalentValueTempletCfg.getTempletById( curTalent.getLevel() + 1 );
				
		if( templet == null )
			return ErrorCode.UNKNOW_ERROR;
			
		// 判断金币  这个放到最后  因为这一步其实已经扣除金币了
		if( user.changeAward( AwardType.CASH, -templet.getNeedMoney(), "升级天赋 金币消耗", DWType.MISCELLANEOUS ) == -1 )
			return ErrorCode.TALENT_NOT_CASH;

		// 在看 是否需要时间
		if( templet.getNeedTime() != 0 )
			return ErrorCode.TALENT_UPGRADE_TIME;
		
		// 刷新前端
		UpdateManager.instance.update( user, UpdateType.U_3 );
		
		return ErrorCode.SUCCESS;
	}
	

	/**
	 *  添加默认属性给他  只有在创建角色的时候 调用
	 */
	public void addDefault() {
		
		if( talents.values().size() != 0 )
			return;
		
		for( TalentTemplet templet : TalentTempletCfg.getData().values() ){
			
			TalentType type = TalentType.fromNumber( templet.getId() );
			
			TalentBase talentBase = new TalentBase( type, (short)1 );
			
			talents.putIfAbsent( talentBase.getType(), talentBase );
		}
		
		db.add( user , talents );
	}

	private byte reminder 	= 0;
	/**
	 * 提示前端
	 * @return
	 */
	public void runReminder() {
		byte temp 	= 0;
		for( TalentBase t : talents.values() ){
			
			if( t.getLevel() < user.getLevel()  ){
				
				short bad = (short) (user.getLevel() - t.getLevel());
				
				if( bad == 1 && t.getTime() != 0 )
					continue;
				
				temp 	= 1;
				break;
			}
		}
		
		if( reminder != temp ){
			reminder = temp;
			UpdateManager.instance.update( user, UpdateType.U_20 );
		}
	}
	public byte reminder() {
		return reminder;
	}
	public void reminder( byte reminder ){
		this.reminder = reminder;
	}

}
