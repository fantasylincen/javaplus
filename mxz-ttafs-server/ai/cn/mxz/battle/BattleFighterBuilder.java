package cn.mxz.battle;

import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.fighter.Fighter;
import cn.mxz.protocols.user.battle.WarSituationP.BattleFighterPro;

class BattleFighterBuilder {

	public BattleFighterPro build(Fighter f, Camp<?> under) {
		BattleFighterPro.Builder b = BattleFighterPro.newBuilder();

		b.setHp(f.getHpNow());
		b.setHpMax(f.getHpMax());
		b.setId(f.getTypeId());
		b.setPosition(under.getPosition(f));

		b.setSpeed(f.getAttribute().getSpeed());

		int quality = f.getQuality();
		GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);
		int step = temp.getStep();
		b.setStep(step);

		return b.build();
	}
}
