package cn.javaplus.shhz.events;

import cn.javaplus.shhz.components.buildings.Building;

public class BuildingUnSelectEvent {

	private Building building;

	public BuildingUnSelectEvent(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}

}
