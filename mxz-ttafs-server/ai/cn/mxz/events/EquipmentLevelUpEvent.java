package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

public class EquipmentLevelUpEvent {

	private int	id;
	private int	levelAdd;
	private City	city;
	private Equipment equipment;

	public EquipmentLevelUpEvent(Equipment equipment, int id, int levelAdd, City city) {
		this.equipment = equipment;
		this.id = id;
		this.levelAdd = levelAdd;
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public int getLevelAdd() {
		return levelAdd;
	}
	public City getCity() {
		return city;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}

}
