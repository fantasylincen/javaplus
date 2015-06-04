package game.battle.auto;


import game.battle.IBattleUtil;
import game.fighter.FighterBase;
import game.pvp.DanGrad;
import game.skill.SkillBase;
import game.talent.TalentBase;
import game.talent.TalentType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import config.critofjob.CritOfJobTempletCfg;
import config.dangrading.LevelPromoteRuleTempletCfg;
import config.skill.PassiveSkillTemplet;
import config.skill.captain.EffectType;

import util.RandomUtil;

public class AutoBattleUtil implements IBattleUtil {

	private static final IBattleUtil 	INSTANCE = new AutoBattleUtil();
	private AutoBattleUtil() {}
	public static final IBattleUtil getInstance(){
		return INSTANCE;
	}
	
	/**
	 * 出手顺序由位置确定
	 */
	private static final Comparator<FighterBase> speedComparator = new Comparator<FighterBase>(){
		@Override
		public int compare( FighterBase f1, FighterBase f2 ) {
			return f1.getPosition() - f2.getPosition();
		}
	};
	
	/**
	 * 判断攻击者是否命中<br>
	 * （受击方闪避绝对值/10000 + 受击方自身闪避几率 + 受击方队长技能闪避几率） - （攻击方命中绝对值/10000 + 攻击方队长技能命中几率）
	 * @param attacker			攻击者
	 * @param defender			防御者
	 * @param defendTalents 
	 * @param attackTalents 
	 * @param dCaptain 
	 * @param aCaptain 
	 * @return
	 * 			true		命中
	 * 			false		未命中
	 * 
	 */
	private boolean isHit( FighterBase attacker, FighterBase defender, 
			Map<TalentType, TalentBase> attackTalents, Map<TalentType, TalentBase> defendTalents, List<FighterBase> aCaptain, List<FighterBase> dCaptain ) 
	{
		// 受击方闪避绝对值
		float defDodge 		= getTalentValue( defendTalents, TalentType.DODGE ) + defender.getDodgeAbs();
		// 攻击方命中绝对值
		float attHit		= getTalentValue( attackTalents, TalentType.HIT ) + attacker.getHitAbs();
		// 受击方自身闪避几率 + 受击方队长技能闪避几率
		float selfDodge		= defender.getDodge();
		// 攻击方队长技能命中几率
		float attHitodds	= getCaptain( attacker, aCaptain, EffectType.HIT );
		
		// 算出闪避值 （受击方闪避绝对值/10000 + 受击方自身闪避几率 + 受击方队长技能闪避几率） - （攻击方命中绝对值/10000 + 攻击方队长技能命中几率）
		int	dodge		= (int)(( defDodge/1000 + selfDodge ) - ( attHit/1000 + attHitodds )) * 100;
		dodge			= dodge < 0 ? 10 : dodge;
		
		int r 			= RandomUtil.getRandomInt( 0, 10000 );//随机值
		
		return r > dodge;
	}
	
