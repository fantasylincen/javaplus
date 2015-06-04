package cn.mxz.enemy;

import cn.mxz.battle.WarSituation;
import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.protocols.user.enemy.EnemyP.EnemyWarSituationPro;

public class EnemyWarSituationBuilder {

	public EnemyWarSituationPro build(EnemyBattle battle) {
		WarSituation warSituation = battle.getWarSituation();
		EnemyWarSituationPro.Builder b = EnemyWarSituationPro.newBuilder();
		WarSituationPro build = new WarSituationBuilder().build(battle.getUnderPlayerCamp().getCity(), warSituation);
		b.setSituation(build);
		return b.build();
	}

}
