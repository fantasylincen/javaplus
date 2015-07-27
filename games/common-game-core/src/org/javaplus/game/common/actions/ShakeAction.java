package org.javaplus.game.common.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class ShakeAction extends SequenceAction {

	public ShakeAction(Actor a) {
		if (a != null) {
			addMoveToAction(a, a.getX() + 2, a.getY() + 0);
			addMoveToAction(a, a.getX(), a.getY());
		}
	}

	/**
	 * @param a
	 * @param x
	 *            复原x
	 * @param y
	 *            复原y
	 */
	public ShakeAction(Actor a, float x, float y) {
		if (a != null) {
			addMoveToAction(a, a.getX() + 2, a.getY());
			addMoveToAction(a, x, y);
		}
	}

	/**
	 * @param a
	 * @param dx
	 *            抖动x
	 * @param dy
	 *            抖动y
	 * @param x
	 *            复原x
	 * @param y
	 *            复原y
	 */
	public ShakeAction(Actor a, float dx, float dy, float x, float y) {
		if (a != null) {
			addMoveToAction(a, a.getX() + dx, a.getY() + dy);
			addMoveToAction(a, x, y);
		}
	}

	private void addMoveToAction(Actor a, float x, float y) {
		MoveToAction action = Actions.moveTo(x, y);
		action.setDuration(0.02f);
		addAction(action);
	}
}