	/**
	 *	是否暴击
	 *  公式	:
	 *  （攻击方暴击绝对值/10000 + 攻击方自身暴击几率 + 攻击方队长技能暴击几率） - （受击方韧性绝绝对值/10000 + 受击方队长技能韧性）
	 * @param attacker
	 * @param defender
	 * @param dCaptain 
	 * @param aCaptain 
	 * @return 
	 */
	private boolean calcCrit( FighterBase attacker, FighterBase defender, boolean canSkill, 
			Map<TalentType, TalentBase> attackTalents, Map<TalentType, TalentBase> defendTalents,
			List<FighterBase> aCaptain, List<FighterBase> dCaptain, DanGrad danGrad ) 
	{
		
		// 攻击方暴击绝对值
		float attackCrit		= getTalentValue( attackTalents, TalentType.CRIT ) + attacker.getCritAbs();
		// 攻击方自身暴击几率 + 攻击方队长技能暴击几率
		float selfCrit			= canSkill ? attacker.getSkillCrit() : attacker.getCommonCrit();
		// 受击方韧性绝绝对值
		float defDuctility 		= getTalentValue( defendTalents, TalentType.TOUGHNESS ) + defender.getTenacityAbs();
		// 受击方队长技能韧性
		float defDuctilityOdds	= getCaptain( defender, dCaptain, EffectType.TOUGHNESS );
		// 加上段位的韧性
		if( danGrad != null )
			defDuctilityOdds	+= LevelPromoteRuleTempletCfg.get( danGrad ).getBoonTenacity();
		
		// 算出暴击值
		int crit				= (int) (( attackCrit/100f + selfCrit ) - ( defDuctility/100f + defDuctilityOdds ));
		crit					= (crit < 0 ? 10 : crit) * 100;
		
		int r 					= RandomUtil.getRandomInt( 0, 10000 );//随机值
		
		return r < crit;
//		return r < 10000;
	}

	/**
	 * 根据公式计算普通攻击的伤害值
	 * @param attacker
	 * @param defender
	 * @param formula	计算公式
	 * @param arguments	相应参数，如不存在，请放入null
	 * @return
	 */
	@Override
	public AttackInfo calcAttackInfo(FighterBase attacker, FighterBase defender,
			Map<TalentType, TalentBase> _attackTalents, Map<TalentType, TalentBase> _defendTalents,
			List<FighterBase> _aCaptain, List<FighterBase> _dCaptain, 
			DanGrad _attackDanGrad, DanGrad _defendDanGrad, SkillBase skill )
	{
		
		//当前攻击方 天赋和队长信息
		Map<TalentType, TalentBase> attackTalents 	= attacker.getIsBottom() ? _attackTalents : _defendTalents;
		List<FighterBase> aCaptain					= attacker.getIsBottom() ? _aCaptain : _dCaptain;
//		DanGrad attackDanGrad						= attacker.getIsBottom() ? _attackDanGrad : _defendDanGrad;
		
		//当前防守方 天赋和队长信息
		Map<TalentType, TalentBase> defendTalents	= defender.getIsBottom() ? _attackTalents : _defendTalents;
		List<FighterBase> dCaptain					= defender.getIsBottom() ? _aCaptain : _dCaptain;
		DanGrad defendDanGrad						= defender.getIsBottom() ? _attackDanGrad : _defendDanGrad;
		
		
		AttackInfo info = new AttackInfo();
		
		// 先计算命中   这里要是针对敌人才 结算命中
		boolean isHit 	= skill.getRival() == 0 ? isHit(attacker, defender, attackTalents, defendTalents, aCaptain, dCaptain) : true;
		if( !isHit ){
			return info;
		}
		
		// 根据技能获取攻击力
		float damage 	= skill.run( attacker, defender );
		
		// 攻防结算
		damage			= attackAndDefenseSettlement( damage, attackTalents, defendTalents, aCaptain, dCaptain, attacker, defender );	
		
		// 如果命中 那么就计算暴击
		boolean crit  	= calcCrit(attacker, defender, skill.getIsCan(), attackTalents, defendTalents, aCaptain, dCaptain, defendDanGrad );
		if( crit )
			damage 		*= CritOfJobTempletCfg.get( attacker.getProfessional() , skill.getChoose() );
		
		// 这里做 被动技能的
		PassiveSkillTemplet passivs = defender.getPassiveSkill(); 
		damage 			= passivs != null ? passivs.run( damage, attacker.getAttackType() ) : damage;
		
		info.setCrit( crit?1:0 );
		info.setHit( isHit );
		info.setDamage( (int)damage );
		
		return info;
	}

