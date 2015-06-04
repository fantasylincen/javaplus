package cn.mxz.battle;

import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.city.City;
import cn.mxz.protocols.user.god.FighterP.FighterSkillPro;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.team.Skill;
import cn.mxz.user.team.Team;

public class FighterSkillBuilder {

	public FighterSkillPro build(City city, Skill skill) {

		FighterSkillPro.Builder b = FighterSkillPro.newBuilder();
		int id = skill.getId();

		b.setSkillId(id);
		b.setSkillLevel(skill.getLevel());

		int fighterId = skill.getFighterId();

		Team team = city.getTeam();

		if (!team.contains(fighterId)) {
			fighterId = -1;
		}

		b.setFighterId(fighterId);
		b.setPrice(skill.getPrice());
		b.setFighterName(getFighterName(fighterId));

		Addition damage = skill.getDamage();

		int value1 = getValue(damage);
		b.setDamage(value1);

		damage = skill.getDamageNext();
		
		int value2 = getValue(damage);
		b.setDamageNext(value2);
		
//		Debuger.debug("FighterSkillBuilder.build()    " + value1 + ", " + value2);

		int identification = skill.getIdentification();

		// Debuger.debug("FighterSkillBuilder.build()" + id + "," +
		// identification);

		b.setId(identification);
		return b.build();
	}

	private int getValue(Addition damage) {
		float v = damage.getValue();
		if(damage.isPercent()) {
			return (int) (v * 100);
		} else {
			return (int) v;
		}
	}

	private String getFighterName(int fighterId) {
		IFighterTemplet temp = FighterTempletConfig.get(fighterId);
		return temp != null ? temp.getName() : "";
	}

}
