package cn.javaplus.shhz.events.building;

import cn.javaplus.shhz.components.buildings.Building;

public class BuildingOutBoundEvent {

	private Building building;

	public BuildingOutBoundEvent(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}

}
