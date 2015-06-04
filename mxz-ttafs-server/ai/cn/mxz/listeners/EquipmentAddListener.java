package cn.mxz.listeners;

import cn.mxz.equipment.Equipment;
import cn.mxz.events.EquipmentAddEvent;
import cn.mxz.events.Listener;
import cn.mxz.system.SnapsortEquipments;

public class EquipmentAddListener extends SnapsortEquipments implements Listener<EquipmentAddEvent> {

	@Override
	public void onEvent(EquipmentAddEvent e) {
		Equipment equipment = e.getEquipment();
		update(e.getCity(), equipment);
	}
}
