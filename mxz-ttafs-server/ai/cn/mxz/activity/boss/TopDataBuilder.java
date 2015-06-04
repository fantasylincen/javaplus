package cn.mxz.activity.boss;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.city.City;
import cn.mxz.protocols.user.boss.BossP.BossPro.ChallengerPro;
import cn.mxz.user.builder.UserBaseBuilder;

/**
 * Boss当前的MVP JMVP 击杀者 和我自己的信息
 *
 * @author 林岑
 *
 */
class TopDataBuilder {

	public Iterable<ChallengerPro> build(Boss boss, City city) {

		List<ChallengerPro> list = new ArrayList<ChallengerPro>();

		buildMvp(list, boss, city);

		buildJMvp(list, boss, city);

		buildMe(list, boss, city);

		buildKiller(list, boss, city);

		return list;
	}

	// 类型 1:我 2:MVP 3:JMVP 4:击杀者

	private void buildKiller(List<ChallengerPro> list, Boss boss, City city) {

		BossChallenger killer = boss.getKiller();

		add(list, boss, city, killer, 4);
	}

	private void buildMe(List<ChallengerPro> list, Boss boss, City city) {

		BossChallenger me = getMe(boss, city);

		add(list, boss, city, me, 1);

	}

	private BossChallenger getMe(Boss boss, City city) {

		List<BossChallenger> bossChallengers = boss.getBossChallengers();

		for (BossChallenger c : bossChallengers) {

			if (c.getId().equals(city.getId())) {

				return c;
			}
		}

		return null;
	}

	private void buildJMvp(List<ChallengerPro> list, Boss boss, City city) {

		BossChallenger jmvp = boss.getJMvp();

		add(list, boss, city, jmvp, 3);
	}

	private void buildMvp(List<ChallengerPro> list, Boss boss, City city) {

		BossChallenger mvp = boss.getMvp();

		add(list, boss, city, mvp, 2);
	}

	private void add(List<ChallengerPro> list, Boss boss, City city, BossChallenger c, final int value) {

		if (c != null) {

			ChallengerPro.Builder b = buildBase(c, city, boss);

			b.setType(value);

			list.add(b.build());
		}
	}

	private ChallengerPro.Builder buildBase(BossChallenger c, City city, Boss boss) {

		ChallengerPro.Builder b = ChallengerPro.newBuilder();

		BossChallenger killer = boss.getKiller();

		b.setDamage(c.getDamage(boss));

		if (killer == null) {

			b.setIsKiller(false);

		} else {

			String id = c.getId();

			b.setIsKiller(id.equals(killer.getId()));
		}

		b.setRank(c.getRank(boss));

		b.setUser(new UserBaseBuilder().build(c.getCity().getPlayer()));

		return b;
	}
}
