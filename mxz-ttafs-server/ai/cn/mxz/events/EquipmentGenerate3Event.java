package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

/**
 * 装备合成后
 * @author 林岑
 *
 */
public class EquipmentGenerate3Event {
	private int	stuffId;
	private City	city;
	private Equipment equipment;

	public EquipmentGenerate3Event(Equipment equipment, int stuffId, City city) {
		this.equipment = equipment;
		this.stuffId = stuffId;
		this.city = city;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}

	public City getCity() {
		return city;
	}

	public int getStuffId() {
		return stuffId;
	}
}
