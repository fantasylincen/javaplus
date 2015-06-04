package cn.mxz.tower;

import cn.mxz.city.City;
import define.D;


class TowerBugImpl implements TowerBug {

	private City	city;

	private long	createTime;

	TowerBugImpl(City city) {

		this.city = city;

		createTime = System.currentTimeMillis();
	}

	@Override
	public boolean isMine(String userId) {

		return getFinder().equals(userId);
	}

	@Override
	public String getFinder() {

		return city.getId();
	}

	@Override
	public int getRemainSec() {

		long now = System.currentTimeMillis();

		long s = now - createTime;

		long t = D.TOWER_BUG_TIME * 1000 - s;

		return (int) (t < 0 ? 0 : t / 1000);
	}

}
