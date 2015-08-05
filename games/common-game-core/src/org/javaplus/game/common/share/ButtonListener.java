package org.javaplus.game.common.share;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ButtonListener extends ChangeListener {

	@Override
	public final void changed(ChangeEvent event, Actor actor) {

		try {
			click(event, actor);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void click(ChangeEvent event, Actor actor) {

	}

}
