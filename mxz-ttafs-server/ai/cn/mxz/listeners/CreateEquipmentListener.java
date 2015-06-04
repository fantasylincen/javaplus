package cn.mxz.listeners;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentsBuilder;
import cn.mxz.events.EquipmentCreateEvent;
import cn.mxz.events.Listener;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentsPro;

public class CreateEquipmentListener implements Listener<EquipmentCreateEvent> {

	@Override
	public void onEvent(EquipmentCreateEvent e) {
		City city = e.getCity();
		List<? extends Equipment> es = e.getEquipments();

		EquipmentsPro equipments = new EquipmentsBuilder().build(es);

		MessageFactory.getEquipment().equipmentsUpdate(city.getSocket(), equipments);

	}

}
