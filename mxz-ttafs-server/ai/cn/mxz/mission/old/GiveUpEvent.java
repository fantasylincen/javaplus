package cn.mxz.mission.old;

import cn.mxz.city.City;

public class GiveUpEvent {

	private City	city;
//	private MissionMap	map;

	
	public GiveUpEvent(City city/*, MissionMap map*/) {
		this.city = city;
//		this.map = map;
	}

	public City getCity() {
		return city;
	}

//	public MissionMap getMap() {
//		return map;
//	}
}
