package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.EquipmentChangeEvent;
import cn.mxz.events.Listener;
import cn.mxz.system.SnapsortEquipments;

public class EquipmentChangeListener  extends SnapsortEquipments implements Listener<EquipmentChangeEvent> {

	@Override
	public void onEvent(EquipmentChangeEvent e) {
		City user = e.getUser();
		update(user, e.getChange());
	}


}
