package game.util.fighting;

import game.equipment.EquipmentInfo;
import game.fighter.Hero;
import game.hero.HeroManager;
import game.pvp.MatchingType;
import game.skill.AccordSkill;
import game.skill.CaptainSkill;
import game.talent.TalentBase;
import game.talent.TalentType;
import game.team.TeamHero;
import game.util.heroGrowup.HeroGrowupFormula;

import java.util.List;
import java.util.Map;

import user.UserInfo;

/**
 * 战斗力 公式
 * @author DXF
 *
 */
public class FightingFormula {
	
	// 天赋系数
	private static final int		TALENT_FACTOR 	= 35;
	
	// 主动技能系数
	private static final int		SKILL_FACTOR 	= 275;
	
	// 队长技能系数
	private static final int		C_SKILL_FACTOR 	= 175;

	// 装备 系数
	private static final int		EQUIP_FACTOR	= 2;
	
	/**
	 * 品质系数
	 * @return
	 */
	public static final Modulus[] qualityFactor = {
		
	//--------------品质系数----------达到品质加成----------------
		
		new Modulus(  85 		, 		0 		),	// 绿
		new Modulus(  115 		, 		100 	),	// 绿+
		new Modulus(  145 		, 		200 	),	// 蓝
		new Modulus(  175 		, 		300 	),	// 蓝+
		new Modulus(  205 		, 		400 	),	// 蓝+1
		new Modulus(  235 		, 		500 	),	// 蓝+2
		new Modulus(  265 		, 		600 	),	// 蓝+3
		new Modulus(  295 		, 		700 	),	// 蓝+4
		new Modulus(  325 		, 		800 	),	// 蓝+5
		new Modulus(  295 		, 		700 	),	// 紫
		new Modulus(  325 		, 		800 	),	// 紫+
		new Modulus(  355 		, 		900 	),	// 紫+1
		new Modulus(  385 		, 		1000 	),	// 紫+2
		new Modulus(  415 		, 		1200 	),	// 紫+3
		
							};
	
	
	
	/**
	 * 开始执行战斗力计算
	 * @param user
	 * @return
	 */
	public static int run( UserInfo user ){
		
		List<TeamHero> lists = user.getTeamManager().getTeam( );
		if( lists == null || lists.isEmpty() ) 
			return 0;
		
		// 先算出天赋的值
		int value 	= talentFormula( user.getTalentManager().getTalents() );
		
		// 算出英雄品质的值
		value		+= heroQualityFormula( user, lists );
		
		return value;
	}
	
	/**
	 * 开始执行战斗力计算
	 * @param user
	 * @return
	 */
	public static int run( UserInfo user, MatchingType type ){
		
		List<TeamHero> lists = user.getTeamManager().getTeam( type );
		if( lists == null || lists.isEmpty() ) 
			return 0;
		
		// 先算出天赋的值
		int value 	= talentFormula( user.getTalentManager().getTalents() );
		
		// 算出英雄品质的值
		value		+= heroQualityFormula( user, lists );
		
		return value;
	}
	

	public static int run( UserInfo user, List<Hero> lists ) {
		
		if( lists.isEmpty() )
			return 0;
		
		// 先算出天赋的值
		int value 	= talentFormula( user.getTalentManager().getTalents() );
		
		for( Hero h : lists )
			value += formula( h );
		
		return value;
	}
	
	public static int run1( UserInfo user, List<TeamHero> lists ){
		
		if( lists.isEmpty() )
			return 0;
		
		// 先算出天赋的值
		int value = talentFormula( user.getTalentManager().getTalents() );
		
		value += heroQualityFormula( user, lists );
		
		return value;
	}
	
	/**
	 * 计算天赋 战斗力
	 * 公式：天赋等级*天赋系数
	 * @param talents
	 * @return
	 */
	private static int talentFormula( Map<TalentType,TalentBase> talents ){
		int value = 0;
		
		if( talents == null )
			return 0;
		
		for( TalentBase talent : talents.values() ){
			value += talent.getLevel() * TALENT_FACTOR;
		}
		
		return value;
	}
	
	/**
	 * 计算英雄品质 
	 * 公式：有点复杂
	 * @param user
	 * @return
	 */
	private static int heroQualityFormula( UserInfo user , List<TeamHero> lists ) {
		int result = 0;
		
		HeroManager heroManager = user.getHeroManager();
		
		for( TeamHero teamHero : lists ){
			if( teamHero == null ) continue;
			
			Hero hero 			= heroManager.getHero( teamHero.getUId() );
			result 				+= formula( hero );
		}
	
		return result;
	}

	// 公式 
	public static int formula( Hero hero ){
		
		if( hero == null )
			return 0;
		
		int result 			= 0;
		
		// 根据品质获取 实际数
		byte actualNum		= HeroGrowupFormula.actualNumberFormula( hero.getQuality() ) ;
		
		// 根据品质获取 数据链
		byte[] link 		= HeroGrowupFormula.dataLink( hero.getQuality() );
		
		short level			= hero.getLevel();
		
		for( byte idx = 0; idx < link.length; idx++ ){
			byte i				= link[idx];
			
			// 先来就把  达到品质加成  加上
			result				+= qualityFactor[i].achieveQuality;
			
			short changeLevel 	= level > HeroGrowupFormula.levelLimit[i][1] ? HeroGrowupFormula.levelLimit[i][1] : level;
			short change		= (short)(changeLevel - HeroGrowupFormula.levelLimit[i][0]);
			change				= change < 0 ? 0 : change;
			
			result				+= qualityFactor[i].factor * change;
			
			if( changeLevel == level && actualNum == i ) break;
		}
		
		// 主动技能 结算
		AccordSkill	accordSkill		= hero.getSkillAttack();
		result 	+= accordSkill.getLevel() * SKILL_FACTOR;
		
		// 队长技能 结算
		CaptainSkill captainSkill	= hero.getCaptainSkill();
		result 	+= captainSkill.getLevel() * C_SKILL_FACTOR;
		
		// 装备 结算
		if( !hero.getEquBar().isEmpty() )
		{
			List<EquipmentInfo> list = hero.getEquBar().get();
			int temp = 0;
			for( EquipmentInfo e : list ){
				int[] x = e.getValue();
				for( int i = 0; i < x.length; i++ )
					temp += x[i];
			}
			result += temp * EQUIP_FACTOR;
		}
		
		return result;
	}
	
	public static int formula( List<Hero> list ) {
		
		if( list == null || list.isEmpty() )
			return 0;
		
		int result 	= 0;
		
		for( Hero h : list )
			result	+= formula( h );
		
		return result;
	}
	
	/**
	 * 判断两个 玩家 匹配的时候战斗力
	 * @param fighting_1
	 * @param fighting_2
	 * @return
	 */
	public static boolean toDetermineStrength( float fighting_1, float fighting_2, boolean isRound ) {
		
		float f	= (1f - ( fighting_2 / fighting_1 )) * 100f;
		
		if( (f > 0 && isRound) || (f < 0) && !isRound ) 
			return false;
		
		f		= Math.abs( f );
		
		return f <= 500;
	}

	
}


/**
 * 品质系数
 * @author DXF
 */
class Modulus{
	
	/** 品质系数 */
	public int factor;
	
	/** 提升到当前品质的实力加成 */
	public int achieveQuality;
	
	public Modulus( int factor, int achieveQuality ){
		this.factor 		= factor;
		this.achieveQuality = achieveQuality;
	}
}