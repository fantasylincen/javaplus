package cn.javaplus.shhz.events.building;

import cn.javaplus.shhz.components.buildings.Building;

public class NotEmptySpaceForBuildingEvent {

	private Building building;

	public NotEmptySpaceForBuildingEvent(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}
}
