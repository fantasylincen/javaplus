package cn.mxz.events.fighter;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

/**
 * 进阶成功
 * 
 * @author 林岑
 * 
 */
public class QualityLevelUpEvent {

	private City city;
	private Hero hero;

	public QualityLevelUpEvent(City city, Hero hero) {
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
