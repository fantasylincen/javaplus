package cn.mxz.fighter;

import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.skill.SkillManager;
import cn.mxz.team.Skill;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

public class SkillDeleter {

	/**
	 * 移除天赋技能
	 * @param from
	 */
	public void removeSkill(Hero from) {
		FighterTemplet temp = FighterTempletConfig.get(from.getTypeId());
		int sId = temp.getSkill();

		City city = from.getCity();
		SkillManager sm = city.getSkillManager();
		List<Skill> all = sm.getSkills(from);

		for (Skill skill : all) {
			int id = skill.getId();
			if (sId == id) {
				Debuger.debug("Inherit.removeSkill() 删除了伙伴的天赋技能:" + sId);
				sm.removeByIds(skill.getIdentification());
			}
		}
	}
}
