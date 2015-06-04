package cn.mxz.fighter;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;
import cn.mxz.team.Skill;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

public class SkillTianMing {

	private Hero	hero;

	public SkillTianMing(Hero hero) {
		this.hero = hero;
	}

	/**
	 * 天命加成
	 * @return
	 */
	public Attribute getAddition() {

		List<Integer> ids = getIds();

		Attribute a = new AttributeEmpty();

		for (Integer tId : ids) {

			Additions additions = hero.getAdditions();
			ExclusiveTemplet temp = ExclusiveTempletConfig.get(tId);
			if(temp.getJudge() == 0) {
				continue;
			}
			a = AttributeCalculator.adding(a, new TianMingAddition(additions.getBase2(), temp));
		}

		return a;
	}

	/**
	 * 所有激活的天命ID列表
	 *
	 * @return
	 */
	public List<Integer> getIds() {
		List<Integer> ls = Lists.newArrayList();
		List<Skill> all = hero.getSkills();

		for (Skill skill : all) {
			int typeId = hero.getTypeId();
			int id = skill.getId();
			Integer tId = SkillTianMingConfig.getId(typeId, id);
			if (tId != null) {
				ls.add(tId);
			}
		}
		return ls;
	}

}
