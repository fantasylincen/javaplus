package cn.javaplus.game.defender.stage;

import cn.javaplus.common.util.Util;
import cn.javaplus.game.defender.App;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.IBody;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Ground extends ImageGroup implements IActor {

	private static final String GROUND_NAME = "ground";

	public Ground() {
		super(GROUND_NAME, 30, 2000f);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.getPosition().set(getX(), getY());
		bodyDef.setType(BodyType.StaticBody);
		bodyDef.setAllowSleep(true);
		bodyDef.setActive(true);

		IBody body = App.getWorld().createBody(bodyDef);

		FixtureDef def = new FixtureDef();
		EdgeShape ps = new EdgeShape();

		def.setDensity(1.0f);
		def.setFriction(0.8f);
		def.setRestitution(0.3f);

		int x = 10000000;
		
		Image image = App.getAssetsManager().getImage(GROUND_NAME);
		float h = image.getHeight();
		h = Util.getStageToWorld(h);
		ps.set(-x, getY() + h, x, getY() + h);
		
		def.setShape(ps);

		body.createFixture(def);
	}
}
