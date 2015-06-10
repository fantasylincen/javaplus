package game.skill;

import config.skill.accord.SkillTemplet;
import define.DefaultCfg;

/**
 * 主动技能
 * @author DXF
 */
public class AccordSkill {
	
	private SkillTemplet 		skill 	= null;
	
	private byte				level	= 0;
	
	public AccordSkill( SkillTemplet skill, byte level ){
		this.skill = skill;
		this.level = level;
		if( skill == null ) this.level = 0;
	}
	
	public SkillTemplet getSkill(){
		return skill;
	}
	
	public byte getLevel(){
		return this.level;
	}
	public void setLevel( byte level ){
		this.level = level;
	}
	
	/**
	 * 是否满级
	 * @return
	 */
	public boolean isFullLevel() {
		return level >= DefaultCfg.SKILL_MAX_LEVEL;
	}

	/** 升级 */
	public void updatalevel() {
		++this.level;
		if( this.level >= DefaultCfg.SKILL_MAX_LEVEL ){
			this.level = DefaultCfg.SKILL_MAX_LEVEL;
		}
	}

	public String toName() {
		return skill.getName();
	}
}
