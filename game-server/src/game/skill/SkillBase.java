package game.skill;

import game.fighter.FighterBase;
import config.skill.accord.ChooseFighters;
import config.skill.accord.SkillEffect;
import config.skill.accord.SkillTemplet;

/**
 * 基本技能信息  只作为普通技能和主动技能
 * @author DXF
 *
 */
public class SkillBase {

	
	// 技能模板
	private SkillTemplet 		skill;
	
	// 技能等级
	private byte				level;
	
	// 是否主动技能
	private boolean 			isCan;
	
	// 攻击类型
	private byte				attackType;
	
	public SkillBase( SkillTemplet skill, byte level, boolean isCan, byte attackType ){
		this.skill 		= skill;
		this.level		= level;
		this.isCan		= isCan;
		this.attackType	= attackType;
	}
	
	public byte getLevel(){
		return this.level;
	}
	
	public SkillTemplet getSkill(){
		return this.skill;
	}
	
	public boolean getIsCan(){
		return this.isCan;
	}
	
	public byte getAttackType(){
		return this.attackType;
	}

	public int getRival() {
		return skill.getRival();
	}

	public ChooseFighters getChoose() {
		return skill.getChoose();
	}

	public int getId() {
		return skill.getId();
	}

	
	/**
	 * 执行技能功能
	 * @param attacker
	 * @param defender
	 * @return
	 */
	public float run(FighterBase attacker, FighterBase defender) {
		
		SkillEffect effect 	= skill.getSkillEffect();
		
		if( effect == null ) return 0;
		
		float damage 		= effect.run(attacker, defender, level);
		
		return damage;
	}
}
