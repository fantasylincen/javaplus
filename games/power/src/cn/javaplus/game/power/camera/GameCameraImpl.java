package cn.javaplus.game.power.camera;

import java.util.List;

import cn.javaplus.game.power.Game;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.ICamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.google.common.collect.Lists;

public class GameCameraImpl implements GameCamera {

	public class MoveEvent extends Event {

		private Vector2 dis;

		public MoveEvent(Vector2 dis) {
			this.dis = dis;
		}

		public Vector2 getDis() {
			return dis;
		}

	}

	public class UpdatePositionEvent extends Event {

		private float x;
		private float y;

		public UpdatePositionEvent(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}
	}

	private ICamera camera;
	private static final int HIDE_BOUND = 200;
	private final float initX;
	private final float initY;
	private final List<EventListener> listeners = Lists.newArrayList();

	public GameCameraImpl(ICamera camera) {
		this.camera = camera;

		initX = getX();
		initY = getY();
	}

	@Override
	public float getX() {
		return camera.getPosition().x;
	}

	@Override
	public float getY() {
		return camera.getPosition().y;
	}

	@Override
	public boolean isVisible(VisibleObject o) {

		if (isOverUp(o)) {
			return false;
		}

		if (isOverLeft(o)) {
			return false;
		}

		if (isOverRight(o)) {
			return false;
		}

		if (isOverDown(o)) {
			return false;
		}

		return true;
	}

	private boolean isOverDown(VisibleObject o) {
		return getY() - o.getY() > initY + HIDE_BOUND;
	}

	private boolean isOverRight(VisibleObject o) {
		int gameWidth = Game.getStageWidth();
		return o.getX() - getX() > gameWidth - initX + HIDE_BOUND;
	}

	private boolean isOverLeft(VisibleObject o) {
		return getX() - o.getX() > initX + HIDE_BOUND;
	}

	private boolean isOverUp(VisibleObject o) {
		int gameHeight = Game.getStageHeight();
		return o.getY() - getY() > gameHeight - initY + HIDE_BOUND;
	}

	public void update() {
		camera.update();
	}

	@Override
	public Vector3 getPosition() {
		Vector3 position = camera.getPosition();

		return position;
	}

	public void update(boolean updateFrustum) {
		camera.update(updateFrustum);
	}

	public void apply(GL10 gl) {
		camera.apply(gl);
	}

	public void lookAt(float x, float y, float z) {
		camera.lookAt(x, y, z);
	}

	public void lookAt(Vector3 target) {
		camera.lookAt(target);
	}

	public void normalizeUp() {
		camera.normalizeUp();
	}

	public void rotate(float angle, float axisX, float axisY, float axisZ) {
		camera.rotate(angle, axisX, axisY, axisZ);
	}

	public void rotate(Vector3 axis, float angle) {
		camera.rotate(axis, angle);
	}

	public void rotate(Matrix4 transform) {
		camera.rotate(transform);
	}

	public void rotate(Quaternion quat) {
		camera.rotate(quat);
	}

	public void rotateAround(Vector3 point, Vector3 axis, float angle) {
		camera.rotateAround(point, axis, angle);
	}

	public void transform(Matrix4 transform) {
		camera.transform(transform);
	}

	public void translate(float x, float y, float z) {
		float xOld = getX();
		float yOld = getY();
		camera.translate(x, y, z);

		fire(new UpdatePositionEvent(getX(), getY()));
		fire(new MoveEvent(new Vector2(getX() - xOld, getY() - yOld)));
	}

	@Override
	public boolean removeListener(EventListener listener) {
		return listeners.remove(listener);
	}

	public void translate(Vector3 vec) {
		camera.translate(vec);
	}

	public void unproject(Vector3 vec, float viewportX, float viewportY,
			float viewportWidth, float viewportHeight) {
		camera.unproject(vec, viewportX, viewportY, viewportWidth,
				viewportHeight);
	}

	public void unproject(Vector3 vec) {
		camera.unproject(vec);
	}

	public void project(Vector3 vec) {
		camera.project(vec);
	}

	public void project(Vector3 vec, float viewportX, float viewportY,
			float viewportWidth, float viewportHeight) {
		camera.project(vec, viewportX, viewportY, viewportWidth, viewportHeight);
	}

	public Ray getPickRay(float x, float y, float viewportX, float viewportY,
			float viewportWidth, float viewportHeight) {
		return camera.getPickRay(x, y, viewportX, viewportY, viewportWidth,
				viewportHeight);
	}

	public Ray getPickRay(float x, float y) {
		return camera.getPickRay(x, y);
	}

	public float getViewPortHeight() {
		return camera.getViewPortHeight();
	}

	public Matrix4 getCombined() {
		return camera.getCombined();
	}

	public void setViewportHeight(float height) {
		camera.setViewportHeight(height);
	}

	public void setViewportWidth(float width) {
		camera.setViewportWidth(width);
	}

	public void setPosition(float centerX, float centerY, int i) {
		float xOld = getX();
		float yOld = getY();
		camera.setPosition(centerX, centerY, i);

		fire(new UpdatePositionEvent(getX(), getY()));
		fire(new MoveEvent(new Vector2(getX() - xOld, getY() - yOld)));
	}

	private void fire(Event e) {
		for (EventListener l : listeners) {
			l.handle(e);
		}
	}

	@Override
	public void addListener(EventListener listener) {
		listeners.add(listener);

	}

	@Override
	public void setPosition(float x, float y) {
		Vector3 p = getPosition();
		p.x = x;
		p.y = y;
	}

	@Override
	public void translate(float x, float y) {
		Vector3 p = getPosition();
		p.x += x;
		p.y += y;
	}
}