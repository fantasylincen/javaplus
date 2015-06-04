package cn.mxz.events;

import cn.mxz.bag.Bag;
import cn.mxz.bag.Grid;
import cn.mxz.city.City;

/**
 * 获得物品事件
 * @author 林岑
 *
 */
public class GetPropEvent {

	private City	city;
	private Bag<Grid>	bag;
	private int id;
	private int count;

	public GetPropEvent(City city, Bag<Grid> bag, int id, int count) {
		this.city = city;
		this.bag = bag;
		this.id = id;
		this.count = count;
	}

	public Bag<Grid> getBag() {
		return bag;
	}

	public City getCity() {
		return city;
	}
	
	/**
	 * 物品ID
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 物品数量
	 * @return
	 */
	public int getCount() {
		return count;
	}
}
