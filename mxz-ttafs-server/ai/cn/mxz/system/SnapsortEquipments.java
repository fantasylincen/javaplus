package cn.mxz.system;

import java.util.Collection;

import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentsBuilder;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentsPro;

import com.google.common.collect.Lists;

public class SnapsortEquipments {

	protected void update(City city, Equipment... equipment) {
		Collection<? extends Equipment> equipments = Lists.newArrayList(equipment);
		update(city, equipments);
	}


	protected void update(City user, Collection<? extends Equipment> equipments ) {
		EquipmentsPro build = new EquipmentsBuilder().build(equipments);
		MessageFactory.getEquipment().equipmentsUpdate(user.getSocket(), build);
	}
	
//	public void onEvent(City city) {
//
//		TempCollection c = city.getTempCollection();
//
//		EquipmentSnapshoot s = (EquipmentSnapshoot) c.get(TempKey.EQUIPMENT_SNAPSORT);
//
//		EquipmentManager em = city.getEquipmentManager();
//
//		Collection<? extends Equipment> all = em.getAll().values();
//
//		if (s == null) { // 是否是第一次
//			s = new EquipmentSnapshoot(city);
//			c.put(TempKey.EQUIPMENT_SNAPSORT, s);
//			s.snapshoot(all);
//			
//		} else {
//			s.snapshoot(all);
//			c.remove(TempKey.EQUIPMENT_SNAPSORT);
//		}
//
//	}

}
