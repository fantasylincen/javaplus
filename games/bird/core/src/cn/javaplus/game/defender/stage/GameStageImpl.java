package cn.javaplus.game.defender.stage;

import cn.javaplus.game.defender.camera.GameCamera;
import cn.javaplus.game.defender.camera.GameCameraImpl;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.ICamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.IGroup;
import com.badlogic.gdx.scenes.scene2d.IStage;
import com.badlogic.gdx.utils.Array;

public class GameStageImpl implements GameStage {

	private IStage stage;

	private GameCamera camera;


	public GameStageImpl(int w, int h) {
		stage = new com.badlogic.gdx.scenes.scene2d.Stage(w, h);
		camera = new GameCameraImpl(stage.getCamera());

		addActor(new BackGround());
		addActor(new BirdLayer());
	}

	@Override
	public void act(float delta) {
		stage.act(delta);
	}

	@Override
	public void setCamera(ICamera camera) {
		stage.setCamera(camera);
		this.camera = new GameCameraImpl(camera);
	}

	@Override
	public GameCamera getCamera() {
		return camera;
	}

	@Override
	public void addActor(IActor actor) {
		stage.addActor(actor);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return stage.touchDown(screenX, screenY, pointer, button);
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return stage.touchDragged(screenX, screenY, pointer);
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return stage.touchUp(screenX, screenY, pointer, button);
	}

	public void draw() {
		stage.draw();
		getRoot().fire(new UpdateEvent(this));
	}

	public void act() {
		stage.act();
	}

	public void addTouchFocus(EventListener listener, IActor listenerActor,
			IActor target, int pointer, int button) {
		stage.addTouchFocus(listener, listenerActor, target, pointer, button);
	}

	public void cancelTouchFocus() {
		stage.cancelTouchFocus();
	}

	public void cancelTouchFocus(EventListener listener, IActor actor) {
		stage.cancelTouchFocus(listener, actor);
	}

	public void addAction(Action action) {
		stage.addAction(action);
	}

	public Array<IActor> getActors() {
		return stage.getActors();
	}

	public boolean addListener(EventListener listener) {
		return stage.addListener(listener);
	}

	public boolean addCaptureListener(EventListener listener) {
		return stage.addCaptureListener(listener);
	}

	public void clear() {
		stage.clear();
	}

	public IActor getKeyboardFocus() {
		return stage.getKeyboardFocus();
	}

	public IActor getScrollFocus() {
		return stage.getScrollFocus();
	}

	public float getWidth() {
		return stage.getWidth();
	}

	public float getHeight() {
		return stage.getHeight();
	}

	public void dispose() {
		stage.dispose();
	}

	public float getGutterWidth() {
		return stage.getGutterWidth();
	}

	public float getGutterHeight() {
		return stage.getGutterHeight();
	}

	public Batch getSpriteBatch() {
		return stage.getSpriteBatch();
	}

	public IGroup getRoot() {
		return stage.getRoot();
	}

	public void calculateScissors(Rectangle area, Rectangle scissor) {
		stage.calculateScissors(area, scissor);
	}

	public boolean equals(Object obj) {
		return stage.equals(obj);
	}

	public int hashCode() {
		return stage.hashCode();
	}

	public void setViewport(float width, float height) {
		stage.setViewport(width, height);
	}

	public void setViewport(float width, float height, boolean keepAspectRatio) {
		stage.setViewport(width, height, keepAspectRatio);
	}

	public void setViewport(float stageWidth, float stageHeight,
			boolean keepAspectRatio, float viewportX, float viewportY,
			float viewportWidth, float viewportHeight) {
		stage.setViewport(stageWidth, stageHeight, keepAspectRatio, viewportX,
				viewportY, viewportWidth, viewportHeight);
	}

	public boolean mouseMoved(int screenX, int screenY) {
		stage.mouseMoved(screenX, screenY);
		return false;
	}

	public boolean scrolled(int amount) {
		return stage.scrolled(amount);
	}

	public boolean keyDown(int keyCode) {
		return stage.keyDown(keyCode);
	}

	public boolean keyUp(int keyCode) {
		return stage.keyUp(keyCode);
	}

	public boolean keyTyped(char character) {
		return stage.keyTyped(character);
	}

	public void removeTouchFocus(EventListener listener, IActor listenerActor,
			IActor target, int pointer, int button) {
		stage.removeTouchFocus(listener, listenerActor, target, pointer, button);
	}

	public boolean removeListener(EventListener listener) {
		return stage.removeListener(listener);
	}

	public boolean removeCaptureListener(EventListener listener) {
		return stage.removeCaptureListener(listener);
	}

	public void unfocusAll() {
		stage.unfocusAll();
	}

	public void unfocus(IActor actor) {
		stage.unfocus(actor);
	}

	public void setKeyboardFocus(IActor actor) {
		stage.setKeyboardFocus(actor);
	}

	public void setScrollFocus(IActor actor) {
		stage.setScrollFocus(actor);
	}

	public void setCamera(Camera camera) {
		stage.setCamera(camera);
	}

	public IActor hit(float stageX, float stageY, boolean touchable) {
		return stage.hit(stageX, stageY, touchable);
	}

	public Vector2 screenToStageCoordinates(Vector2 screenCoords) {
		return stage.screenToStageCoordinates(screenCoords);
	}

	public Vector2 stageToScreenCoordinates(Vector2 stageCoords) {
		return stage.stageToScreenCoordinates(stageCoords);
	}

	public Vector2 toScreenCoordinates(Vector2 coords, Matrix4 transformMatrix) {
		return stage.toScreenCoordinates(coords, transformMatrix);
	}

	public String toString() {
		return stage.toString();
	}
}
