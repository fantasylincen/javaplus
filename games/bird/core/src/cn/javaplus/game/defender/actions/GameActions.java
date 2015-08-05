package cn.javaplus.game.defender.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;

public class GameActions {

	/**
	 * 心跳動作
	 * 
	 * @return
	 */
	public static Action getBeatAction() {
		return new ActionBuilder().scale(1.03, 0.1).scale(1, 0.4).step(0.4)
				.forever().build();
	}

	public static Action getColorAction() {
		SequenceAction action = new SequenceAction();

		ColorAction c = new ColorAction();
		c.setColor(Color.WHITE);
		c.setEndColor(Color.GRAY);
		c.setDuration(0.5f);
		action.addAction(c);

		c = new ColorAction();
		c.setColor(Color.GRAY);
		c.setEndColor(Color.WHITE);
		c.setDuration(0.5f);
		action.addAction(c);

		RepeatAction a = new RepeatAction();
		a.setAction(action);
		a.setCount(RepeatAction.FOREVER);
		return a;
	}

	public static Action getMoveRightAction(IActor actor, float width) {
		MoveToAction a = new MoveToAction() {
			@Override
			protected void end() {
				getActor().removeAction(this);
			}
		};
		a.setDuration(0.1f);
		a.setX(actor.getX() + width);
		a.setY(actor.getY());
		return a;
	}

	public static Action getInsertSelfAction(IActor target) {
		
		float width = target.getWidth();
		float height = target.getHeight();
		SequenceAction action = new SequenceAction();
		
		SizeToAction a = new SizeToAction();
		a.setDuration(0.01f);
		a.setWidth(width * 0.3f);
		a.setHeight(height);
		
		action.addAction(a);
		
		a = new SizeToAction();
		a.setDuration(0.09f);
		a.setWidth(width);
		a.setHeight(height);
		
		action.addAction(a);
		
		return action;
	}

}
