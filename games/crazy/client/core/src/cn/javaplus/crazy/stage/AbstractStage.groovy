package cn.javaplus.crazy.stage;

import cn.javaplus.crazy.login.MessageBox

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport

public abstract class AbstractStage extends Stage implements IStage {

	public AbstractStage() {
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
