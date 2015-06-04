package cn.mxz.listeners;

import cn.mxz.events.EquipmentGenerate3Event;
import cn.mxz.events.Listener;
import cn.mxz.system.SnapsortEquipments;

public class EquipmentGenerate3Listener  extends SnapsortEquipments implements Listener<EquipmentGenerate3Event> {

	@Override
	public void onEvent(EquipmentGenerate3Event e) {
		update(e.getCity(), e.getEquipment());
	}

}
