package cn.javaplus.shhz.actor;

import cn.javaplus.game.shhz.define.D;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;

public class SanKaiAction extends SequenceAction {

	private MoveToAction m;
	private SizeToAction a;
	private float y;
	private float x;
	private double ag;
	private int r;

	/**
	 * @param menu
	 * @param ag
	 *            角度
	 * @param r
	 *            半径
	 */
	public SanKaiAction(ActorMenu menu, double ag, int r) {
		this.ag = ag;
		this.r = r;
	}

	@Override
	public void setActor(Actor actor) {
		super.setActor(actor);
		if (actor == null) {
			return;
		}
		a = new SizeToAction();
		m = new MoveToAction();

		a.setActor(actor);
		m.setActor(actor);

		m.setDuration((float) D.ACTOR_MENU_SHOW_TIME);

		y = (float) (Math.cos(ag) * r);
		x = (float) (Math.sin(ag) * r);

		m.setPosition(actor.getX() + x, actor.getY() + y);

		a.setSize(D.ACTOR_MENU_WIDTH, D.ACTOR_MENU_HEIGHT);
		a.setDuration((float) D.ACTOR_MENU_SHOW_TIME);
	}

	@Override
	public boolean act(float delta) {
		a.act(delta);
		return m.act(delta);
	}
}
