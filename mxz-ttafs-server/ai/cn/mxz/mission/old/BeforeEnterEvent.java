package cn.mxz.mission.old;

import cn.mxz.city.City;

/**
 * 进入地图之前调用
 * @author 林岑
 *
 */
public class BeforeEnterEvent {

	private City	city;

	
	public BeforeEnterEvent(City city) {
		this.city = city;
	}

	public City getCity() {
		return city;
	}
}
