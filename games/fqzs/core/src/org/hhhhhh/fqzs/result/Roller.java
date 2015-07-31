package org.hhhhhh.fqzs.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hhhhhh.fqzs.core.GameStage.Light;
import org.hhhhhh.fqzs.result.PlayResult.Result;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Roller extends Actor {

//	public class RollAction {
//
//		private Result result;
//		private SpaceDefine space;
//		int index;
//
//		public RollAction(Result result, SpaceDefine space) {
//			this.result = result;
//			this.space = space;
//		}
//
//		public Result getResult() {
//			return result;
//		}
//
//		public SpaceDefine getSpace() {
//			return space;
//		}
//
//		public void doAction(float delta) {
//			if (timeUp()) {
//				rollToNext();
//				index++;
//			}
//			if (isOver())
//				time = 0;
//		}
//
//		private void rollToNext() {
//			// re
//		}
//
//		private boolean timeUp() {
//			float secAll = 0;
//			for (int i = 0; i < index; i++) {
//				Integer mi = space.getSpaces().get(i);
//				float sec = mi / 1000f;
//				secAll += sec;
//			}
//			return time >= secAll;
//		}
//
//		public boolean isOver() {
//			return index >= space.getSpaces().size();
//		}
//
//	}

	private List<RollerLitener> listeners = Lists.newArrayList();
	private PlayResult playResult;
	private List<SpaceDefine> spaces;
//	private ArrayList<RollAction> rollQueue;
	// private boolean isStart;
	private int time;
	private Map<String, Light> lights;

	public Roller(PlayResult playResult, Map<String, Light> lights) {
		this.playResult = playResult;
		this.lights = lights;
		spaces = loadSpaces();
//		rollQueue = Lists.newArrayList();

		addActions();
	}

	private void addActions() {
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

	private List<SpaceDefine> loadSpaces() {
		FileHandle file = Assets.getDefaultLoader().getFile("data/rollSpace.txt");
		String readString = file.readString();
		String[] split = readString.split("\r");
		ArrayList<SpaceDefine> ls = Lists.newArrayList();
		for (String line : split) {
			if (line.trim().isEmpty()) {
				continue;
			}
			ls.add(new SpaceDefine(line));
		}
		return ls;
	}

	public void addListener(RollerLitener l) {
		listeners.add(l);
	}

	public void roll() {
		// List<Result> results = playResult.getResults();
		// for (int i = 0; i < results.size(); i++) {
		// Result result = results.get(i);
		// SpaceDefine space = spaces.get(i);
		// addToRollQueue(result, space);
		// }
		// this.isStart = true;
		// Log.d("start roll");

		addAction(actions);
	}

//	private void addToRollQueue(Result result, SpaceDefine space) {
//		RollAction action = new RollAction(result, space);
//		this.rollQueue.add(action);
//	}

	// public void update(float delta) {
	// if (!isStart) {
	// this.time = 0;
	// return;
	// }
	//
	// this.time += delta;
	//
	// RollAction action = getCurrentAction();
	// if (action == null) {
	// isStart = false;
	// for (RollerLitener l : listeners) {
	// l.onRollEnd(new RollEndEvent(this));
	// }
	// return;
	// }
	//
	// action.doAction(delta);
	// }
//
//	private RollAction getCurrentAction() {
//		for (RollAction action : rollQueue) {
//			if (!action.isOver()) {
//				return action;
//			}
//		}
//		return null;
//	}
//
//	private static float ACTION_SPACE_SEC = 0.2f;
	private SequenceAction actions;

}
