package cn.javaplus.shhz.actor;

import cn.javaplus.game.shhz.define.D;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;

public class HeLongAction extends Action {

	private MoveToAction m;
	private SizeToAction a;

	public HeLongAction(ActorControlable actor) {
		m = new MoveToAction();
		m.setPosition(actor.getWidth() / 2, actor.getHeight() / 2);
		m.setDuration((float) D.ACTOR_MENU_SHOW_TIME);

		a = new SizeToAction();
		a.setSize(0, 0);
		a.setDuration((float) D.ACTOR_MENU_SHOW_TIME);
	}

	@Override
	public void setActor(Actor ac) {
		super.setActor(ac);
		a.setActor(getActor());
		m.setActor(getActor());
	}

	@Override
	public boolean act(float delta) {
		a.act(delta);
		return m.act(delta);
	}
}
