package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.fighter.PlayerHero;

/**
 * 主角进阶事件
 * @author 林岑
 *
 */
public class PlayerHeroQualityUpEvent {

	private City	city;
	private PlayerHero	hero;

	public PlayerHeroQualityUpEvent(City city, PlayerHero hero) {
		this.city = city;
		this.hero = hero;
	}

	public City getCity() {
		return city;
	}

	public PlayerHero getHero() {
		return hero;
	}
}
