package cn.mxz.events.dogz;

import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;

public class DogzLevelUpEvent {

	private Dogz dogz;
	private City city;

	public DogzLevelUpEvent(Dogz dogz, City city) {
		this.dogz = dogz;
		this.city = city;
	}

	public Dogz getDogz() {
		return dogz;
	}

	public City getCity() {
		return city;
	}

}
