package cn.javaplus.shhz.screen;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.App;
import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.events.loading.ProgressBarEndEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 * @author Mats Svensson
 */
public class LoadingScreen implements Screen {

	private Stage stage;

	private Image logo;
	private Image loadingFrame;
	private Image loadingBarHidden;
	private Image screenBg;
	private Image loadingBg;

	private float startX, endX;
	private float percent;

	private Actor loadingBar;

	private App game;

	public LoadingScreen(App game) {
		this.game = game;
	}

	@Override
	public void show() {
		Assets.load("data/loading.pack", TextureAtlas.class);
		Assets.finishLoading();

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		OrthographicCamera c = new OrthographicCamera();
		stage = new Stage(new ScalingViewport(Scaling.stretch, w, h, c), null);

		TextureAtlas atlas = Assets.getTextureAtlas("data/loading.pack");

		logo = new Image(atlas.findRegion("libgdx-logo"));
		loadingFrame = new Image(atlas.findRegion("loading-frame"));
		loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
		screenBg = new Image(atlas.findRegion("screen-bg"));
		loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

		Array<AtlasRegion> findRegions = atlas.findRegions("loading-bar-anim");
		Animation anim = new Animation(0.05f, findRegions);
		anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
		loadingBar = new LoadingBar(anim);

		stage.addActor(screenBg);
		stage.addActor(loadingBar);
		stage.addActor(loadingBg);
		stage.addActor(loadingBarHidden);
		stage.addActor(loadingFrame);
		stage.addActor(logo);
	}

	@Override
	public void resize(int width, int height) {
		width = 480 * width / height;
		height = 480;

		screenBg.setSize(width, height);

		logo.setX((width - logo.getWidth()) / 2);
		logo.setY((height - logo.getHeight()) / 2 + 100);

		loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
		loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

		loadingBar.setX(loadingFrame.getX() + 15);
		loadingBar.setY(loadingFrame.getY() + 5);

		loadingBarHidden.setX(loadingBar.getX() + 35);
		loadingBarHidden.setY(loadingBar.getY() - 3);

		startX = loadingBarHidden.getX();
		endX = 440;

		loadingBg.setSize(450, 50);
		loadingBg.setX(loadingBarHidden.getX() + 30);
		loadingBg.setY(loadingBarHidden.getY() + 3);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH);

		if (Assets.update()) {
			// if (Gdx.input.isTouched()) {
			Events.dispatch(new ProgressBarEndEvent(game, this));
			// }
		}
		float progress = Assets.getProgress();
		percent = Interpolation.linear.apply(percent, progress, 0.1f);
		loadingBarHidden.setX(startX + endX * percent);
		loadingBg.setX(loadingBarHidden.getX() + 30);
		loadingBg.setWidth(450 - 450 * percent);
		loadingBg.invalidate();
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {
		Assets.unload("data/loading.pack");
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
