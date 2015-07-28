package cn.javaplus.game.power;

import cn.javaplus.game.power.assets.GameAssetsManager;
import cn.javaplus.game.power.camera.GameCamera;
import cn.javaplus.game.power.screen.GameScreen;
import cn.javaplus.game.power.screen.GameScreenImpl;
import cn.javaplus.game.power.stage.GameStage;

public class Game extends com.badlogic.gdx.Game {

	private static final int H = 1024 * 14 / 4;
	private static final int W = 1024 * 14 / 4;
	private static GameScreen currentScreen;
	private static GameAssetsManager assetsManager;


	public Game(String assetsRoot) {
		assetsManager = new GameAssetsManager();
		assetsManager.setAssetsRoot(assetsRoot);
		assetsManager.load();
	}

	public static GameAssetsManager getAssetsManager() {
		return assetsManager;
	}

	@Override
	public void create() {
		currentScreen = new GameScreenImpl(W, H);
		setScreen(currentScreen);

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
}