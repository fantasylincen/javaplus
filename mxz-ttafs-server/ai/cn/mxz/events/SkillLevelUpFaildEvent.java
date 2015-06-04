package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.team.Skill;

/**
 * 技能升级失败
 * @author 林岑
 *
 */
public class SkillLevelUpFaildEvent {

	private Skill	skill;
	private City	city;

	public SkillLevelUpFaildEvent(Skill skill, City city) {
		this.skill = skill;
		this.city = city;
	}

	public Skill getSkill() {
		return skill;
	}

	public City getCity() {
		return city;
	}
}
