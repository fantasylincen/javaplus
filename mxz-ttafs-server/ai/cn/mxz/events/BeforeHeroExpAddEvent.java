package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

public class BeforeHeroExpAddEvent {

	private Hero	hero;
	private City	city;
	private int	level;

	public BeforeHeroExpAddEvent(Hero hero, City city, int level) {
		this.hero = hero;
		this.city = city;
		this.level = level;
	}

	public City getCity() {
		return city;
	}
	public Hero getHero() {
		return hero;
	}
	public int getLevel() {
		return level;
	}
}
