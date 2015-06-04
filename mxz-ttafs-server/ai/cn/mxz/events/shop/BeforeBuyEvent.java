package cn.mxz.events.shop;

import cn.mxz.city.City;

/**
 * 购买前事件
 * @author 林岑
 *
 */
public class BeforeBuyEvent {

	private int typeId;
	private int count;
	private City city;

	public BeforeBuyEvent(int typeId, int count, City city) {
		this.typeId = typeId;
		this.count = count;
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	public int getCount() {
		return count;
	}

	public int getTypeId() {
		return typeId;
	}

}
