package cn.javaplus.crazy.pocker;

import java.util.List;

public class BeforeCpEvent {

	private Place place;
	private List<Integer> ids;

	public BeforeCpEvent(List<Integer> ids, Place place) {
		this.ids = ids;
		this.place = place;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public Place getPlace() {
		return place;
	}

}
