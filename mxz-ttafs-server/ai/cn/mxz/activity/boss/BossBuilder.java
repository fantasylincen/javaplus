package cn.mxz.activity.boss;

import java.util.List;

import cn.javaplus.math.Fraction;
import cn.mxz.city.City;
import cn.mxz.protocols.user.boss.BossP.BossPro;
import cn.mxz.util.FractionBuilder;


public class BossBuilder {

	public BossPro build(Boss boss, City city) {

		BossPro.Builder b = BossPro.newBuilder();

		Fraction hp = boss.getHp();

		if (hp.getNumerator() < 0) {

			hp = new Fraction(0, hp.getDenominator());
		}

		b.setHp(new FractionBuilder().build(hp));

		b.setId(boss.getId());

		b.setTempletId(boss.getTempletId());

		b.setLevel(boss.getLevel());

		b.setMultiple(boss.getMultiple());

		b.setRemainSec(boss.getRemainSec());

		List<BossChallenger> all = boss.getBossChallengers();

		for (BossChallenger bc : all) {

			int damage = bc.getDamage(boss);

			if (damage > 0 || bc.getId().equals(city.getId())) {// 伤害大于0,
																// 或者是自己的时候, 才显示

				b.addChallengers(new ChallengerBuilder().build(bc, boss));
			}
		}

		b.addAllTopData(new TopDataBuilder().build(boss, city));

		b.setIsSuperBoss(boss.isSuperBoss());

		return b.build();
	}

}
