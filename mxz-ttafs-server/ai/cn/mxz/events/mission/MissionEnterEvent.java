package cn.mxz.events.mission;

import cn.mxz.city.City;

/**
 * 进入副本
 * @author Administrator
 *
 */
public class MissionEnterEvent {

	private City user;

	public MissionEnterEvent(City user) {
		this.user = user;
	}
	
	public City getUser() {
		return user;
	}

}
