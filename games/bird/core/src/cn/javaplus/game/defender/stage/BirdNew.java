package cn.javaplus.game.defender.stage;

import cn.javaplus.game.defender.App;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.IStage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

public class BirdNew implements IActor {

	private PhysicalActor sprite;

	private final class InputAdapterEx extends InputAdapter {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {

//			applyForce(new Vector2(1f, 10f), new Vector2(0.9f, 0.5f), true);
			setLinearVelocity(0, 4);
//			setRotation(10);
			return true;
		}
		//
		// @Override
		// public boolean touchDragged(int x, int y, int pointer) {
		// float x2 = getX();
		// float y2 = getY();
		//
		// // setLinearVelocity((x - x2) / X, (y - y2) / X);
		//
		// Vector2 force = new Vector2();
		// // force.set((x - x2) / X, (y - y2) / X);
		// force.set((x - x2) / X, (y - y2) / X);
		// applyForceToCenter(force, false);
		// return true;
		// }
		//
		// @Override
		// public boolean touchUp(int screenX, int screenY, int pointer, int
		// button) {
		// // force.set(0, 0);
		// return true;
		// }
	}

	public BirdNew(int x, int y) {

		Animation a = App.getAssetsManager().getAnimation(0.1f, "bird1.png", "bird2.png", "bird3.png");
		a.setPlayMode(Animation.LOOP);
		sprite = App.getAssetsManager().getPhysicalActor("bird1", a, x, y, 1);

		App.getCurrentInput().add(new InputAdapterEx());
	}

