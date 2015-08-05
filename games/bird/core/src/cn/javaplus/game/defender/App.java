package cn.javaplus.game.defender;

import cn.javaplus.game.defender.assets.GameAssetsManager;
import cn.javaplus.game.defender.camera.GameCamera;
import cn.javaplus.game.defender.record.Record;
import cn.javaplus.game.defender.record.RecordImpl;
import cn.javaplus.game.defender.screen.GameScreen;
import cn.javaplus.game.defender.screen.GameScreenImpl;
import cn.javaplus.game.defender.stage.GameInputProcessor;
import cn.javaplus.game.defender.stage.GameInputProcessorImpl;
import cn.javaplus.game.defender.stage.GameStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class App extends com.badlogic.gdx.Game {

	private static final int H = 480;
	private static final int W = 800;
	private static GameInputProcessor input;
	private static GameScreen currentScreen;
	private static Record record;
	private static GameAssetsManager assetsManager;

	private static World world;

	public App(String assetsRoot) {
		assetsManager = new GameAssetsManager();
		assetsManager.setAssetsRoot(assetsRoot);
		assetsManager.load();
		input = new GameInputProcessorImpl();
	}

	public static GameAssetsManager getAssetsManager() {
		return assetsManager;
	}

	@Override
	public void create() {
		world = new World(new Vector2(0, -9.8f), true);
		currentScreen = new GameScreenImpl(W, H);
		setScreen(currentScreen);
		record = new RecordImpl();
		Gdx.input.setInputProcessor(input);
	}

	public static GameInputProcessor getCurrentInput() {
		return input;
	}

	public static World getWorld() {
		return world;
	}

	public static GameScreen getCurrentScreen() {
		return currentScreen;
	}

	public static GameStage getCurrentStage() {
		return getCurrentScreen().getStage();
	}

	public static int getStageWidth() {
		return W;
	}

	public static int getStageHeight() {
		return H;
	}

	public static GameCamera getCurrentCamera() {
		return getCurrentStage().getCamera();
	}

	public static Record getRecord() {
		return record;
	}
}