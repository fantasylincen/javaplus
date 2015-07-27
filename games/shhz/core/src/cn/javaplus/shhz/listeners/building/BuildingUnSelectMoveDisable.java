package cn.javaplus.shhz.listeners.building;

import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.components.buildings.BuildingMoveController;
import cn.javaplus.shhz.events.BuildingUnSelectEvent;
import cn.javaplus.shhz.events.Listener;

public class BuildingUnSelectMoveDisable implements
		Listener<BuildingUnSelectEvent> {

	@Override
	public void onEvent(BuildingUnSelectEvent e) {
		Building building = e.getBuilding();
		BuildingMoveController c = building.getMoveController();
		c.getMoveListener().disable();
	}

}
