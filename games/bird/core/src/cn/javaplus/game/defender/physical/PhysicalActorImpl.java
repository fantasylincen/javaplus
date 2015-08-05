package cn.javaplus.game.defender.physical;

import java.util.List;

import cn.javaplus.game.defender.stage.PhysicalActor;
import cn.javaplus.game.defender.stage.WorldToStagePositionAdaptor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.IBody;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.google.common.collect.Lists;

public class PhysicalActorImpl extends Actor implements PhysicalActor {

	private IBody body;
	private Animation animation;
	private float time;
	private int index;
	private List<Sprite> sprites;

	public PhysicalActorImpl(IBody body, Sprite sprite) {
		this(body, createEmptyAnimation(sprite), new Vector2(), 1);
	}

	private static Animation createEmptyAnimation(Sprite sprite) {
		SnapshotArray<TextureRegion> s = new SnapshotArray<TextureRegion>();
		s.add(sprite);
		return new Animation(0, s);
	}

	public PhysicalActorImpl(IBody body, Animation animation, Vector2 origin,
			float scale) {
		this.body = body;
		this.animation = animation;
		sprites = Lists.newArrayList();
		for (TextureRegion t : animation.getKeyFrames()) {
			Sprite sprite = new Sprite(t);
			sprite.setSize(sprite.getWidth() * scale, sprite.getHeight()
					* scale);
			sprite.setOrigin(origin.getX(), origin.getY());
			sprites.add(sprite);
		}
	}

	@Override
	public void setRotation(float degrees) {
		Sprite sprite = getCurrentSprite();
		sprite.setRotation(degrees);
	}

	private Sprite getCurrentSprite() {
		return sprites.get(index);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		setRotation((float) Math.toDegrees(body.getAngle()));
		Vector2 p = body.getPosition();
		p = new WorldToStagePositionAdaptor(p);
		Sprite sprite = getCurrentSprite();
		sprite.setPosition(p.getX() - sprite.getOriginX(),
				p.getY() - sprite.getOriginY());
		sprite.draw(batch, parentAlpha);
		// sprite.setBounds(10, 10, 10, 10);
		// System.out.println("PhysicalSpriteImpl.draw()" + p.getY() + "," +
		// body.getPosition().getY());

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		this.time += delta;
		index = animation.getKeyFrameIndex(time);
	}

	@Override
	public void setPosition(float x, float y) {
		Sprite sprite = getCurrentSprite();
		sprite.setPosition(x, y);
		super.setPosition(x, y);
	}

	public Fixture createFixture(FixtureDef def) {
		return body.createFixture(def);
	}

	public Fixture createFixture(Shape shape, float density) {
		return body.createFixture(shape, density);
	}

	public void destroyFixture(Fixture fixture) {
		body.destroyFixture(fixture);
	}

	public void setTransform(Vector2 position, float angle) {
		body.setTransform(position, angle);
	}

	public void setTransform(float x, float y, float angle) {
		body.setTransform(x, y, angle);
	}

	public Transform getTransform() {
		return body.getTransform();
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	public float getAngle() {
		return body.getAngle();
	}

	public Vector2 getWorldCenter() {
		return body.getWorldCenter();
	}

	public Vector2 getLocalCenter() {
		return body.getLocalCenter();
	}

	public void setLinearVelocity(Vector2 v) {
		body.setLinearVelocity(v);
	}

	public void setLinearVelocity(float vX, float vY) {
		body.setLinearVelocity(vX, vY);
	}

	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}

