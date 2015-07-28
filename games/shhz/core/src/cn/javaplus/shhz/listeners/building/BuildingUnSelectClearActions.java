package cn.javaplus.shhz.listeners.building;

import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.events.BuildingUnSelectEvent;
import cn.javaplus.shhz.events.Listener;

import com.badlogic.gdx.graphics.Color;

public class BuildingUnSelectClearActions implements
		Listener<BuildingUnSelectEvent> {

	@Override
	public void onEvent(BuildingUnSelectEvent e) {
		Building building = e.getBuilding();
		building.clearActions();
		building.setColor(Color.WHITE);
	}

}
