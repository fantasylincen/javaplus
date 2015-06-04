package cn.mxz.listeners;

import cn.mxz.events.EquipmentSellEvent;
import cn.mxz.events.Listener;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.system.SnapsortEquipments;

public class EquipmentSellListener  extends SnapsortEquipments implements Listener<EquipmentSellEvent> {

	@Override
	public void onEvent(EquipmentSellEvent e) {
		String ids = e.getEquipmentIds();
		MessageFactory.getEquipment().equipmentsRemove(e.getCity().getSocket(), ids);
	}

}
