package org.javaplus.game.common.util;

import java.util.ArrayList;

import org.javaplus.game.common.util.Lists;

public class Space {

	private double space;
	private long times;
	private ArrayList<Runnable> actions;
	private double time;

	/**
	 * @param space 秒
	 */
	public Space(double space) {
		this.space = space;
		actions = Lists.newArrayList();
	}

	public double getSpace() {
		return space;
	}

	public void setSpace(double space) {
		this.space = space;
	}
	
	public void update(float delta) {
		if (needDo(delta)) {
			for (Runnable a : actions) {
				a.run();
			}
			times++;
		}
		time += delta;
	}

	private boolean needDo(float delta) {
		return time / space >= times;
	}

	public void addAction(Runnable action) {
		actions.add(action);
	}

}
