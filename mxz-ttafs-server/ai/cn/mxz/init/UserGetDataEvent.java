package cn.mxz.init;

import cn.mxz.city.City;

public class UserGetDataEvent {

	private City city;

	public UserGetDataEvent(City city) {
		this.city = city;
	}

	public City getCity() {
		return city;
	}

}
