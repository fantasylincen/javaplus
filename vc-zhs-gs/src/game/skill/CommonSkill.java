package game.skill;

import config.skill.accord.SkillTemplet;

/**
 * 普通技能
 * @author DXF
 */
public class CommonSkill {
	
	private SkillTemplet 		skill = null;
	
	
	public CommonSkill( SkillTemplet skill ){
		this.skill = skill;
	}
	
	public SkillTemplet getSkill(){
		return skill;
	}
	
	
}
