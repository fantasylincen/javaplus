package cn.mxz.listeners;

import cn.mxz.events.EquipmentLevelUpEvent;
import cn.mxz.events.Listener;
import cn.mxz.system.SnapsortEquipments;

//装备变化通知前端
public class SnapsortEquipments2  extends SnapsortEquipments implements Listener<EquipmentLevelUpEvent> {

	@Override
	public void onEvent(EquipmentLevelUpEvent e) {
		update(e.getCity(), e.getEquipment());
	}

}
