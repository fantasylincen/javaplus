package cn.javaplus.shhz.components.buildings;

import java.util.List;
import java.util.Map;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.components.Background;
import cn.javaplus.shhz.components.GridManager;
import cn.javaplus.shhz.components.MapGrid;
import cn.javaplus.shhz.stage.GameStage;
import cn.javaplus.shhz.util.Util;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class BuildingGridEditor implements Switch {

	public class EditListener extends ActorGestureListener {

		public EditListener(Building building) {
		}

		@Override
		public void tap(InputEvent event, float x, float y, int count,
				int button) {
			if (!D.IS_EDITOR_ENABLE) {
				return;
			}
			if (isEnable()) {
				switchMapGrid((int) x, (int) y);
			}
		}
	}

	private boolean isEnable;
	private Building building;

	public BuildingGridEditor(Building building) {

		this.building = building;
		building.addListener(new EditorSwitch(this));
		building.addListener(new EditListener(building));
	}

	public Building getBuilding() {
		return building;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void disable() {
		this.isEnable = false;
		building.clearActions();
		building.getColor().a = 1;
		building.getMoveController().enable();
		building.toPolygonBound();
	}

	public void enable() {
		this.isEnable = true;
		building.clearActions();

		GameStage stage = building.getStage();

		stage.cancelSelected();
		building.getMoveController().disable();
		building.getColor().a = 0.5f;
		building.toRectBound();
		
		showGridColor();
	}

	private void showGridColor() {
		List<MapGrid> selectGrids = getSelectGrids();
		for (MapGrid mapGrid : selectGrids) {
			mapGrid.setColor(Color.GREEN);
		}
	}

	public void switchMapGrid(int x, int y) {

		float bx = building.getX();
		float by = building.getY();

		int pointXOnStage = (int) (x + bx);
		int pointYOnStage = (int) (y + by);

		MapGrid g = getGrid(pointXOnStage, pointYOnStage);
		if (g == null) {
			return;
		}

		int gridX = (int) g.getX();
		int gridY = (int) g.getY();

		int dx = (int) (gridX - bx);
		int dy = (int) (gridY - by);

//		Log.d("BuildingGridEditor.switchMapGrid dxy", dx, dy, " gridxy ", gridX, gridY , "bxy" , bx, by);
		BuidingGridsConfig config = building.getConfig();
		config.changeStatus(dx, dy);
		updateGridStatus(g);
		config.save();
	}

	private void updateGridStatus(MapGrid g) {
		OccupiedGrids occupiedGrids = building.getOccupiedGrids();
		if (occupiedGrids.contains(g)) {
			occupiedGrids.remove(g);
			g.unOccupied();
		} else {
			occupiedGrids.add(g);
			g.occupied();
		}
	}

	private MapGrid getGrid(int stageX, int stageY) {
		GameStage stage = building.getStage();
		Background background = stage.getBackground();
		GridManager gm = background.getGridManager();
		
		List<MapGrid> gs = gm.getGrids();
		for (MapGrid g : gs) {
			if (containsInRhombus(g, stageX, stageY)) {
				return g;
			}
		}
		return null;
		
//		return gm.getGridPositionMap().get(stageX + ":" + stageY);
	}

	private boolean containsInRhombus(MapGrid g, int stageX, int stageY) {
		return Util.Shape.Rhombus.isIn(stageX, stageY, g);
	}

	public List<MapGrid> getSelectGrids() {

		GameStage stage = building.getStage();
		Background bg = stage.getBackground();
		Map<String, MapGrid> grids = bg.getGridManager().getGridPositionMap();

		List<VectorInt> gridPositions = building.getConfig().getPositions();
		List<MapGrid> list = Lists.newArrayList();

		for (VectorInt d : gridPositions) {
			int x = (int) building.getX();
			int y = (int) building.getY();
			int gx = (x + d.getX());
			int gy = (y + d.getY());

			String key = key(gx, gy);
//			Log.d("BuildingGridEditor.getSelectGrids " , gx, gy, "dxy", d, "bxy" , x, y);
			MapGrid g = grids.get(key);

			if (g != null) {
				list.add(g);
			}
		}
		return list;
	}

	private String key(int stageX, int stageY) {
		return stageX + ":" + stageY;
	}

	public boolean isSelect(OccupiedGrids occupiedGrids) {
		List<MapGrid> selectGrids = getSelectGrids();
		boolean contains = selectGrids.contains(occupiedGrids);
		return contains;
	}

}
