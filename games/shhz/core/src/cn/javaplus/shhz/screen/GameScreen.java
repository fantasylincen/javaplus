package cn.javaplus.shhz.screen;

import java.util.List;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.App;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.events.screen.GameScreenDisposeEvent;
import cn.javaplus.shhz.events.screen.GameScreenHideEvent;
import cn.javaplus.shhz.events.screen.GameScreenPauseEvent;
import cn.javaplus.shhz.events.screen.GameScreenResizeEvent;
import cn.javaplus.shhz.events.screen.GameScreenResumeEvent;
import cn.javaplus.shhz.events.screen.GameScreenShowEvent;
import cn.javaplus.shhz.events.stage.CreateGameStageEvent;
import cn.javaplus.shhz.stage.GameStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen {

	GameStage gameStage;

	ControllerStage controllerStage;

	private App game;

	private List<RenderListener> listeners = Lists.newArrayList();

	public GameScreen(App game) {
		this.game = game;
		gameStage = new GameStage();
		controllerStage = new ControllerStage();
		Events.dispatch(new CreateGameStageEvent(game, this));
	}

	public App getGame() {
		return game;
	}

	@Override
	public void pause() {
		Events.dispatch(new GameScreenPauseEvent(this));
	}

	@Override
	public void hide() {
		Events.dispatch(new GameScreenHideEvent(this));
	}

	@Override
	public void dispose() {
		gameStage.dispose();
		controllerStage.dispose();
		Events.dispatch(new GameScreenDisposeEvent(this));
	}

	@Override
	public void resume() {
		Events.dispatch(new GameScreenResumeEvent(this));
	}

	@Override
	public void resize(int width, int height) {
		Events.dispatch(new GameScreenResizeEvent(this, width, height));
	}

	@Override
	public void show() {
		Events.dispatch(new GameScreenShowEvent(this));
	}

	public GameStage getGameStage() {
		return gameStage;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		actStage(delta);
		for (RenderListener l : listeners) {
			l.onRender(delta);
		}
	}

	public ControllerStage getControllerStage() {
		return controllerStage;
	}

	private void actStage(float delta) {
		act(gameStage, delta);
		act(controllerStage, delta);
	}

	private void act(Stage s, float delta) {
		s.draw();
		s.act(delta);
	}

	public void addRenderListener(RenderListener lis) {
		listeners.add(lis);
	}
}
