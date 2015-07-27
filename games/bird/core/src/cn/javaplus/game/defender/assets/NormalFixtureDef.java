package cn.javaplus.game.defender.assets;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public class NormalFixtureDef extends FixtureDef {

	public NormalFixtureDef() {
		setDensity(1.3f);
		setFriction(0.2f);
		setRestitution(0.3f);
	}
}
