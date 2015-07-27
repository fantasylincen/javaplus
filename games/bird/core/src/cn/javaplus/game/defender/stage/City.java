package cn.javaplus.game.defender.stage;

import com.badlogic.gdx.scenes.scene2d.IActor;

public class City extends ImageGroup implements IActor {
	public City() {
		super("city", 6, 100);
		setY(32);
	}
}
