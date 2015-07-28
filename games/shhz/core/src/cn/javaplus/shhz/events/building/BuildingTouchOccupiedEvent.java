package cn.javaplus.shhz.events.building;

import cn.javaplus.shhz.components.buildings.Building;

public class BuildingTouchOccupiedEvent {

	private Building building;

	public BuildingTouchOccupiedEvent(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}

}
