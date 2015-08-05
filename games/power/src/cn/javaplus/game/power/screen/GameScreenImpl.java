package cn.javaplus.game.power.screen;

import java.util.List;

import cn.javaplus.game.power.Game;
import cn.javaplus.game.power.events.GameStartEvent;
import cn.javaplus.game.power.record.RecordImpl;
import cn.javaplus.game.power.stage.GameInputProcessorImpl;
import cn.javaplus.game.power.stage.GameModel;
import cn.javaplus.game.power.stage.GameStage;
import cn.javaplus.game.power.stage.GameStageImpl;
import cn.mxz.events.Events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.google.common.collect.Lists;

public class GameScreenImpl extends ScreenAdapter implements GameScreen {

	private GameStage stage;
	private int w;
	private int h;
	private final List<EventListener> listeners = Lists.newArrayList();
	private boolean isAssetsFinish;

	public GameScreenImpl(int w, int h) {
		this.w = w;
		this.h = h;
		stage = new ProgressStage(w, h);
	}

	@Override
	public boolean addListener(EventListener listener) {
		listeners.add(listener);
		return true;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());

		if (!isAssetsFinish) {
			if (Game.getAssetsManager().update()) {
				stage = new GameStageImpl(w, h);
				gameStart();
				isAssetsFinish = true;
			}
		}
	}

	private void gameStart() {
		GameModel gameModel = new GameModel();
		RecordImpl record = new RecordImpl();
		GameInputProcessorImpl input = new GameInputProcessorImpl();
		Gdx.input.setInputProcessor(input);
		Events.getInstance().dispatch(new GameStartEvent(input, this, w, h, record, Game.getAssetsManager(), gameModel));
	}

	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public int getHeight() {
		return h;
	}

	@Override
	public GameStage getStage() {
		return stage;
	}
}
