package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

/**
 * 穿上一键装备后
 * @author 林岑
 *
 */
public class EquipmentAddEvent {
	private int		id;
	private int		fighterTypeId;
	private City	city;
	private Equipment equipment;

	public EquipmentAddEvent(Equipment equipment, int id, int fighterTypeId, City city) {
		this.equipment = equipment;
		this.id = id;
		this.fighterTypeId = fighterTypeId;
		this.city = city;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}

	public int getId() {
		return id;
	}

	public int getFighterTypeId() {
		return fighterTypeId;
	}

	public City getCity() {
		return city;
	}
}
