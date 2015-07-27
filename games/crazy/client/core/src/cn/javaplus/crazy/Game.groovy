package cn.javaplus.crazy;

import java.util.concurrent.ConcurrentLinkedQueue;

import cn.javaplus.crazy.assets.Assets;
import cn.javaplus.crazy.input.GameInputProcessor;
import cn.javaplus.crazy.stage.IStage;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public abstract class Game implements ApplicationListener {

	private IStage currentStage;
	private IStage stageWillChange;

	private boolean isLoadOver;
	private ConcurrentLinkedQueue<Runnable> runnables = new ConcurrentLinkedQueue<Runnable>();

	public IStage getStage() {
		return currentStage;
	}

	private void renderStage(float delta) {
		if (currentStage != null) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			currentStage.draw();
			currentStage.act(delta);
		}
	}

	@Override
	public void pause() {
		if (currentStage != null) {
			currentStage.pause();
		}
	}

	@Override
	public void resume() {
		if (currentStage != null) {
			currentStage.resume();
		}
	}

	@Override
	public void resize(int width, int height) {
		if (currentStage != null) {
			currentStage.resize(width, height);
		}
	}

	@Override
	public void render() {
		changeStage();
		Gdx.gl.glClear(GL20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH);

		if (Assets.update()) {
			if (!isLoadOver) {
				this.currentStage.getCocosUI().build();
				this.currentStage.onLoadingOver();
				isLoadOver = true;
			}
		} else {
			float progress = Assets.getProgress();
			this.currentStage.onLoading(progress);
		}

		float deltaTime = Gdx.graphics.getDeltaTime();
		renderStage(deltaTime);
		excuteRunables();
	}

	private void excuteRunables() {
		while(!runnables.isEmpty()) {
			Runnable poll = runnables.poll();
			try {
				poll.run();
			} catch (RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void append(Runnable runnable) {
		runnables.add(runnable);
	}

	private void changeStage() {
		if (stageWillChange != null) {
			if (this.currentStage != null) {
				this.currentStage.getCocosUI().unload();
				this.currentStage.unloadAssets();
				this.currentStage.hide();
			}
			this.currentStage = stageWillChange;
			if (this.currentStage != null) {
				this.currentStage.getCocosUI().load();
				this.currentStage.loadAssets();
				this.currentStage.show();
				int width = Gdx.graphics.getWidth();
				int height = Gdx.graphics.getHeight();
				this.currentStage.resize(width, height);
			}
			stageWillChange = null;
		}
	}

	@Override
	public final void dispose() {
		currentStage.unloadAssets();
		currentStage.dispose();
	}

	public void setStage(IStage stageWillChange) {
		isLoadOver = false;
		this.stageWillChange = stageWillChange;

		setInput(stageWillChange);
	}

	private void setInput(IStage stageWillChange) {
		GameInputProcessor processor = new GameInputProcessor();
		processor.set(stageWillChange);
		Gdx.input.setInputProcessor(processor);
	}
}
