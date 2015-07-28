package cn.javaplus.game.power.assets;

import com.badlogic.gdx.physics.box2d.BodyDef;

public class NormalBodyDef extends BodyDef {

	public NormalBodyDef() {
		setType(BodyType.DynamicBody);
		setAllowSleep(false);
		setActive(true);
		setAngularDamping(0.2f);
		setLinearDamping(0.2f);
	}
}
