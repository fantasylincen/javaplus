package cn.javaplus.shhz.components.buildings;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.game.shhz.R.TextureShape;
import cn.javaplus.shhz.components.GridManager;
import cn.javaplus.shhz.components.PolygonImage;
import cn.javaplus.shhz.events.building.BuildingMovedEvent;
import cn.javaplus.shhz.listeners.stage.Menu;
import cn.javaplus.shhz.stage.GameStage;
import cn.javaplus.shhz.stage.MapObject;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Building extends PolygonImage implements MapObject {

	private BuidingGridsConfig config;
	private BuildingMoveController controller;
	private OccupiedGrids occupiedGrids;
	private BuildingEditor editor;
	private HasTouchDownListener touchDownListener;

	/**
	 * 
	 * @param shape
	 *            from R.TextureShapes.xxx
	 */
	public Building(TextureShape shape) {
		super(shape);
		occupiedGrids = new OccupiedGrids(this);
		config = new BuidingGridsConfig(this);
		controller = new BuildingMoveController(this);
		editor = new BuildingEditor(this);
		touchDownListener = new HasTouchDownListener();
		addListener(touchDownListener);
	}

	public String getPng() {
		return shape.getTexturePath();
	}

	public BuidingGridsConfig getConfig() {
		return config;
	}

	public BuildingMoveController getMoveController() {
		return controller;
	}

	public OccupiedGrids getOccupiedGrids() {
		return occupiedGrids;
	}

	public BuildingEditor getEditor() {
		return editor;
	}

	@Override
	public GameStage getStage() {
		return (GameStage) super.getStage();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		getOccupiedGrids().updateColor();
	}

	@Override
	public void setPosition(float xx, float yy) {

		int xOld = (int) getX();
		int yOld = (int) getY();

		VectorInt p = GridManager.getMovePositionToGrid((int) xx, (int) yy);

		super.setPosition(p.getX(), p.getY());

		int xNew = (int) getX();
		int yNew = (int) getY();

		boolean isXyChanged = xOld != xNew || yOld != yNew;
		if (isXyChanged) {
			getOccupiedGrids().occupied(xNew, yNew);
			Events.dispatch(new BuildingMovedEvent(this));
		}
	}

	public boolean isSelected() {
		return getStage().getSelected() == this;
	}

	@Override
	public boolean hasTouchDown() {
		return touchDownListener.isTouchDown();
	}

	@Override
	public Menu createMenu() {
		// TODO 自动生成的方法存根
		return null;
	}

}
