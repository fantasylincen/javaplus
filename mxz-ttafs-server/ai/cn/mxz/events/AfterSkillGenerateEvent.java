package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.team.Skill;

/**
 * 技能合成成功后
 * @author 林岑
 *
 */
public class AfterSkillGenerateEvent {

	private City	city;
	private Skill	skill;

	public AfterSkillGenerateEvent(City city, Skill skill) {
		this.city = city;
		this.skill = skill;

	}

	public City getCity() {
		return city;
	}

	public Skill getSkill() {
		return skill;
	}

}
