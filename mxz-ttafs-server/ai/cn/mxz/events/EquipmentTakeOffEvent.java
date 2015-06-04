package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

public class EquipmentTakeOffEvent {
	private int	fighterId;
	private int	equipmentId;
	private City	city;
	private Equipment equipment;

	public EquipmentTakeOffEvent(Equipment equipment, int fighterId, int equipmentId, City city) {
		this.equipment = equipment;
		this.fighterId = fighterId;
		this.equipmentId = equipmentId;
		this.city = city;
	}

	public int getFighterId() {
		return fighterId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public City getCity() {
		return city;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}
}
