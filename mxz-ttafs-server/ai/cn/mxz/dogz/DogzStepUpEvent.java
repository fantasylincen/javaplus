package cn.mxz.dogz;

import cn.mxz.city.City;

public class DogzStepUpEvent {

	private int	old;

	private City	city;

	public DogzStepUpEvent(int old, City city) {


		this.old = old;

		this.city = city;
	}

	public int getOld() {
		return old;
	}

	public City getCity() {
		return city;
	}
}
