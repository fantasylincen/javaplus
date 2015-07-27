package cn.javaplus.game.defender.stage;

import com.badlogic.gdx.scenes.scene2d.IActor;

public class Trees extends ImageGroup implements IActor {
	public Trees() {
		super("trees", 6, 300);
		setY(32);
	}
}
