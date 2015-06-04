package cn.mxz.events;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;

/**
 */
public class FightEndEvent {

	private City city;
	private Battle battle;

	public FightEndEvent(Battle battle, City city) {
		this.battle = battle;
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	public Battle getBattle() {
		return battle;
	}
}
