package cn.mxz.team;

import java.util.Collection;
import java.util.Map;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

/**
 * 战士容器
 * @author 林岑
 *
 */
abstract class FighterCollection {

	protected Map<Integer, Hero> fighters;

	protected City city;

	public Hero get(int fighterId) {

		final Hero god = fighters.get(fighterId);

		return god;
	}

	public void setFighters(Map<Integer, Hero> fighters) {
		this.fighters = fighters;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int size() {
		return fighters.size();
	}

	public Collection<Hero> getAll() {
		return fighters.values();
	}
	
	
}
