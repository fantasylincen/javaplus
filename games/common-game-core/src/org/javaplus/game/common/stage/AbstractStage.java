package org.javaplus.game.common.stage;

import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.messagebox.MessageBox;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class AbstractStage extends Stage implements IStage {

	private static ScalingViewport create(float w, float h) {
		OrthographicCamera c = new OrthographicCamera();
		return new ScalingViewport(Scaling.stretch, w, h, c);
	}
	
	public AbstractStage(float w, float h) {
		this(create(w, h));
	}

	public AbstractStage(Viewport viewport) {
		super(viewport);
	}

	public AbstractStage(Viewport viewport, Batch batch) {
		super(viewport, batch);
	}

	@Override
	public void show() {

	}
	public final boolean contains(Actor actor) {
		return getActors().contains(actor, true);
	}

	@Override
	public MessageBox getMessageBox() {
		return new MessageBox() {

			@Override
			public void showMessage(String text) {
				Log.d(text);
			}
		};
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void loadAssets() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void unloadAssets() {

	}

	@Override
	public void onLoading(float progress) {

	}
	
	@Override
	public void onLoadingOver() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

}
