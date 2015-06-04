package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

/**
 * 装备合成后
 * @author 林岑
 */
public class EquipmentGenerate2Event {

	private int	srcEquipmentId;
	private int	dstEquipmentTempletId;
	private City	city;
	private Equipment equipment;

	public EquipmentGenerate2Event(Equipment equipment, int srcEquipmentId, int dstEquipmentTempletId, City city) {
		this.equipment = equipment;
		this.srcEquipmentId = srcEquipmentId;
		this.dstEquipmentTempletId = dstEquipmentTempletId;
		this.city = city;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}

	public City getCity() {
		return city;
	}
	public int getSrcEquipmentId() {
		return srcEquipmentId;
	}
	public int getDstEquipmentTempletId() {
		return dstEquipmentTempletId;
	}

}
