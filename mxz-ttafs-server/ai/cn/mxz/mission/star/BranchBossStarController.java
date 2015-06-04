package cn.mxz.mission.star;

import db.domain.MissionStar;

public class BranchBossStarController {

	private MissionStar	m;

	public BranchBossStarController(db.domain.MissionStar m) {
		this.m = m;
	}

	public void add(int star) {

		int index = 1;
		int v = m.getBranchBossStar(index);

		v = v + star;

		if (v > 3) {
			v = 3;
		}

		m.setBranchBossStar(index, v);
		if (v > m.getBranchBossStarMax(index)) {
			m.setBranchBossStarMax(index, v);
		}
	}

	public int getCountMax() {

		int ds = m.getDemonStarMax();
		int ms = m.getMainBossStarMax();

		int c = 0;
		for (int i = 0; i < db.domain.MissionStarImpl.BRANCH_BOSS_STAR__LEN; i++) {
			c += m.getBranchBossStarMax(i);
		}

		return ds + ms + c;
	}

	public int getCurrentCount() {
		int ds = m.getDemonStar();
		int ms = m.getMainBossStar();

		int c = 0;
		for (int i = 0; i < db.domain.MissionStarImpl.BRANCH_BOSS_STAR__LEN; i++) {
			c += m.getBranchBossStar(i);
		}

		return ds + ms + c;
	}

}
