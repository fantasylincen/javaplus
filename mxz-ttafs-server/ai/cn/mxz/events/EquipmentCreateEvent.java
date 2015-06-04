package cn.mxz.events;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

/**
 * 装备创建时的事件
 * @author 林岑
 *
 */
public class EquipmentCreateEvent {

	private List<? extends Equipment>	equipments;
	private City	city;

	public EquipmentCreateEvent(List<? extends Equipment> equipments, City city) {
		this.equipments = equipments;
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	public List<? extends Equipment> getEquipments() {
		return equipments;
	}
}
