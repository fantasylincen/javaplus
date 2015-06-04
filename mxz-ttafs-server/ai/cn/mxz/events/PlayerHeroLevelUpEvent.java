package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

public class PlayerHeroLevelUpEvent {

	private Hero hero;
	private City	city;
	private int	level;


	public PlayerHeroLevelUpEvent(Hero hero, City city, int level) {
		this.hero = hero;
		this.city = city;
		this.level = level;
	}

	public City getCity() {
		return city;
	}


	public int getLevel() {
		return level;
	}

	public Hero getHero() {
		return hero;
	}
}
