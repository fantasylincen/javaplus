package cn.javaplus.shhz.listeners.building;

import cn.javaplus.shhz.actions.BeatAction;
import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.events.BuildingOnSelectEvent;
import cn.javaplus.shhz.events.Listener;

public class BuildingOnSelectShowBeatEffect implements
		Listener<BuildingOnSelectEvent> {

	@Override
	public void onEvent(BuildingOnSelectEvent e) {
		Building building = e.getBuilding();
		building.addAction(new BeatAction());
	}

}
