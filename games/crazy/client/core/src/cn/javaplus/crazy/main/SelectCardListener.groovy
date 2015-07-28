package cn.javaplus.crazy.main;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class SelectCardListener extends InputListener {

	static boolean cardMoveable;

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		changePosition(event);
		cardMoveable = true;
		return cardMoveable;
	}

	private void changePosition(InputEvent event) {
		CardActor c = (CardActor) event.getTarget();
		c.changeSelected();
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer,
			Actor actor) {
		if (pointer != 0 || actor == null || !cardMoveable)
			return;
		changePosition(event);
	}

}