package cn.mxz.fighter;

import java.util.List;

import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentBuilder;
import cn.mxz.protocols.user.god.TeamP.TeamGrid;


public class TeamGridBuilder {

	public TeamGrid build(TGrid t) {

		TeamGrid.Builder b = TeamGrid.newBuilder();

		List<Equipment> ls = t.getEquipments();

		for (Equipment equipment : ls) {

			b.addEquipments(new EquipmentBuilder().build(equipment));
		}

		b.setFighterId(t.getFighterId()	);

		b.setIsOpen(t.isOpen());

		return b.build();
	}

}
