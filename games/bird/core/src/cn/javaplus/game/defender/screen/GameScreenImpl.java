package cn.javaplus.game.defender.screen;

import java.util.List;

import cn.javaplus.common.util.Util;
import cn.javaplus.game.defender.App;
import cn.javaplus.game.defender.debug.Debuger;
import cn.javaplus.game.defender.stage.GameStage;
import cn.javaplus.game.defender.stage.GameStageImpl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.google.common.collect.Lists;

public class GameScreenImpl extends ScreenAdapter implements GameScreen {

	private GameStage stage;
	private int w;
	private int h;
	private final List<EventListener> listeners = Lists.newArrayList();
	private Box2DDebugRenderer renderer;
	private Matrix4 combined;
	private boolean isAssetsFinish;

	public GameScreenImpl(int w, int h) {
		this.w = w;
		this.h = h;
		stage = new ProgressStage(w, h);
		createDebuger();
	}

	private void createDebuger() {
		renderer = new Box2DDebugRenderer();
		// renderer.setDrawAABBs(true);
		renderer.setDrawBodies(true);
		// renderer.setDrawContacts(true);
		// renderer.setDrawInactiveBodies(true);
		renderer.setDrawJoints(true);
		renderer.setDrawVelocities(true);

	}

	@Override
	public boolean addListener(EventListener listener) {
		listeners.add(listener);
		return true;
	}

	@Override
	public void render(float delta) {

		if (combined == null) {

			updateCombined();
		}
		App.getWorld().step(delta, 6, 2);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());
		renderer.render(App.getWorld(), combined);

		if (!isAssetsFinish) {
			if (App.getAssetsManager().update()) {
				Debuger.debug("GameScreenImpl.render() 资源加载完成");
				stage = new GameStageImpl(w, h);
				updateCombined();
				isAssetsFinish = true;
			} else {
				Debuger.debug("GameScreenImpl.render() 正在加载资源, 进度:"
						+ App.getAssetsManager().getProgress() * 100 + "%");
			}
		}
	}

	private void updateCombined() {
		combined = App.getCurrentCamera().getCombined();
		combined = new Matrix4(combined);
		float w2 = Util.getStageToWorld(App.getStageWidth());
		float h2 = Util.getStageToWorld(App.getStageHeight());
		combined.setToOrtho2D(0, 0, w2, h2);
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
