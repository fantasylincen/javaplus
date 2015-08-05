package cn.javaplus.game.defender.stage;

import cn.javaplus.game.defender.App;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.IBody;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bird extends Actor {

	private static final int VY_MAX = 2;

	private static final int VY_MIN = -4;

	private final class InputAdapterEx extends InputAdapter {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			body.setLinearVelocity(0, 4);
			return true;
		}
	}

	// 翅膀速度 范围在 1 - 999
	private static final int SPEED = 850;

	private Sprite[] s = new Sprite[3];

	private float time;

	private IBody body;

	public Bird() {
		for (int i = 0; i < s.length; i++) {
			s[i] = new Sprite(App.getAssetsManager().getPngTexture("bird" + (i + 1)));
		}
		createPhysicalBody();

		App.getCurrentInput().add(new InputAdapterEx());
	}

	private void createPhysicalBody() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.setType(BodyType.DynamicBody);
		bodyDef.setAllowSleep(true);
		bodyDef.setActive(true);
		bodyDef.setAngularDamping(0.2f);
		bodyDef.setLinearDamping(0.2f);
		Vector2 p = new StageToWorldPositionAdaptor(200, 300);
		bodyDef.getPosition().set(p.getX(), p.getY());

		body = App.getWorld().createBody(bodyDef);

		FixtureDef def = new FixtureDef();
		PolygonShape ps = new PolygonShape();

		def.setDensity(1.3f);
		def.setFriction(0.2f);
		def.setRestitution(0.3f);

		float[] vertices = new float[] { 0, 0, 0, 60, 85, 60, 85, 0 };

		ps.set(vertices);

		def.setShape(ps);

		body.createFixture(def);
		MassData data = new MassData();

		data.setMass(1.5f);

		body.setMassData(data);

	}

	@Override
	public void setRotation(float degrees) {
		for (int i = 0; i < s.length; i++) {
			s[i].setRotation(degrees);
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		long d = ((long) (time * 1000)) / (1000 - SPEED) % 3;

		s[(int) d].draw(batch, parentAlpha);
		setRotation(body.getAngle());
		Vector2 p = body.getPosition();
		p = new WorldToStagePositionAdaptor(p);
		setPosition(p.getX(), p.getY());
		// System.out.println("Bird.draw() pos:" + body.getPosition() + ",  v:"
		// + body.getLinearVelocity());
	}

	@Override
	public void setPosition(float x, float y) {
		for (int i = 0; i < s.length; i++) {
			s[i].setPosition(x, y);
		}
		body.getPosition().set(x, y);
		// System.out.println("Bird.setPosition()" + body.getLinearVelocity());
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		time += delta;
		float vy = body.getLinearVelocity().getY();
		vy = Math.max(VY_MIN, vy);
		vy = Math.min(VY_MAX, vy);

		float rotation = (vy - VY_MIN) / (VY_MAX - VY_MIN) * 120 - 90;
		
		setRotation(rotation);

//		System.out.println("Bird.act()" + rotation + "," + vy);
		// -90 --- 30
	}

}
