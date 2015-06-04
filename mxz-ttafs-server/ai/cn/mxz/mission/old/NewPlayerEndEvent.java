package cn.mxz.mission.old;

import cn.mxz.city.City;
import cn.mxz.user.mission.Mission;

public class NewPlayerEndEvent {

	private Mission mission;
	private City city;

	
	
	public NewPlayerEndEvent(Mission mission, City city) {
		this.mission = mission;
		this.city = city;
	}

	public Mission getMission() {
		return mission;
	}

	public City getCity() {
		return city;
	}
}