	/**
	 * 攻防结算
	 * 公式：
	 * 攻击方物理（法术）攻击 * （1 +（（攻击方物理（法术）穿透绝对值 - 受击方物理（法术）抗性绝对值）/10000） + （攻击方物理（法术）穿透百分比 - 受击方物理（法术）抗性百分比）） - 受击方物理（法术）防御 = 攻击方伤害值(上下浮动10%)
	 * @param damage
	 * @param attackTalents
	 * @param defendTalents
	 * @param dCaptain 
	 * @param aCaptain 
	 * @param attackType 
	 * @return
	 */
	private float attackAndDefenseSettlement( float damage,
			Map<TalentType, TalentBase> attackTalents,
			Map<TalentType, TalentBase> defendTalents, List<FighterBase> aCaptain, List<FighterBase> dCaptain, 
			FighterBase attacker, FighterBase defender ) 
	{
		
		float hurtValue				= 1;
		
		// ----- 加血 -------
		if( damage < 0 ) {
			// 获取 治疗量
			float dose 				= getCaptain( defender, aCaptain, EffectType.CURATIVE_DOSE );
			hurtValue				= damage * ( 1f + dose/100f );
			
		// ----- 攻击 -------
		}else{
			
			// 攻击方物理（法术）穿透绝对值
			float attackPenetration	= getTalentValue( attackTalents, attacker.getAttackType() == 0 ? TalentType.PHY_PENETRATION : TalentType.MAGIC_PENETRATION);
			// 受击方物理（法术）抗性绝对值
			float defPitsex			= getTalentValue( defendTalents, attacker.getAttackType() == 0 ? TalentType.PHY_RESIST : TalentType.MAGIC_RESIST);
			// 攻击方物理（法术）穿透百分比
			float attackPentOdds	= getCaptain( attacker, aCaptain, attacker.getAttackType() == 0 ? EffectType.PHY_PENETRATION : EffectType.MAGIC_PENETRATION);
			// 受击方物理（法术）抗性百分比
			float defPitsexOdds		= getCaptain( defender, dCaptain, attacker.getAttackType() == 0 ? EffectType.PHY_RESISTANCE : EffectType.MAGIC_RESISTANCE);
			// 受击方物理（法术）防御  < 暂时没有 所有为0 >
			float defense			= 0;
			
			// 算出最终伤害值 ()
			hurtValue				= damage * ( 1 + ((attackPenetration - defPitsex)/10000 + (attackPentOdds - defPitsexOdds)/100) ) - defense;
		}
		
		// 浮动
		float drift				= (float)RandomUtil.getRandomInt(9000, 11000) / 10000f;
		hurtValue				= hurtValue * drift;
		
		// 最小都给1
		if( Math.abs( hurtValue ) < 1 && Math.abs( hurtValue ) > 0 )	 hurtValue = hurtValue / Math.abs( hurtValue );
		
		return hurtValue;
	}
	
	
	@Override
	public Comparator<FighterBase> getOrderComparator() {
		return speedComparator;
	}
	
	/**
	 * 根据类型获取 天赋对应信息
	 * @param talents
	 * @param type
	 * @return
	 */
	private float getTalentValue( Map<TalentType, TalentBase> talents,  TalentType type ){
		
		if( talents == null )
			return 0;
		
		if( talents.get(type) == null )
			return 0;
		
		return talents.get(type).getValue();
	}
	
	/**
	 * 根据类型获取 队长技能对应信息
	 * @param attacker 
	 * @param aCaptain
	 * @param hit
	 * @return
	 */
	private float getCaptain(FighterBase attacker, List<FighterBase> captain, EffectType type ) {
		float value = 0;
		
		for( FighterBase f : captain ){
			float fl = f.getCaptainSkill().run( attacker, type );
			value 	+= fl;
		}
		
		return value;
	}

	public static void main(String[] args) {
		float r = 100 / 133f;
		System.out.println( r );
		
		r = (float)200 / 30;
		System.out.println( r );
		
		int damage = 301;
		System.out.println( damage);
		
		
		
	}
}
