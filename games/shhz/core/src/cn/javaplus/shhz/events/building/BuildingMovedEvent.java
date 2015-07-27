package cn.javaplus.shhz.events.building;

import cn.javaplus.shhz.components.buildings.Building;

public class BuildingMovedEvent {

	private Building building;

	public BuildingMovedEvent(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}
}
