package org.hhhhhh.fqzs.result;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.core.GameStage.Light;
import org.hhhhhh.fqzs.result.PlayResult.Result;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Roller extends Image {


	private List<RollerLitener> listeners = Lists.newArrayList();

	private int time;

	private PlayResult playResult;

	private Map<String, Light> lights;

	Light current;

	public Roller() {
		super(getRegion());
		loadSpaces();
	}

	private static AtlasRegion getRegion() {
		TextureAtlas altas = Assets.getDefaultLoader().getTextureAtlas("data/game.txt");
		return altas.findRegion("qx");
	}

	private void loadSpaces() {

		if (FIRST_ROLL_SPACES_A != null)
			return;

		FIRST_ROLL_SPACES_A = get("firstRollSpacesA");
		FIRST_ROLL_SPACES_B = get( "firstRollSpacesB");
		FIRST_ROLL_SPACES_C = get( "firstRollSpacesC");

		NEXT_ROLL_SPACE = new Integer(App.getProperties().get("nextRollSpace"));
	}

	private SpaceDefine get(String string) {
		String property = App.getProperties().get(string);
		SpaceDefine spaceDefine = new SpaceDefine(property);
		return spaceDefine;
	}

	private void addActions() {
		
		clearActions();
		List<Result> rs = playResult.getResults();
		Iterator<Result> it = rs.iterator();

		Result first = it.next();

		actions = new SequenceAction();

		actions.addAction(new RollFirstAction(first, this));
		actions.addAction(new PlayResultAction(first));

		while (it.hasNext()) {
			Result next = it.next();
			actions.addAction(new RollNextAction(next, this));
			actions.addAction(new PlayResultAction(next));
		}
		actions.addAction(new EndAction(this));
	}

	public void addListener(RollerLitener l) {
		listeners.add(l);
	}

	public void rollOnce() {
		Light c = getCurrent();
		this.current = c.getNext();
		
		float x = getCurrent().getX();
		float y = getCurrent().getY();
		setPosition(x, y);
	}

	public void startRoll(PlayResult playResult, Map<String, Light> lights) {
		this.playResult = playResult;
		this.lights = lights;
		addActions();
		addAction(actions);
	}

	private SequenceAction actions;
	static int NEXT_ROLL_SPACE;
	static SpaceDefine FIRST_ROLL_SPACES_A;
	static SpaceDefine FIRST_ROLL_SPACES_B;
	static SpaceDefine FIRST_ROLL_SPACES_C;

	public Light getCurrent() {
		if (current == null)
			current = lights.get("1");
		return current;
	}

	public int getSpace(String id) {

		Light from = getCurrent();
		Light to = lights.get(id);
		
		if(to == null)
			throw new NullPointerException();

		Light cursor = from;
		int times = 0;
		while (true) {
			if (cursor == to) {
				return times;
			}
			times++;
			cursor = cursor.getNext();
		}
	}

	public void onRollEnd() {
		RollEndEvent e = new RollEndEvent(this);
		for (RollerLitener l : listeners) {
			l.onRollEnd(e);
		}
		Log.d("roll end");
	}

}
