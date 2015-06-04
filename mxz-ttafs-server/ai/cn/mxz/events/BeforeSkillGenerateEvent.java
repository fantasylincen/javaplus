package cn.mxz.events;

import cn.mxz.city.City;

/**
 * 技能合成前
 * @author 林岑
 *
 */
public class BeforeSkillGenerateEvent {

	private City	city;
	private int	id;

	public BeforeSkillGenerateEvent(City city, int id) {
		this.city = city;
		this.id = id;
	}

	public City getCity() {
		return city;
	}
	public int getId() {
		return id;
	}
}
