package cn.mxz.events;

import cn.mxz.city.City;

/**
 * 玩家属性发生变化事件
 * @author 林岑
 *
 */
public class AttributeChangeEvent {

	private City city;
	private int oldShenJia;

	public AttributeChangeEvent(City city, int oldShenJia) {
		this.city = city;
		this.oldShenJia = oldShenJia;
	}

	public City getCity() {
		return city;
	}

	public int getNewShenJia() {
		return city.getFormation().getShenJia();
	}

	public int getOldShenJia() {
		return oldShenJia;
	}
}
