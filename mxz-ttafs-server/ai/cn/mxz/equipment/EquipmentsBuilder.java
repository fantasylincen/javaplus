package cn.mxz.equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentsPro;

import com.google.common.collect.Lists;

public class EquipmentsBuilder {

	public EquipmentsPro build(Collection<? extends Equipment> equipments) {

		ArrayList<Equipment> values = Lists.newArrayList(equipments);

		EquipmentsPro.Builder b = EquipmentsPro.newBuilder();

		Comparator<Equipment> c = new Comparator<Equipment>() {

			@Override
			public int compare(Equipment o1, Equipment o2) {
				return o2.getShenJia() - o1.getShenJia();
			}
		};

		Collections.sort(values, c);

		int i = 0;
		
		for (Equipment e : values) {
			if(i++ > 200) {
				break;
			}
			b.addEquipments(new EquipmentBuilder().build(e));
		}

		return b.build();
	}

}
