package cn.javaplus.shhz.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.log.Log;
import cn.javaplus.shhz.stage.GameStage;
import cn.javaplus.shhz.util.GridCoordinate;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.math.Vector2;

public class GridManager {

	private ArrayList<MapGrid> grids;

	/**
	 * <gridIndexX:gridIndexY, MapGrid>
	 */
	private Map<String, MapGrid> gridIndexMap;

	/**
	 * <stageX:stageY, MapGrid>
	 */
	private Map<String, MapGrid> gridPositionMap;
	static GridCoordinate gridCoordinate;

	public GridManager(Background background) {
		createMapGrids();
		if (D.IS_GRID_POSITION_DEBUGER_ENABLE) {
			GameStage stage = background.getStage();
			stage.addListener(new GridDebuger(stage, this));
		}
		initGridCordinate();
	}

	private void initGridCordinate() {
		if (gridCoordinate != null) {
			return;
		}
		gridCoordinate = new GridCoordinate();
	}

	public List<MapGrid> getGrids() {
		return grids;
	}

	/**
	 * grid index on grid coordinate
	 * 
	 * @param stageX
	 * @param stageY
	 * @return
	 */
	public static VectorInt getGridIndex(int stageX, int stageY) {
		Vector2 translate = gridCoordinate.translate(stageX, stageY);
		if (translate.y < 0) {
			translate.y -= SIDE_LEN;
		}
		if (translate.x < 0) {
			translate.x -= SIDE_LEN;
		}
		int x = (int) (translate.x / SIDE_LEN);
		int y = (int) (translate.y / SIDE_LEN);
		return new VectorInt(x, y);
	}

	/**
	 * grid position on grid coordinate
	 * 
	 * @param stageX
	 * @param stageY
	 * @return
	 */
	public static VectorInt getGridCoordinate(int stageX, int stageY) {
		Vector2 translate = gridCoordinate.translate(stageX, stageY);
		return VectorInt.parse(translate);
	}

	/**
	 * grid position on stage coordinate
	 * 
	 * @param stageX
	 * @param stageY
	 * @return
	 */
	public static VectorInt getGridPositionOnStage(int stageX, int stageY) {
		VectorInt p = getGridIndex(stageX, stageY);
		int startX = D.MAP_GRID_X_START * D.MAP_GRID_W;
		int startY = D.MAP_GRID_Y_START * D.MAP_GRID_H;
		int dx = D.MAP_GRID_W_HALF * p.getX();
		int dy = D.MAP_GRID_H_HALF * p.getY() + D.MAP_GRID_H_HALF * p.getX();
		return new VectorInt(startX + dx, startY + dy);
	}

	private static final float SIDE_LEN = (float) getSideLen();

	private static double getSideLen() {
		int aa = D.MAP_GRID_H_HALF * D.MAP_GRID_H_HALF;
		int bb = D.MAP_GRID_W_HALF * D.MAP_GRID_W_HALF;
		return Math.sqrt(aa + bb);
	}

	private void createMapGrids() {
		grids = Lists.newArrayList();
		gridIndexMap = new HashMap<String, MapGrid>();
		gridPositionMap = new HashMap<String, MapGrid>();

		for (int i = 0; i < D.MAP_GRID_LEFT_TO_DOWN_SIZE; i++) {
			fillLine(i);
		}
	}

	private void fillLine(int y) {
		int w = D.MAP_GRID_W;
		int aa = D.MAP_GRID_X_START * w;
		int bb = y * D.MAP_GRID_W_HALF;
		int h = D.MAP_GRID_H;
		int cc = D.MAP_GRID_Y_START * h;
		int dd = y * D.MAP_GRID_H_HALF;

		float xStart = aa - bb;
		float yStart = cc + dd;
		for (int x = 0; x < D.MAP_GRID_DOWN_TO_RIGHT_SIZE; x++) {
			MapGrid m = new MapGrid(Background.tieldGrid, x, y, xStart, yStart,
					w, h);
			add(m);
			xStart += D.MAP_GRID_W_HALF;
			yStart += D.MAP_GRID_H_HALF;
		}
	}

	private void add(MapGrid m) {
		gridIndexMap.put(m.getXIndex() + ":" + m.getYIndex(), m);
		int x = (int) m.getX();
		int y = (int) m.getY();
		gridPositionMap.put(x + ":" + y, m);
		Log.d("GridManager.add ", x, y);
		grids.add(m);
	}

	public MapGrid getTouchedGrid(int stageX, int stageY) {
		return gridPositionMap.get(stageX + ":" + stageY);
	}

	public Map<String, MapGrid> getGridIndexMap() {
		return gridIndexMap;
	}

	public Map<String, MapGrid> getGridPositionMap() {
		return gridPositionMap;
	}

	public static VectorInt getMovePositionToGrid(int stageX, int stageY) {
		int a = stageX / D.MAP_GRID_W;
		int b = stageY / D.MAP_GRID_H_HALF;
		stageX = a * D.MAP_GRID_W;
		stageY = b * D.MAP_GRID_H_HALF;

		if (b % 2 == 1) {
			stageX += D.MAP_GRID_W_HALF;
		}

		return new VectorInt(stageX, stageY);
	}

}