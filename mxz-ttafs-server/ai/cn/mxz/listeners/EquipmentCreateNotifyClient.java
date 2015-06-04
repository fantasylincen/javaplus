package cn.mxz.listeners;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentsBuilder;
import cn.mxz.events.EquipmentCreateEvent;
import cn.mxz.events.Listener;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentsPro;

import com.lemon.commons.socket.ISocket;

//获得新装备, 通知前端更新装备内存
public class EquipmentCreateNotifyClient implements Listener<EquipmentCreateEvent> {

	@Override
	public void onEvent(EquipmentCreateEvent e) {
		City city = e.getCity();
		ISocket socket = city.getSocket();
		List<? extends Equipment> all = e.getEquipments();
		EquipmentsPro equipments = new EquipmentsBuilder().build(all);
		MessageFactory.getEquipment().equipmentsUpdate(socket, equipments);
	}

}
