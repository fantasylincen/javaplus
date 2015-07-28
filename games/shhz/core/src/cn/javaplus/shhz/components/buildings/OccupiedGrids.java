package cn.javaplus.shhz.components.buildings;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.components.MapGrid;
import cn.javaplus.shhz.events.building.BuildingOutBoundEvent;
import cn.javaplus.shhz.events.building.BuildingTouchOccupiedEvent;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.graphics.Color;

public class OccupiedGrids implements Iterable<MapGrid> {

	private Building building;
	private List<MapGrid> occupiedGrids = Lists.newArrayList();

	public OccupiedGrids(Building building) {
		this.building = building;
	}

	public boolean remove(Object o) {
		return occupiedGrids.remove(o);
	}

	public void add(MapGrid g) {
		occupiedGrids.add(g);
	}

	@Override
	public Iterator<MapGrid> iterator() {
		return occupiedGrids.iterator();
	}

	public boolean contains(Object o) {
		return occupiedGrids.contains(o);
	}

	public void occupied(int xx, int yy) {
		occupiedGrids(xx, yy);
	}

	private void occupiedGrids(int xx, int yy) {
		unOccupiedOld();
		occupied();
	}

	private void occupied() {

//		GameStage stage = building.getStage();
//		Background bg = stage.getBackground();
//		Map<String, MapGrid> grids = bg.getGridManager().getGridPositionMap();
		
		List<MapGrid> gs = building.getEditor().getGridEditor().getSelectGrids();
		
		
		
		List<VectorInt> gridPositions = building.getConfig().getPositions();
		boolean isOutBound = false;
		boolean isTouchOccupied = false;
//		for (VectorInt d : gridPositions) {
//			int x =  (int) building.getX();
//			int y =  (int) building.getY();
//			int gx =  (x + d.getX());
//			int gy =  (y + d.getX());
//
//			String key = key(gx, gy);
//			MapGrid g = grids.get(key);
//
//			if (g == null) {
//				isOutBound = true;
//				continue;
//			}
//			if (!g.isOccupied()) {
//				occupiedGrids.add(g);
//				g.occupied();
//			} else {
//				isTouchOccupied = true;
//			}
//		}
		
		isOutBound = gs.size() < gridPositions.size();
		for (MapGrid g : gs) {
			if (!g.isOccupied()) {
				occupiedGrids.add(g);
				g.occupied();
			} else {
				isTouchOccupied = true;
			}
		}

		BuildingMoveController c = building.getMoveController();

		c.setStandable(true);
		if (isOutBound) {
			c.setStandable(false);
			Events.dispatch(new BuildingOutBoundEvent(building));
		}

		if (isTouchOccupied) {
			c.setStandable(false);
			Events.dispatch(new BuildingTouchOccupiedEvent(building));
		}
		c.updateStandStatus();
	}

	private void unOccupiedOld() {
		for (MapGrid g : this.occupiedGrids) {
			g.unOccupied();
		}
		occupiedGrids.clear();
	}
//
//	private String key(int stageX, int stageY) {
//		return stageX + ":" + stageY;
//	}

	private void setColor(Color c) {
		for (MapGrid g : occupiedGrids) {
			g.setColor(c);
		}
	}

	public void updateColor() {
		BuildingGridEditor ge = building.getEditor().getGridEditor();
		if(ge.isEnable()) {
			return;
		}
		if (!building.isSelected()) {
			setColor(Color.WHITE);
			return;
		}

		BuildingMoveController c = building.getMoveController();
		if (c.isStandable()) {
			setColor(Color.GREEN);
		} else {
			setColor(Color.RED);
		}
	}
}
