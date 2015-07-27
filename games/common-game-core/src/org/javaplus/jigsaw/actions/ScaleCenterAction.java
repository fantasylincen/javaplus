package org.javaplus.jigsaw.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

public class ScaleCenterAction extends ParallelAction {

	private ScaleToAction scaleAction;
	private MoveToAction moveToAction;
	private Actor actor;

	public ScaleCenterAction(Actor actor) {
		this.actor = actor;
	}

	public void setScale(float scale) {

		float scaleMove = scale - actor.getScaleX();

		// System.out.println("ScaleCenterAction.setScale()" + scaleMove);

		if (scaleAction != null) {
			getActions().removeValue(scaleAction, false);
		}
		scaleAction = new ScaleToAction() {
			@Override
			protected void end() {
				ScaleCenterAction.this.end();
			}
		};
		scaleAction.setScale(scale);
		scaleAction.setDuration(0.1f);
		scaleAction.setScale(scale);
		addAction(scaleAction);

		if (moveToAction != null) {
			getActions().removeValue(moveToAction, true);
		}
		moveToAction = new MoveToAction();
		moveToAction.setDuration(0.1f);
		float x = actor.getX() - actor.getWidth() * scaleMove / 2;
		float y = actor.getY() - actor.getHeight() * scaleMove / 2;
		System.out.println("ScaleCenterAction.setScale()" + x + "," + y);
		moveToAction.setPosition(x, y);
		addAction(moveToAction);
	}

	public void end() {
	}

	public void setDuration(float d) {
		scaleAction.setDuration(d);
		moveToAction.setDuration(d);
	}

}
