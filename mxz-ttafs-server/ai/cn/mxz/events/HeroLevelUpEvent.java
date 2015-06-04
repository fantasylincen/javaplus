package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

/**
 * 神将升级事件
 * @author 林岑
 *
 */
public class HeroLevelUpEvent{

	private Hero hero;
	private City	city;
	private int	level;


	public HeroLevelUpEvent(Hero hero, City city, int level) {
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
