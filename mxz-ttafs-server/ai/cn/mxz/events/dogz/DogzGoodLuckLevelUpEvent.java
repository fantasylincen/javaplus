package cn.mxz.events.dogz;

import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;

public class DogzGoodLuckLevelUpEvent {

	private Dogz dogz;
	private City city;

	public DogzGoodLuckLevelUpEvent(Dogz dogz, City city) {
		this.dogz = dogz;
		this.city = city;
	}

	public City getCity() {
		return city;
	}
	public Dogz getDogz() {
		return dogz;
	}
}
