package cn.javaplus.shhz.camera;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.components.Background;
import cn.javaplus.shhz.screen.RenderListener;
import cn.javaplus.shhz.stage.GameStage;
import cn.javaplus.shhz.stage.MapObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;

public class CameraController extends GestureAdapter implements RenderListener {

	private Background background;
	private OrthographicCamera camera;
	private float ratioMax;
	private float ratioMin;
	float velX, velY;
	boolean flinging = false;
	float initialScale = 1;
	private GameStage stage;

	public CameraController(GameStage stage, Background background) {
		this.stage = stage;
		this.background = background;
		this.camera = stage.getCamera();
		initLimitSize();
		camera.zoom = 1;
	}

	public boolean touchDown(float x, float y, int pointer, int button) {
		initialScale = camera.zoom;
		flinging = false;
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (!isEnable()) {
			return false;
		}
		flinging = true;
		velX = camera.zoom * velocityX * 0.5f;
		velY = camera.zoom * velocityY * 0.5f;
		return false;
	}

	private boolean isEnable() {
		return !hasTabCurrentSelect();
//		return background.isTouched() || stage.getSelected() == null;
	}

	private boolean hasTabCurrentSelect() {
		MapObject selected = stage.getSelected();
		if(selected == null) {
			return false;
		}
		return selected.hasTouchDown();
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if (!isEnable()) {
			return false;
		}
		camera.position.add(-deltaX * camera.zoom, deltaY * camera.zoom, 0);
		limitPosition();
		return false;
	}

	@Override
	public boolean zoom(float originalDistance, float currentDistance) {
		if (!isEnable()) {
			return false;
		}
		float ratio = originalDistance / currentDistance;
		camera.zoom = initialScale * ratio;
		limitSize();
		limitPosition();
		return false;
	}

	private void initLimitSize() {
		float vh = camera.viewportHeight;
		float bh = background.getHeight();
		float vw = camera.viewportWidth;
		float bw = background.getWidth();

		ratioMax = Math.min(bh / vh, bw / vw);
		ratioMin = ratioMax / 4;
	}

	public Background getBackground() {
		return background;
	}

	private void limitSize() {
		if (camera.zoom > ratioMax) {
			camera.zoom = ratioMax;
		} else if (camera.zoom < ratioMin) {
			camera.zoom = ratioMin;
		}

	}

	@Override
	public void onRender(float delta) {
		if (flinging) {
			velX *= 1 - D.CAMMERA_SPEED_DOWN;
			velY *= 1 - D.CAMMERA_SPEED_DOWN;
			float dx = -velX * Gdx.graphics.getDeltaTime();
			float dy = velY * Gdx.graphics.getDeltaTime();
			camera.position.add(dx, dy, 0);
			limitPosition();
			if (Math.abs(velX) < 0.01f)
				velX = 0;
			if (Math.abs(velY) < 0.01f)
				velY = 0;
		}
	}

	private void limitPosition() {
		limitX();
		limitY();
	}

	private void limitY() {
		float minY = getMinY();
		float maxY = getMaxY();
		if (camera.position.y < minY) {
			camera.position.y = minY;
		} else if (camera.position.y > maxY) {
			camera.position.y = maxY;
		}
	}

	private void limitX() {
		float minX = getMinX();
		float maxX = getMaxX();
		if (camera.position.x < minX) {
			camera.position.x = minX;
		} else if (camera.position.x > maxX) {
			camera.position.x = maxX;
		}
	}

	private float getMaxY() {
		return background.getHeight() - getCameraLookHeight() / 2;
	}

	private float getMinY() {
		return getCameraLookHeight() / 2;
	}

	private float getMaxX() {
		return background.getWidth() - getCameraLookWidth() / 2;
	}

	private float getMinX() {
		return getCameraLookWidth() / 2;
	}

	private float getCameraLookWidth() {
		return camera.viewportWidth * camera.zoom;
	}

	private float getCameraLookHeight() {
		return camera.viewportHeight * camera.zoom;
	}
}