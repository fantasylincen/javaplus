package cn.javaplus.game.defender.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * 动作序列构建器
 * @author Administrator
 *
 */
public class ActionBuilder {

	private SequenceAction action;

	public ActionBuilder() {
		action = new SequenceAction();
	}

	public ActionBuilder scale(double scale, double duration) {
		ScaleToAction temp = new ScaleToAction();
		temp.setDuration((float) duration);
		temp.setScale((float) scale);
		action.addAction(temp);
		return this;
	}

	public ActionBuilder step(double duration) {
		DelayAction temp = new DelayAction();
		temp.setDuration((float) duration);
		action.addAction(temp);
		return this;
	}


	public ActionBuilder repeat(int count) {
		RepeatAction r = new RepeatAction();
		r.setAction(action);
		r.setCount(count);
		action = new SequenceAction();
		action.addAction(r);
		return this;
	}

	public ActionBuilder forever() {
		repeat(RepeatAction.FOREVER);
		return this;
	}

	public Action build() {
		return action;
	}

}
