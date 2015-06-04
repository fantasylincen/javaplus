package cn.mxz.events;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

public class EquipmentSellEvent {
	private String	equipmentIds;
	private City	city;
	private List<Equipment> equipments;

	public EquipmentSellEvent(List<Equipment> equipments, String equipmentIds, City city) {
		this.equipments = equipments;
		this.equipmentIds = equipmentIds;
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	public String getEquipmentIds() {
		return equipmentIds;
	}
	
	public List<Equipment> getEquipments() {
		return equipments;
	}
}
