package cn.mxz.mission.type;

import cn.mxz.battle.BattleExpPrize;
import cn.mxz.protocols.user.mission.MissionP.MissionPrizePro.FighterPrizePro;

public class FighterPrizeBuilder {

	public FighterPrizePro build(BattleExpPrize fp) {
		final FighterPrizePro.Builder b = FighterPrizePro.newBuilder();

		b.setExpAdd(fp.getExp());

		b.setFighterId(fp.getFighterId());

		b.setLevelAdd(fp.getLevelAdd());

		b.setExpNeed(fp.getExpNeed());

//		Debuger.debug("FighterPrizeBuilder.build() 战士奖励: exp = " + fp.getExp() + ", fighterId:" + fp.getFighterId() + ", levelAdd:" + fp.getLevelAdd() + ", expNeed:" + fp.getExpNeed());

		return b.build();
	}

}
