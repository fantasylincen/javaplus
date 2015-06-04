package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

public class CreateNewHeroEvent {

	private City city;
	private Hero hero;

	public CreateNewHeroEvent(City city, Hero hero) {
		this.city = city;
		this.hero = hero;
	}
	
	public City getCity() {
		return city;
	}
	
	public Hero getHero() {
		return hero;
	}

}
