package cn.mxz.listeners;

import cn.mxz.events.EquipmentTakeOffEvent;
import cn.mxz.events.Listener;
import cn.mxz.system.SnapsortEquipments;

public class EquipmentTakeOffListener  extends SnapsortEquipments implements Listener<EquipmentTakeOffEvent> {

	@Override
	public void onEvent(EquipmentTakeOffEvent e) {
		update(e.getCity(), e.getEquipment());
	}

}
