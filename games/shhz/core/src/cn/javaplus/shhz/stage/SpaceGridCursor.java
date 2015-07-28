package cn.javaplus.shhz.stage;

import java.util.List;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.components.Background;
import cn.javaplus.shhz.components.GridManager;
import cn.javaplus.shhz.components.MapGrid;
import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.graphics.Color;

public class SpaceGridCursor {

	private static final boolean IS_DEBUG = D.IS_DEBUG_SPACE_GRID_CURSOR;

	public class Direction {

		private List<VectorInt> vs = Lists.newArrayList();

		private int index;

		public Direction() {
			vs.add(new VectorInt(1, 1));
			vs.add(new VectorInt(-1, 1));
			vs.add(new VectorInt(-1, -1));
			vs.add(new VectorInt(1, -1));
		}

		public int getX() {
			return get().getX();
		}

		public int getY() {
			return get().getY();
		}

		private VectorInt get() {
			return vs.get(index);
		}

		public void turnLeft() {

			index++;
			if (index == 4) {
				index = 0;
			}
		}
	}

	private VectorInt start;
	private List<VectorInt> offests;
	private Building building;
	private Background background;
	private Direction direction;
	private int moveCount = 1;
	private int touchCount;
	private int gridSize;
	private GameStage stage;
	private float green = 1;

	public SpaceGridCursor(VectorInt start, Background b, Building bd) {
		this.start = start;
		this.background = b;
		this.building = bd;
		stage = background.getStage();
		offests = building.getConfig().getPositions();
		direction = new Direction();
		gridSize = background.getGridManager().getGrids().size();
		gridSize += gridSize / 4;
	}

	public VectorInt find() {
		moveStartPointOnGrid();
		ensureToutchOneGrid();
		return findRotate();
	}

	private VectorInt findRotate() {
		while (true) {
			direction.turnLeft();
			for (int i = 0; i < moveCount / 2; i++) {
				moveByDirection(start, direction);
				BuildingGrids bg = getTouchGrids();

				debug(bg.getGrids());

				if (touchCount > gridSize) {
					return null;
				}
				if (canPut(bg)) {
					return start;
				}
				touchCount++;
			}
			moveCount++;
		}
	}

	private void debug(List<MapGrid> grids) {
		if (IS_DEBUG) {
			green -= 0.01f;
			Color color = new Color(0, green, 0, 1);
			for (MapGrid g : grids) {
				if (g != null) {
					g.setColor(color);
					break;
				}
			}
		}
	}

	private boolean canPut(BuildingGrids bg) {
		if (bg.isToutchBound()) {
			return false;
		}
		for (MapGrid g : bg.getGrids()) {
			if (g.isOccupied()) {
				return false;
			}
		}
		return true;
	}

	private BuildingGrids getTouchGrids() {

		BuildingGrids bg = new BuildingGrids();

		GridManager gm = background.getGridManager();
		bg.setGridsSize(this.offests.size());
		for (VectorInt v : this.offests) {
			int stageX = v.getX() + start.getX();
			int stageY = v.getY() + start.getY();
			MapGrid g = gm.getTouchedGrid(stageX, stageY);
			if (g == null) {
				break;
			}
			bg.getGrids().add(g);
		}
		return bg;

	}

	private void moveByDirection(VectorInt p, Direction d) {
		int x = d.getX();
		int y = d.getY();
		p.setX(x * D.MAP_GRID_W_HALF + p.getX());
		p.setY(y * D.MAP_GRID_H_HALF + p.getY());
	}

	private void ensureToutchOneGrid() {
		Direction dir = new Direction();
		for (int i = 0; i < 4; i++) {
			MapGrid g = findGridThisDirection(dir);
			if (g != null) {
				buildVectorInt(g);
				if (IS_DEBUG) {
					g.setColor(Color.RED);
				}
				break;
			}
			dir.turnLeft();
		}
	}

	private MapGrid findGridThisDirection(Direction dir) {
		VectorInt v = new VectorInt(start);
		GridManager gm = background.getGridManager();
		for (int i = 1; i < 20; i++) {
			moveByDirection(v, dir);

			if (IS_DEBUG) {
				stage.addPoint(v);
			}

			MapGrid g = gm.getTouchedGrid(v.getX(), v.getY());
			if (g != null) {
				return g;
			}
		}
		return null;
	}

	private void buildVectorInt(MapGrid g) {
		start = new VectorInt((int) g.getX(), (int) g.getY());
	}

	private void moveStartPointOnGrid() {
		int x = start.getX();
		int y = start.getY();
		VectorInt p = GridManager.getMovePositionToGrid(x, y);
		start.set(p);
		if (IS_DEBUG) {
			stage.addPoint(start);
		}
	}

}
