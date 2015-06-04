package cn.mxz.findfighter;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

public class FindFighterEvent {

	private City	city;
	private List<Hero>	heros;

	
	public FindFighterEvent(City city, List<Hero> ls) {

		this.city = city;
		this.heros = ls;
	}

	public City getCity() {
		return city;
	}

	public List<Hero> getHeros() {
		return heros;
	}
}
