package game.growup;

import game.fighter.Hero;
import game.util.heroGrowup.HeroGrowupFormula;

import java.util.List;

import config.skill.accord.SkillTemplet;

import define.DefaultCfg;

import util.ErrorCode;


/**
 * 英雄升级 管理中心
 * @author DXF
 *
 */
public class HeroUpgrade {

	// 主英雄
	private Hero 				lordHero;
	// 被吞噬英雄ID
	
	private List<Hero> 			devourHero;
	
	
	public HeroUpgrade( Hero lordID, List<Hero> devour ){
		this.lordHero 		= lordID;
		this.devourHero		= devour;
	}
	
	/**
	 * 选择吞噬英雄后  获得结果
	 */
	public UpgradeInfo getDevourResult(){
		UpgradeInfo info = new UpgradeInfo();
		info.code( detection() );
		
		float exp 		= 0;
		
		if( info.code() == ErrorCode.SUCCESS ){
			for( Hero hero : devourHero ){
				exp		+= hero.getExtraValue( 1 );
			}
		}
		
		Hero conle = new Hero( lordHero );
		conle.addExp( (int)exp );
		
		info.getLevel( conle.getLevel() );
		info.getAttack( conle.getAttack() );
		info.getHp( conle.getHpMax() );
		info.getExp( (int)exp );
//		info.needGold( exp/2 );
		info.needGold( exp*1.5f );
		info.skillOdds( getSkillOdds() );
		
		return info;
	}
	
	// 算出技能几率
	private byte getSkillOdds() {
		
		if( lordHero.getSkillAttack().getSkill() == null )
			return 0;
		
		int odds 		= 0;
		
		int lskillID 	= lordHero.getSkillAttack().getSkill().getId();
		
		for( Hero hero : devourHero ){
			
			SkillTemplet skill = hero.getSkillAttack().getSkill();
			if( skill == null ) continue;
			
			if( lskillID == skill.getId() ){
				
				odds += HeroGrowupFormula.getSkillOdds( lordHero, hero.getQuality() );
				if( odds >= 100 ) {
					odds = 100;
					break;
				}
			}
		}
		
		return (byte) odds;
	}

	// 检测 是否合法
	private ErrorCode detection()
	{
		if( devourHero == null || devourHero.isEmpty() || devourHero.size() > DefaultCfg.MAX_DEVOUR_NUM )
			return ErrorCode.HEROGROWUP_NOT_DEVOUR;
		
		
		if( lordHero.isFullLevel() && lordHero.getSkillAttack().isFullLevel() )
			return ErrorCode.HEROGROWUP_FULL_LEVEL;
			
		return ErrorCode.SUCCESS;
	}

	
}
