package cn.javaplus.shhz.components.buildings;

public class BuildingEditor {

	private BuildingGridEditor gridEditor;

	public BuildingEditor(Building building) {
		gridEditor = new BuildingGridEditor(building);
	}

	public BuildingGridEditor getGridEditor() {
		return gridEditor;
	}
}
