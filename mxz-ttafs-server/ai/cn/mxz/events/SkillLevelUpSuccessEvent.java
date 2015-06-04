package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.team.Skill;

/**
 * 技能升级成功
 * @author 林岑
 */
public class SkillLevelUpSuccessEvent {

	private Skill	skill;
	private City	city;

	public SkillLevelUpSuccessEvent(Skill skill, City city) {
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