	public Fixture createFixture(FixtureDef def) {
		return sprite.createFixture(def);
	}

	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}

	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch, parentAlpha);
	}

	public Fixture createFixture(Shape shape, float density) {
		return sprite.createFixture(shape, density);
	}

	public void act(float delta) {
		sprite.act(delta);
	}

	public void destroyFixture(Fixture fixture) {
		sprite.destroyFixture(fixture);
	}

	public boolean fire(Event event) {
		return sprite.fire(event);
	}

	public void setTransform(Vector2 position, float angle) {
		sprite.setTransform(position, angle);
	}

	public void setTransform(float x, float y, float angle) {
		sprite.setTransform(x, y, angle);
	}

	public boolean notify(Event event, boolean capture) {
		return sprite.notify(event, capture);
	}

	public Transform getTransform() {
		return sprite.getTransform();
	}

	public Vector2 getPosition() {
		return sprite.getPosition();
	}

	public float getAngle() {
		return sprite.getAngle();
	}

	public Vector2 getWorldCenter() {
		return sprite.getWorldCenter();
	}

	public IActor hit(float x, float y, boolean touchable) {
		return sprite.hit(x, y, touchable);
	}

	public Vector2 getLocalCenter() {
		return sprite.getLocalCenter();
	}

	public void setLinearVelocity(Vector2 v) {
		sprite.setLinearVelocity(v);
	}

	public void setLinearVelocity(float vX, float vY) {
		sprite.setLinearVelocity(vX, vY);
	}

	public Vector2 getLinearVelocity() {
		return sprite.getLinearVelocity();
	}

	public void setAngularVelocity(float omega) {
		sprite.setAngularVelocity(omega);
	}

	public float getAngularVelocity() {
		return sprite.getAngularVelocity();
	}

	public void applyForce(Vector2 force, Vector2 point, boolean wake) {
		sprite.applyForce(force, point, wake);
	}

	public boolean remove() {
		return sprite.remove();
	}

	public boolean addListener(EventListener listener) {
		return sprite.addListener(listener);
	}

	public void applyForce(float forceX, float forceY, float pointX,
			float pointY, boolean wake) {
		sprite.applyForce(forceX, forceY, pointX, pointY, wake);
	}

	public boolean removeListener(EventListener listener) {
		return sprite.removeListener(listener);
	}

	public Array<EventListener> getListeners() {
		return sprite.getListeners();
	}

	public boolean addCaptureListener(EventListener listener) {
		return sprite.addCaptureListener(listener);
	}

	public boolean removeCaptureListener(EventListener listener) {
		return sprite.removeCaptureListener(listener);
	}

	public Array<EventListener> getCaptureListeners() {
		return sprite.getCaptureListeners();
	}

	public void addAction(Action action) {
		sprite.addAction(action);
	}

	public void applyForceToCenter(Vector2 force, boolean wake) {
		sprite.applyForceToCenter(force, wake);
	}

	public void removeAction(Action action) {
		sprite.removeAction(action);
	}

	public Array<Action> getActions() {
		return sprite.getActions();
	}

	public void clearActions() {
		sprite.clearActions();
	}

	public void clearListeners() {
		sprite.clearListeners();
	}

	public void applyForceToCenter(float forceX, float forceY, boolean wake) {
		sprite.applyForceToCenter(forceX, forceY, wake);
	}

	public void clear() {
		sprite.clear();
	}

	public IStage getStage() {
		return sprite.getStage();
	}

	public void applyTorque(float torque, boolean wake) {
		sprite.applyTorque(torque, wake);
	}

	public boolean isDescendantOf(IActor actor) {
		return sprite.isDescendantOf(actor);
	}

	public boolean isAscendantOf(IActor actor) {
		return sprite.isAscendantOf(actor);
	}

	public boolean hasParent() {
		return sprite.hasParent();
	}

	public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
		sprite.applyLinearImpulse(impulse, point, wake);
	}

	public Group getParent() {
		return sprite.getParent();
	}

	public boolean isTouchable() {
		return sprite.isTouchable();
	}

	public Touchable getTouchable() {
		return sprite.getTouchable();
	}

	public void setTouchable(Touchable touchable) {
		sprite.setTouchable(touchable);
	}

	public void applyLinearImpulse(float impulseX, float impulseY,
			float pointX, float pointY, boolean wake) {
		sprite.applyLinearImpulse(impulseX, impulseY, pointX, pointY, wake);
	}

	public boolean isVisible() {
		return sprite.isVisible();
	}

	public void setVisible(boolean visible) {
		sprite.setVisible(visible);
	}

	public Object getUserObject() {
		return sprite.getUserObject();
	}

	public void setUserObject(Object userObject) {
		sprite.setUserObject(userObject);
	}

	public float getX() {
		return sprite.getX();
	}

	public void setX(float x) {
		sprite.setX(x);
	}

	public float getY() {
		return sprite.getY();
	}

	public void setY(float y) {
		sprite.setY(y);
	}

	public void translate(float x, float y) {
		sprite.translate(x, y);
	}

	public float getWidth() {
		return sprite.getWidth();
	}

	public void applyAngularImpulse(float impulse, boolean wake) {
		sprite.applyAngularImpulse(impulse, wake);
	}

	public void setWidth(float width) {
		sprite.setWidth(width);
	}

	public float getHeight() {
		return sprite.getHeight();
	}

	public void setHeight(float height) {
		sprite.setHeight(height);
	}

	public float getTop() {
		return sprite.getTop();
	}

	public float getMass() {
		return sprite.getMass();
	}

	public float getRight() {
		return sprite.getRight();
	}

	public void setSize(float width, float height) {
		sprite.setSize(width, height);
	}

	public float getInertia() {
		return sprite.getInertia();
	}

	public void size(float size) {
		sprite.size(size);
	}

	public MassData getMassData() {
		return sprite.getMassData();
	}

	public void size(float width, float height) {
		sprite.size(width, height);
	}

	public void setBounds(float x, float y, float width, float height) {
		sprite.setBounds(x, y, width, height);
	}

	public void setMassData(MassData data) {
		sprite.setMassData(data);
	}

	public float getOriginX() {
		return sprite.getOriginX();
	}

	public void setOriginX(float originX) {
		sprite.setOriginX(originX);
	}

	public float getOriginY() {
		return sprite.getOriginY();
	}

	public void setOriginY(float originY) {
		sprite.setOriginY(originY);
	}

	public void setOrigin(float originX, float originY) {
		sprite.setOrigin(originX, originY);
	}

	public void resetMassData() {
		sprite.resetMassData();
	}

	public float getScaleX() {
		return sprite.getScaleX();
	}

	public void setScaleX(float scaleX) {
		sprite.setScaleX(scaleX);
	}

	public float getScaleY() {
		return sprite.getScaleY();
	}

	public void setScaleY(float scaleY) {
		sprite.setScaleY(scaleY);
	}

	public void setScale(float scale) {
		sprite.setScale(scale);
	}

	public Vector2 getWorldPoint(Vector2 localPoint) {
		return sprite.getWorldPoint(localPoint);
	}

	public void setScale(float scaleX, float scaleY) {
		sprite.setScale(scaleX, scaleY);
	}

	public void scale(float scale) {
		sprite.scale(scale);
	}

	public void scale(float scaleX, float scaleY) {
		sprite.scale(scaleX, scaleY);
	}

	public Vector2 getWorldVector(Vector2 localVector) {
		return sprite.getWorldVector(localVector);
	}

	public float getRotation() {
		return sprite.getRotation();
	}

	public void setRotation(float degrees) {
		sprite.setRotation(degrees);
	}

	public void rotate(float amountInDegrees) {
		sprite.rotate(amountInDegrees);
	}

	public Vector2 getLocalPoint(Vector2 worldPoint) {
		return sprite.getLocalPoint(worldPoint);
	}

	public void setColor(Color color) {
		sprite.setColor(color);
	}

	public void setColor(float r, float g, float b, float a) {
		sprite.setColor(r, g, b, a);
	}

	public Color getColor() {
		return sprite.getColor();
	}

	public Vector2 getLocalVector(Vector2 worldVector) {
		return sprite.getLocalVector(worldVector);
	}

	public String getName() {
		return sprite.getName();
	}

	public void setName(String name) {
		sprite.setName(name);
	}

	public Vector2 getLinearVelocityFromWorldPoint(Vector2 worldPoint) {
		return sprite.getLinearVelocityFromWorldPoint(worldPoint);
	}

	public void toFront() {
		sprite.toFront();
	}

	public void toBack() {
		sprite.toBack();
	}

	public void setZIndex(int index) {
		sprite.setZIndex(index);
	}

	public Vector2 getLinearVelocityFromLocalPoint(Vector2 localPoint) {
		return sprite.getLinearVelocityFromLocalPoint(localPoint);
	}

	public float getLinearDamping() {
		return sprite.getLinearDamping();
	}

	public void setLinearDamping(float linearDamping) {
		sprite.setLinearDamping(linearDamping);
	}

	public int getZIndex() {
		return sprite.getZIndex();
	}

	public float getAngularDamping() {
		return sprite.getAngularDamping();
	}

	public boolean clipBegin() {
		return sprite.clipBegin();
	}

	public void setAngularDamping(float angularDamping) {
		sprite.setAngularDamping(angularDamping);
	}

	public boolean clipBegin(float x, float y, float width, float height) {
		return sprite.clipBegin(x, y, width, height);
	}

	public void setType(BodyType type) {
		sprite.setType(type);
	}

	public BodyType getType() {
		return sprite.getType();
	}

	public void setBullet(boolean flag) {
		sprite.setBullet(flag);
	}

	public boolean isBullet() {
		return sprite.isBullet();
	}

	public void clipEnd() {
		sprite.clipEnd();
	}

	public void setSleepingAllowed(boolean flag) {
		sprite.setSleepingAllowed(flag);
	}

	public Vector2 screenToLocalCoordinates(Vector2 screenCoords) {
		return sprite.screenToLocalCoordinates(screenCoords);
	}

	public boolean isSleepingAllowed() {
		return sprite.isSleepingAllowed();
	}

	public void setAwake(boolean flag) {
		sprite.setAwake(flag);
	}

	public Vector2 stageToLocalCoordinates(Vector2 stageCoords) {
		return sprite.stageToLocalCoordinates(stageCoords);
	}

	public boolean isAwake() {
		return sprite.isAwake();
	}

	public Vector2 localToStageCoordinates(Vector2 localCoords) {
		return sprite.localToStageCoordinates(localCoords);
	}

	public void setActive(boolean flag) {
		sprite.setActive(flag);
	}

	public Vector2 localToParentCoordinates(Vector2 localCoords) {
		return sprite.localToParentCoordinates(localCoords);
	}

	public Vector2 localToAscendantCoordinates(IActor ascendant,
			Vector2 localCoords) {
		return sprite.localToAscendantCoordinates(ascendant, localCoords);
	}

	public Vector2 parentToLocalCoordinates(Vector2 parentCoords) {
		return sprite.parentToLocalCoordinates(parentCoords);
	}

	public String toString() {
		return sprite.toString();
	}

	public boolean isActive() {
		return sprite.isActive();
	}

	public void setParent(Group group) {
		sprite.setParent(group);
	}

	public void setStage(IStage stage) {
		sprite.setStage(stage);
	}

	public void setFixedRotation(boolean flag) {
		sprite.setFixedRotation(flag);
	}

	public boolean isFixedRotation() {
		return sprite.isFixedRotation();
	}

	public Array<Fixture> getFixtureList() {
		return sprite.getFixtureList();
	}

	public Array<JointEdge> getJointList() {
		return sprite.getJointList();
	}

	public float getGravityScale() {
		return sprite.getGravityScale();
	}

	public void setGravityScale(float scale) {
		sprite.setGravityScale(scale);
	}

	public World getWorld() {
		return sprite.getWorld();
	}

	public Object getUserData() {
		return sprite.getUserData();
	}

	public void setUserData(Object userData) {
		sprite.setUserData(userData);
	}

	public Vector2 getLinVelLoc() {
		return sprite.getLinVelLoc();
	}

	public Vector2 getLinVelWorld() {
		return sprite.getLinVelWorld();
	}

	public Vector2 getLocalPoint2() {
		return sprite.getLocalPoint2();
	}

	public Vector2 getLocalVector() {
		return sprite.getLocalVector();
	}

}
