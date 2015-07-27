package cn.javaplus.game.defender.screen;

import cn.javaplus.game.defender.camera.GameCamera;
import cn.javaplus.game.defender.camera.GameCameraImpl;
import cn.javaplus.game.defender.stage.GameStage;

import com.badlogic.gdx.graphics.ICamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.IGroup;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class ProgressStage implements GameStage {

	// + Game.getAssetsManager().getProgress() * 100 + "%"
	Stage stage;

	private GameCameraImpl camera;

	public ProgressStage(int w, int h) {
		stage = new Stage(w, h);
		camera = new GameCameraImpl(stage.getCamera());
		stage.setCamera(camera);
	}
	
	public void setCamera(ICamera camera) {
		camera = new GameCameraImpl(camera);
		stage.setCamera(camera);
	}

	public void draw() {
		stage.draw();
	}

	public void act() {
		stage.act();
	}

	public void act(float delta) {
		stage.act(delta);
	}

	public boolean equals(Object arg0) {
		return stage.equals(arg0);
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

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return stage.touchDown(screenX, screenY, pointer, button);
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return stage.touchDragged(screenX, screenY, pointer);
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return stage.touchUp(screenX, screenY, pointer, button);
	}

	public boolean mouseMoved(int screenX, int screenY) {
		return stage.mouseMoved(screenX, screenY);
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

	public void addTouchFocus(EventListener listener, IActor listenerActor,
			IActor target, int pointer, int button) {
		stage.addTouchFocus(listener, listenerActor, target, pointer, button);
	}

	public void removeTouchFocus(EventListener listener, IActor listenerActor,
			IActor target, int pointer, int button) {
		stage.removeTouchFocus(listener, listenerActor, target, pointer, button);
	}

	public void cancelTouchFocus() {
		stage.cancelTouchFocus();
	}

	public void cancelTouchFocus(EventListener listener, IActor actor) {
		stage.cancelTouchFocus(listener, actor);
	}

	public void addActor(IActor actor) {
		stage.addActor(actor);
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

	public boolean removeListener(EventListener listener) {
		return stage.removeListener(listener);
	}

	public boolean addCaptureListener(EventListener listener) {
		return stage.addCaptureListener(listener);
	}

	public boolean removeCaptureListener(EventListener listener) {
		return stage.removeCaptureListener(listener);
	}

	public void clear() {
		stage.clear();
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

	public IActor getKeyboardFocus() {
		return stage.getKeyboardFocus();
	}

	public void setScrollFocus(IActor actor) {
		stage.setScrollFocus(actor);
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

	public float getGutterWidth() {
		return stage.getGutterWidth();
	}

	public float getGutterHeight() {
		return stage.getGutterHeight();
	}

	public Batch getSpriteBatch() {
		return stage.getSpriteBatch();
	}

	public GameCamera getCamera() {
		return camera;
	}


	public IGroup getRoot() {
		return stage.getRoot();
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

	public void calculateScissors(Rectangle area, Rectangle scissor) {
		stage.calculateScissors(area, scissor);
	}

	public void dispose() {
		stage.dispose();
	}

	public String toString() {
		return stage.toString();
	}

}
