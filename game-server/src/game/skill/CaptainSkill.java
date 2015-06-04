package game.skill;

import game.fighter.FighterBase;
import config.skill.captain.CaptainSkillTemplet;
import config.skill.captain.CaptainSkillTempletCfg;
import config.skill.captain.EffectType;

/**
 * 队长技能
 * @author DXF
 */
public class CaptainSkill {
	
	private CaptainSkillTemplet 		skill = null;
	
	public CaptainSkill( ){
		
	}
	public CaptainSkill( CaptainSkillTemplet skill ){
		this.skill = skill;
	}
	
	public CaptainSkillTemplet getSkill(){
		return skill;
	}

	public int getID() {
		return skill == null ? 0 : skill.getId();
	}

	public void setSkill( int skillID ) {
		this.skill = CaptainSkillTempletCfg.getById( skillID );
	}

	public byte getLevel() {
		return (byte)(skill == null ? 0 : skill.getId() % 10);
	}
	
	/**
	 * 执行队长技能效果
	 * @param fighterBase
	 */
	public void run( FighterBase fighter ) {
		
		if( skill == null )
			return;
		
		skill.run( fighter );
	}
	
	/**
	 * 根据类型获取队长技能数值
	 * @param attacker 
	 * @param type
	 * @return
	 */
	public float run( FighterBase fighter, EffectType type ) {
		
		if( skill == null )
			return 0;
		
		return skill.run( fighter, type );
	}
	
}
