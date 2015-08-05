package cn.javaplus.game.power.stage;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.game.power.events.GameOverEvent;
import cn.javaplus.game.power.events.MoveEvent;
import cn.javaplus.util.Util;
import cn.mxz.events.Events;

import com.google.common.collect.Lists;

public class GameModel {

//	private static final int W = 4;
//	private int[][] values = new int[W][W];
	private static final int W = 14;
	private int[][] values = new int[][]{
//			{0,0,0,0},
//			{0,0,0,0},
//			{0,0,0,0},
//			{0,1024,2048,4096},

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1024,2048,4096,0,0,0,0,0,0,0,0,0,0},
	};

	public GameModel() {
		randomOneOnSpace();
		randomOneOnSpace();
	}

	public void up() {
		move(1, 3);
	}

	public void down() {
		move(3, 1);
	}

	public void left() {
		move(0, 0);
	}

	public void right() {
		move(2, 2);
	}

	private void move(int t1, int t2) {
		rotate90(t1);
		push();
		rotate90(t2);
		Events.getInstance().dispatch(new MoveEvent(this, values));
	}

	/**
	 * 空白处随机一个
	 */
	private void randomOneOnSpace() {
		List<Grid> grids = getGrids(true);
		Grid g = Util.Random.getRandomOne(grids);
		values[g.getYIndex()][g.getXIndex()] = Util.Random.get(1, 2) * 2;
	}

	public List<Grid> getGrids(boolean isEmpty) {
		List<Grid> grids = getGrids();
		Iterator<Grid> it = grids.iterator();
		while (it.hasNext()) {
			Grid grid = (Grid) it.next();
			boolean isEmt = grid.getV() == 0;
			if (isEmt != isEmpty) {
				it.remove();
			}
		}
		return grids;
	}

	public List<Grid> getGrids() {
		List<Grid> ls = Lists.newArrayList();

		for (int yIndex = 0; yIndex < values.length; yIndex++) {
			int[] rows = values[yIndex];
			for (int xIndex = 0; xIndex < rows.length; xIndex++) {
				int v = rows[xIndex];
				ls.add(new Grid(xIndex, yIndex, v));
			}
		}
		return ls;
	}

	private boolean isFull() {
		return getGrids(true).isEmpty();
	}

	private void push() {
		int[][] vs = Util.Array.copy(values);
		for (int i = 0; i < values.length; i++) {
			values[i] = push(values[i]);
		}

		boolean hasChange = Util.Array.equals(vs, values);
		if (hasChange) {
			randomOneOnSpace();
		} else if (isFull()) {
			Events.getInstance().dispatch(new GameOverEvent(this));
		}
	}

	private int[] push(int[] is) {
		is = closeUp(is);
		for (int i = 0; i < is.length - 1; i++) {
			if (is[i] == is[i + 1]) {
				is[i] *= 2;
				is[i + 1] = 0;
				is = closeUp(is);
			}
		}
		return is;
	}

	/**
	 * 靠拢
	 */
	private int[] closeUp(int[] is) {
		List<Integer> ls = Lists.newArrayList();
		int[] vs = new int[is.length];
		for (int i : is) {
			if (i != 0) {
				ls.add(i);
			}
		}
		int i = 0;
		for (int v : ls) {
			vs[i++] = v;
		}
		return vs;
	}

	private void rotate90(int times) {
		for (int i = 0; i < times; i++) {
			rotate90();
		}
	}

	private void rotate90() {
		int[][] vss = new int[W][W];
		for (int i = 0; i < values.length; i++) {
			int[] vs = values[i];
			for (int j = 0; j < vs.length; j++) {
				vss[vs.length - 1 - j][i] = vs[j];
			}
		}
		values = vss;
	}

	public int getWidth() {
		return W;
	}
}