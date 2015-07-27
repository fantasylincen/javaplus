package cn.javaplus.shhz.stage;

import java.util.ArrayList;

import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.components.MapGrid;

public class BuildingGrids {
	private ArrayList<MapGrid> grids;
	private int size;
	public BuildingGrids() {
		grids = Lists.newArrayList();
	}
	
	public ArrayList<MapGrid> getGrids() {
		return grids;
	}

	public void setGridsSize(int size) {
		this.size = size;
	}

	public boolean isToutchBound() {
		return grids.size() < size;
	}
	
}
