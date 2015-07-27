package cn.javaplus.game.defender.stage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.IActor;

public class BackGround extends Group implements IActor {

	public BackGround() {

		addActor(new Sky());
		addActor(new City());
		addActor(new Trees());
		addActor(new Ground());
	}
}
