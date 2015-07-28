package cn.javaplus.shhz.events;

import cn.javaplus.shhz.components.buildings.Building;

public class BuildingOnSelectEvent {

	private Building building;

	public BuildingOnSelectEvent(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}
}
