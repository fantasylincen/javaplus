package cn.javaplus.shhz.stage;

import java.util.Iterator;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.game.shhz.R;
import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.components.Background;
import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.components.info.InfomationPanel;
import cn.javaplus.shhz.events.building.NotEmptySpaceForBuildingEvent;
import cn.javaplus.shhz.events.stage.GameStageTouchDownEvent;
import cn.javaplus.shhz.events.stage.OnSelectEvent;
import cn.javaplus.shhz.events.stage.UnSelectEvent;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class GameStage extends Stage {

	private MapObject selected;
	private Group buildingGroup;
	private Group debugGroup;

	public GameStage() {
		super(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), new OrthographicCamera()), null);

		createBuildingGroup();
	}

	private void createBuildingGroup() {
		buildingGroup = new Group();
		addActor(buildingGroup);
		if (D.IS_STAGE_POINT_ENABLE) {
			debugGroup = new Group();
			addActor(debugGroup);
			debugGroup.toFront();
		}
	}

	@Override
	public OrthographicCamera getCamera() {
		return (OrthographicCamera) super.getCamera();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean touchDown = super.touchDown(screenX, screenY, pointer, button);

		GameStageTouchDownEvent e = new GameStageTouchDownEvent(this, screenX,
				screenY, pointer, button);
		Events.dispatch(e);
		return touchDown;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public MapObject getSelected() {
		return selected;
	}

	public void select(MapObject selected) {
		if (this.selected != null) {
			Events.dispatch(new UnSelectEvent(this.selected));
		}

		this.selected = selected;

		if (this.selected != null) {
			Events.dispatch(new OnSelectEvent(this.selected));
		}
	}

	public void cancelSelected() {
		select(null);
	}

	public void addBuilding(Building b) {
		VectorInt pos = findEmptyPosition(b);
		if (pos == null) {
			Events.dispatch(new NotEmptySpaceForBuildingEvent(b));
			return;
		}
		buildingGroup.addActor(b);
		b.setPosition(pos.getX(), pos.getY());
		select(b);
	}

	private VectorInt findEmptyPosition(Building b) {
		int x = Gdx.graphics.getWidth();
		int y = Gdx.graphics.getHeight();

		x /= 2;
		y /= 2;

		x -= b.getWidth() / 2;
		y += b.getHeight() / 2;

		VectorInt v = new VectorInt(x, y);

		VectorInt start = screenToStageCoordinates(v);

		SpaceGridCursor cursor = new SpaceGridCursor(start, getBackground(), b);
		return cursor.find();
	}

	public VectorInt screenToStageCoordinates(VectorInt screenCoords) {
		Vector2 vv = VectorInt.parse(screenCoords);
		Vector2 v = super.screenToStageCoordinates(vv);
		return VectorInt.parse(v);
	}

	public boolean isSelect(MapObject obj) {
		return this.selected == obj;
	}

	private void setChildSingle(Actor c) {
		Array<Actor> actors = getActors();
		Iterator<Actor> it = actors.iterator();
		while (it.hasNext()) {
			Actor a = it.next();
			Class<? extends Actor> cc = c.getClass();
			if (cc.isInstance(a)) {
				it.remove();
			}
		}
		addActor(c);
	}

	@SuppressWarnings("unchecked")
	private <T extends Actor> T findChildSingle(Class<T> class1) {
		Array<Actor> actors = getActors();
		Iterator<Actor> it = actors.iterator();
		while (it.hasNext()) {
			Actor a = it.next();
			Class<? extends Actor> class2 = a.getClass();
			if (class2.equals(class1)) {
				return (T) a;
			}
		}
		return null;
	}

	public void setBackground(Background background) {
		setChildSingle(background);
	}

	public Background getBackground() {
		return findChildSingle(Background.class);
	}

	public void setInfomationPanel(InfomationPanel c) {
		setChildSingle(c);
	}

	public InfomationPanel getInfomationPanel() {
		return findChildSingle(InfomationPanel.class);
	}

	public void addPoint(VectorInt point) {
		if (D.IS_STAGE_POINT_ENABLE) {
			Image a = Assets.createImage(R.PointPng);
			a.addAction(getPointAction());
			Touchable touchable = Touchable.disabled;
			a.setTouchable(touchable);
			a.setPosition(point.getX() - a.getWidth() / 2,
					point.getY() - a.getHeight() / 2);
			debugGroup.addActor(a);
		}
	}

	private Action getPointAction() {
		RepeatAction pointAction = new RepeatAction();

		ColorAction c = new ColorAction();
		SequenceAction action = new SequenceAction();

		c.setEndColor(Color.RED);
		c.setDuration(0.1f);
		action.addAction(c);

		c = new ColorAction();
		c.setEndColor(Color.WHITE);
		c.setDuration(0.1f);
		action.addAction(c);

		pointAction.setAction(action);
		pointAction.setCount(RepeatAction.FOREVER);
		return pointAction;
	}

	public void addPoint(int x, int y) {
		addPoint(new VectorInt(x, y));
	}
}
