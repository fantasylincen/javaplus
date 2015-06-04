package cn.mxz.mission.old.events;

import cn.mxz.city.City;
import cn.mxz.user.mission.Mission;

public class EndEvent {

	private Mission	mission;

	private City	city;


//	private MissionMap	map;

	
	public EndEvent(Mission m, City city/*, MissionMap map*/) {

		this.mission = m;

		this.city = city;
//		this.map = map;

	}
//
//	public MissionMap getMap() {
//		return map;
//	}

	public Mission getMission() {
		return mission;
	}

	public City getCity() {
		return city;
	}
}
