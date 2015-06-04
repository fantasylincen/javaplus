package cn.mxz.levelupreward;

import java.util.List;

import cn.mxz.LevelUpRewardTemplet;
import cn.mxz.city.City;

public class Reward {

	private List<Integer> received;
	private LevelUpRewardTemplet l;
	private City city;

	public Reward(City city, List<Integer> received, LevelUpRewardTemplet l) {
		this.city = city;
		this.received = received;
		this.l = l;
	}

	public boolean hasReceive() {
		return received.contains(l.getNeedLevel());
	}

	public int getNeedLevel() {
		return l.getNeedLevel();
	}

	/**
	 * 是否可以领取
	 * @return
	 */
	public boolean canReceive() {
		return !hasReceive() && city.getLevel() >= getNeedLevel();
	}

}
