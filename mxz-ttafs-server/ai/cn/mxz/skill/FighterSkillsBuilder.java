package cn.mxz.skill;

import java.util.List;

import cn.mxz.battle.FighterSkillBuilder;
import cn.mxz.city.City;
import cn.mxz.protocols.user.god.FighterP.FighterSkillsPro;
import cn.mxz.team.Skill;

public class FighterSkillsBuilder {

	public FighterSkillsPro build(City city, List<Skill> skills) {
		FighterSkillsPro.Builder b = FighterSkillsPro.newBuilder();
		for (Skill skill : skills) {
			b.addSkills(new FighterSkillBuilder().build(city, skill));
		}
		return b.build();
	}

}
