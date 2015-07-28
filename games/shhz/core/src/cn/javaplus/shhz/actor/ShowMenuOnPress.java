package cn.javaplus.shhz.actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * 选中时， 弹出菜单
 */
public class ShowMenuOnPress extends ActorGestureListener {

	private ActorControlable actor;

	public ShowMenuOnPress(ActorControlable actor) {
		this.actor = actor;
	}

	public ActorControlable getActor() {
		return actor;
	}

	@Override
	public void tap(InputEvent event, float x, float y, int count, int button) {
		actor.getMenu().show();
	}
}
