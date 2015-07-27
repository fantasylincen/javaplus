package cn.javaplus.jigsaw.gameover;

import org.javaplus.jigsaw.actions.ScaleCenterAction;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PopAction extends SequenceAction {

	public PopAction(Actor actor) {

		ScaleCenterAction action;

		action = new ScaleCenterAction(actor);
		action.setScale(1.2f);
		action.setDuration(0.1f);
		addAction(action);

		action = new ScaleCenterAction(actor) {
			@Override
			public void end() {
				PopAction.this.end();
			}
		};
		action.setScale(1.0f);
		action.setDuration(0.05f);
		addAction(action);

	}

	public void end() {

	}

}
