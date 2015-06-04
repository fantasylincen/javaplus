package cn.mxz.events;

import cn.mxz.city.City;

/**
 * 老账号接入服务器事件
 * @author 林岑
 *
 */
public class OldUserAccessEvent {

	private City	city;

	public OldUserAccessEvent(City city) {
		this.city = city;
	}

	public City getCity() {
		return city;
	}
}
