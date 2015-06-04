package game.growup.captain;

import java.util.ArrayList;
import java.util.List;

import lua.Lua;
import lua.LuaProxy;
import manager.DWType;

import config.fighter.Professional;
import config.skill.captain.CaptainSkillTemplet;
import config.skill.captain.CaptainSkillTempletCfg;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.DefaultCfg;
import game.activity.ActivityManager;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.log.Logs;
import user.UserInfo;
import util.ErrorCode;
import util.RandomUtil;
import util.SystemTimer;

/**
 * 队长技能管理
 * @author DXF
 *
 */
public class CaptainSkillManager {

	// 所有职业个数 包括全部  主要用来随机用的
	private static final byte							PRO_NUM = 10;
	private UserInfo									user;
	private final CaptainSkillDataProvider 				db = CaptainSkillDataProvider.getInstance();
	
	private CaptaninSkillBase							captanin;
	
	// 
	public CaptainSkillManager( UserInfo user ){
		super();
		this.user 		= user;
		captanin		= db.get( user );
		if( captanin == null ){
			captanin 	= new CaptaninSkillBase();
			captanin.setRecordTime( SystemTimer.currentTimeSecond() );
			db.add( user, captanin );
		}
		
	}

	public int getID() {
		return captanin.getNID();
	}

	/** 重置 可学习队长技能 */
	public ErrorCode reset() {
		/**
		 * 规则：
		 * 8个针对性职业的随机几率为11%（目标职业中的众多技能随机抽取一个），全职业的为12%，合计100%。
		 */
		
		int rand 								= RandomUtil.getRandomInt( 0, 99 );
		int i									= 0;
		for( ; i < PRO_NUM; i++ ){
			Professional pro	= Professional.fromNumber(i);
			rand				-= pro.getCaptainSkillOdds();
			if( rand < 0 ) break;
		}
		if( i >= PRO_NUM ) i = PRO_NUM - 1;
		
		List<List<CaptainSkillTemplet>> list 	= CaptainSkillTempletCfg.getByList( (byte)i );
		if( list == null ){
			Logs.error( user, "重置学习队长技能出错  list == null i=" + i );
			return ErrorCode.UNKNOW_ERROR ;
		}
		
		// 这里随便随机 
		rand									= RandomUtil.getRandomInt( 0, list.size() - 1 );
		List<CaptainSkillTemplet> captainList	= list.get( rand );
		
		// 技能随机	初级：10%，  中级：40%，  高级： 30%，  大师级：15%，  宗师级：5%
		int odds[]								= { 10, 40, 30, 15, 5 };
		if( ActivityManager.getInstance().isRestoreIsOpen() ){
			odds[0] = 0;
			odds[1] = 0;
		}
		
		if( captainList.size() != odds.length ){
			Logs.error( user, "重置学习队长技能出错  技能列表大小问题  captainList.size() != odds.length！" );
			return ErrorCode.UNKNOW_ERROR ;
		}
		
		int maxRand								= 0;
		for( i = 0; i < odds.length; i++ )
			maxRand								+= odds[i];
		
		rand									= RandomUtil.getRandomInt( 0, maxRand );
		for( i = 0; i < odds.length; i++ ){
			rand								-= odds[i];
			if( rand < 0 ) break;
		}
		if( i >= captainList.size() ) i			= captainList.size() - 1;
		
		CaptainSkillTemplet templet 			= captainList.get( i );
		
		// 获得技能ID
		int skillID								= templet == null ? 0 : templet.getId();
		
		// 判断 是否可以免费
		if( !captanin.isUse() ){
//			int needGold = 30;
//			int needGold = DefaultCfg.DEVOUR_SKILL_GOLD * ( captanin.getUseTime() - 1 );
//			if( needGold > 100 ) needGold = 100;
			LuaProxy lua 	= Lua.createLuaState( "gameData.lua" );
			int needGold	= lua.retInteger( "getUpdateCaptainSkillsNeedCrystal" , captanin.getUseTime() );
			lua.close();
			
			if( user.changeAward( AwardType.GOLD, -needGold, "队长技能消耗",DWType.CAPTAIN_SKILLS ) == -1 )
				return ErrorCode.USER_GOLD_NOT_ENOUTH;
			UpdateManager.instance.update( user, UpdateType.U_4 );
			DataLogDataProvider.getInstance().add( user, ConsumelogF.REFRESH_CAPTAINSKILLS, DefaultCfg.DEVOUR_SKILL_GOLD );
		}
		
		// 累加刷新次数
		captanin.use();
		// 最后设置技能ID
		captanin.setNID( skillID );
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 学习队长技能
	 * @param hero
	 * @return
	 */
	public ErrorCode learn( Hero hero ) {
		
		if( captanin.getNID() == 0 ){
			Logs.error( user, "学习队长技能出错  没有重置技能!" );
			return ErrorCode.HEROGROWUP_NOT_RESET_CSKILL;
		}
		
		user.getHeroManager().learnCaptainSkill( hero.getUID(), captanin.getNID() );
		
		// 更新前端
		List<Hero> list = new ArrayList<Hero>();
		list.add( user.getHeroManager().getHero( hero.getUID() ) );
		UpdateManager.instance.update( user, UpdateType.U_102, list );
		
		// 学习后 清楚掉 可学习技能
		captanin.setNID( 0 );
		
		return ErrorCode.SUCCESS;
	}
	
	/** 刷新数据库 */
	public void updata(){
		db.updata( user, captanin );
	}

	public byte getCount() {
		return captanin.getUseTime();
	}
	public void init() {
		captanin.init();
	}
	
}
