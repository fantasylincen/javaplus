package cn.mxz.events;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;

public class FightingLoseEvent {

	private Battle	battle;
	private City	city;

	public FightingLoseEvent(Battle battle, City city) {
		this.battle = battle;
		this.city = city;
	}

	public Battle getBattle() {
		return battle;
	}

	public City getCity() {
		return city;
	}
}
