package cn.mxz.activity.boss;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Maps;

import db.dao.impl.BossDamageDataDao;
import db.dao.impl.DaoFactory;
import db.domain.BossDamageData;

class DamageComparetor implements Comparator<BossChallenger> {

	private List<BossDamageData> all;
	private HashMap<String, BossDamageData> allDamages;

	DamageComparetor(Boss boss) {
		BossDamageDataDao DAO = DaoFactory.getBossDamageDataDao();
		all = DAO.findByBossId(boss.getId());
		allDamages = Maps.newHashMap();
		for (BossDamageData d : all) {
			allDamages.put(d.getChallengerId(), d);
		}
	}

	@Override
	public int compare(BossChallenger o1, BossChallenger o2) {
		BossDamageData d1 = allDamages.get(o1.getId());
		BossDamageData d2 = allDamages.get(o2.getId());
		int a1 = d1 != null ? d1.getDamage() : 0;
		int a2 = d2 != null ? d2.getDamage() : 0;
		return a2 - a1;
	}

}
