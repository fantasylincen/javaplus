package cn.mxz.fighter;

import cn.mxz.FighterConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.user.team.god.Hero;

public class TianFuSkill implements IdLevel {

	private Fighter	fighter;

	public TianFuSkill(Fighter fighter) {
		this.fighter = fighter;
	}

	@Override
	public int getId() {
		IFighterTemplet t = FighterConfig.get(fighter.getTypeId());
		return t.getSkill();
	}

	@Override
	public int getLevel() {
		if (fighter instanceof Hero) {
			Hero h = (Hero) fighter;
			return h.getTianFuSkill().getLevel();
		}
		return 1;
	}

}
