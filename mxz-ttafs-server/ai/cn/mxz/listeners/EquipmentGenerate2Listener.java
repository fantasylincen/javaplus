package cn.mxz.listeners;

import cn.mxz.events.EquipmentGenerate2Event;
import cn.mxz.events.Listener;
import cn.mxz.system.SnapsortEquipments;

public class EquipmentGenerate2Listener  extends SnapsortEquipments implements Listener<EquipmentGenerate2Event> {

	@Override
	public void onEvent(EquipmentGenerate2Event e) {
		update(e.getCity(), e.getEquipment());
	}

}
