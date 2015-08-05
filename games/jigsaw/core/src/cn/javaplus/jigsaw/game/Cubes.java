package cn.javaplus.jigsaw.game;

import java.util.List;
import java.util.Map;

import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.Util;

import cn.javaplus.jigsaw.App;
import cn.javaplus.jigsaw.events.Events;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.google.common.collect.Maps;

public class Cubes extends Group {

	int c;
	int cubeWidth = 120;
	float space;
	List<CubeBox> boxes;
	Map<String, CubeBox> boxesMap;
	CubeBox emptyBox;
	long timeStart;
	float widthPix;
	GameStage gameStage;
	boolean isTimerRunning = true;
	long timeUsed;

	public class MoveListener extends InputListener {

		private Cube cube;

		public MoveListener(Cube cube) {
			this.cube = cube;
		}

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			move(cube.getBox());
			return true;
		}

	}

	public Cubes(float widthPix, GameStage gameStage, int c) {
		this.c = c;
		this.widthPix = widthPix;
		this.gameStage = gameStage;
		restart();
	}

	private void restart() {
		clear();
		setSize(widthPix, widthPix);
		createBoxes();
		addCubeToBoxes();
		upset();
		timeStart = System.currentTimeMillis();
	}

	private void createBoxes() {
		boxes = Lists.newArrayList();
		boxesMap = Maps.newHashMap();
		space = (widthPix - c * cubeWidth) / (c + 1);
		float y = space + (c - 1) * (space + cubeWidth);
		for (int rowIndex = c - 1; rowIndex >= 0; rowIndex--) {
			addRow(rowIndex, y);
			y -= space + cubeWidth;
		}
	}

	private void upset() {
		for (int i = 0; i < 50; i++) {
			List<CubeBox> nears = getNears();
			swapEmpty(Util.Collection.getRandomOne(nears));
		}
	}

	private List<CubeBox> getNears() {
		List<CubeBox> ls = Lists.newArrayList();
		int r = emptyBox.getRowIndex();
		int c = emptyBox.getColIndex();

		add(ls, r - 1, c);
		add(ls, r + 1, c);
		add(ls, r, c - 1);
		add(ls, r, c + 1);

		return ls;
	}

	private void add(List<CubeBox> ls, int r, int c) {
		String k = key(r, c);
		CubeBox b = boxesMap.get(k);
		if (b != null) {
			ls.add(b);
		}
	}

	public void move(CubeBox box) {
		if (isNearEmpty(box)) {
			swapEmpty(box);
			if (isOver()) {
				gameOver();
				System.out.println("Cubes gameover");
			}
		}
	}

	private void swapEmpty(CubeBox box) {
		emptyBox.swap(box);
		emptyBox = box;
	}

	private boolean isNearEmpty(CubeBox box) {
		int r = emptyBox.getRowIndex();
		int c = emptyBox.getColIndex();
		int rb = box.getRowIndex();
		int cb = box.getColIndex();

		if (cb == c && abs(rb, r) == 1) {
			return true;
		}

		if (rb == r && abs(cb, c) == 1) {
			return true;
		}

		return false;
	}

	private int abs(int c1, int c2) {
		return Math.abs(c2 - c1);
	}

	private void gameOver() {
		Events.dispatch(new GameOverEvent(this, gameStage, getTime()));
		stopTimer();
		saveBestScore();
	}

	private void saveBestScore() {
		long time = getTime();
		if (time != 0) {
			long best = App.getPreferences().getLong("best-score-" + c);
			if (best == 0 || time < best) {
				App.getPreferences().put("best-score-" + c, time);
			}
		}
	}

	private void stopTimer() {
		timeUsed = getTime();
		this.isTimerRunning = false;
	}

	public long getTime() {
		if (isTimerRunning) {
			return System.currentTimeMillis() - timeStart;
		} else {
			return timeUsed;
		}
	}

	private boolean isOver() {
		for (int i = 0; i < boxes.size() - 2; i++) {
			Cube a = get(i);
			Cube b = get(i + 1);
			if (b == null || a == null) {
				return false;
			}
			if (b.getNumber() - a.getNumber() != 1) {
				return false;
			}
		}
		return true;
	}

	private Cube get(int index) {
		CubeBox box = boxes.get(index);
		return box.getCube();
	}

	private void addCubeToBoxes() {
		for (int i = 0; i < c * c - 1; i++) {
			CubeBox b = boxes.get(i);
			int number = i + 1;
			Cube cube = new Cube(number, cubeWidth);
			cube.addListener(new MoveListener(cube));
			b.put(cube);
			addActor(cube);
		}
		emptyBox = Util.Collection.getLast(boxes);
	}

	private void addRow(int rowIndex, float y) {
		float x = space;
		for (int colIndex = 0; colIndex < c; colIndex++) {
			addCubeBox(rowIndex, colIndex, x, y);
			x += space + cubeWidth;
		}
	}

	private void addCubeBox(int rowIndex, int colIndex, float x, float y) {
		CubeBox b = new CubeBox(rowIndex, colIndex, x, y);
		boxes.add(b);
		boxesMap.put(key(rowIndex, colIndex), b);
	}

	private String key(int rowIndex, int colIndex) {
		return rowIndex + "," + colIndex;
	}

	public int getC() {
		return c;
	}
}