	public void setAngularVelocity(float omega) {
		body.setAngularVelocity(omega);
	}

	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}

	public void applyForce(Vector2 force, Vector2 point, boolean wake) {
		body.applyForce(force, point, wake);
	}

	public void applyForce(float forceX, float forceY, float pointX,
			float pointY, boolean wake) {
		body.applyForce(forceX, forceY, pointX, pointY, wake);
	}

	public void applyForceToCenter(Vector2 force, boolean wake) {
		body.applyForceToCenter(force, wake);
	}

	public void applyForceToCenter(float forceX, float forceY, boolean wake) {
		body.applyForceToCenter(forceX, forceY, wake);
	}

	public void applyTorque(float torque, boolean wake) {
		body.applyTorque(torque, wake);
	}

	public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
		body.applyLinearImpulse(impulse, point, wake);
	}

	public void applyLinearImpulse(float impulseX, float impulseY,
			float pointX, float pointY, boolean wake) {
		body.applyLinearImpulse(impulseX, impulseY, pointX, pointY, wake);
	}

	public void applyAngularImpulse(float impulse, boolean wake) {
		body.applyAngularImpulse(impulse, wake);
	}

	public float getMass() {
		return body.getMass();
	}

	public float getInertia() {
		return body.getInertia();
	}

	public MassData getMassData() {
		return body.getMassData();
	}

	public void setMassData(MassData data) {
		body.setMassData(data);
	}

	public void resetMassData() {
		body.resetMassData();
	}

	public Vector2 getWorldPoint(Vector2 localPoint) {
		return body.getWorldPoint(localPoint);
	}

	public Vector2 getWorldVector(Vector2 localVector) {
		return body.getWorldVector(localVector);
	}

	public Vector2 getLocalPoint(Vector2 worldPoint) {
		return body.getLocalPoint(worldPoint);
	}

	public Vector2 getLocalVector(Vector2 worldVector) {
		return body.getLocalVector(worldVector);
	}

	public Vector2 getLinearVelocityFromWorldPoint(Vector2 worldPoint) {
		return body.getLinearVelocityFromWorldPoint(worldPoint);
	}

	public Vector2 getLinearVelocityFromLocalPoint(Vector2 localPoint) {
		return body.getLinearVelocityFromLocalPoint(localPoint);
	}

	public float getLinearDamping() {
		return body.getLinearDamping();
	}

	public void setLinearDamping(float linearDamping) {
		body.setLinearDamping(linearDamping);
	}

	public float getAngularDamping() {
		return body.getAngularDamping();
	}

	public void setAngularDamping(float angularDamping) {
		body.setAngularDamping(angularDamping);
	}

	public void setType(BodyType type) {
		body.setType(type);
	}

	public BodyType getType() {
		return body.getType();
	}

	public void setBullet(boolean flag) {
		body.setBullet(flag);
	}

	public boolean isBullet() {
		return body.isBullet();
	}

	public void setSleepingAllowed(boolean flag) {
		body.setSleepingAllowed(flag);
	}

	public boolean isSleepingAllowed() {
		return body.isSleepingAllowed();
	}

	public void setAwake(boolean flag) {
		body.setAwake(flag);
	}

	public boolean isAwake() {
		return body.isAwake();
	}

	public void setActive(boolean flag) {
		body.setActive(flag);
	}

	public boolean isActive() {
		return body.isActive();
	}

	public void setFixedRotation(boolean flag) {
		body.setFixedRotation(flag);
	}

	public boolean isFixedRotation() {
		return body.isFixedRotation();
	}

	public Array<Fixture> getFixtureList() {
		return body.getFixtureList();
	}

	public Array<JointEdge> getJointList() {
		return body.getJointList();
	}

	public float getGravityScale() {
		return body.getGravityScale();
	}

	public void setGravityScale(float scale) {
		body.setGravityScale(scale);
	}

	public World getWorld() {
		return body.getWorld();
	}

	public Object getUserData() {
		return body.getUserData();
	}

	public void setUserData(Object userData) {
		body.setUserData(userData);
	}

	public Vector2 getLinVelLoc() {
		return body.getLinVelLoc();
	}

	public Vector2 getLinVelWorld() {
		return body.getLinVelWorld();
	}

	public Vector2 getLocalPoint2() {
		return body.getLocalPoint2();
	}

	public Vector2 getLocalVector() {
		return body.getLocalVector();
	}

}
