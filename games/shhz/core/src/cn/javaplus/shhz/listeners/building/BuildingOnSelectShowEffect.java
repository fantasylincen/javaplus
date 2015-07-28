package cn.javaplus.shhz.listeners.building;

import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.components.buildings.BuildingMoveController;
import cn.javaplus.shhz.events.BuildingOnSelectEvent;
import cn.javaplus.shhz.events.Listener;

public class BuildingOnSelectShowEffect implements
		Listener<BuildingOnSelectEvent> {

	@Override
	public void onEvent(BuildingOnSelectEvent e) {
		Building building = e.getBuilding();
		BuildingMoveController c = building.getMoveController();
		c.getMoveListener().enable();
		c.showEffect();
	}

}
