package cn.mxz.events;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;

public class EquipmentChangeEvent {
	private int	id1;
	private int	id2;
	private City	user;
	private List<Equipment> change;

	public EquipmentChangeEvent(List<Equipment> change, int id1, int id2, City user) {
		this.change = change;
		this.id1 = id1;
		this.id2 = id2;
		this.user = user;
	}
	public List<Equipment> getChange() {
		return change;
	}

	public int getId1() {
		return id1;
	}
	public int getId2() {
		return id2;
	}
	public City getUser() {
		return user;
	}
}
