package org.hhhhhh.fqzs.core;

import org.hhhhhh.fqzs.event.Events;
import org.hhhhhh.fqzs.event.RestartEvent;
import org.hhhhhh.fqzs.welcome.LoadOverListener;
import org.hhhhhh.fqzs.welcome.WelcomeStage;
import org.javaplus.clickscreen.excel.StaticData;
import org.javaplus.clickscreen.excel.XmlDataImpl;
import org.javaplus.game.common.Ads;
import org.javaplus.game.common.Configs;
import org.javaplus.game.common.Game;
import org.javaplus.game.common.GameAssets;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.Logger;
import org.javaplus.game.common.OS;
import org.javaplus.game.common.Share;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.messagebox.MessageBox;
import org.javaplus.game.common.stage.IStage;

import com.badlogic.gdx.ApplicationListener;

public class App extends Game {

	private static OS os;

	static App app;

	private static GameAssets gameAssets;

	private static XmlDataImpl staticData;

	public static void setOs(OS os) {
		App.os = os;
	}

	@Override
	public GameStage getStage() {
		return (GameStage) super.getStage();
	}

	public static Ads getAds() {
		return os.getAds();
	}

	public static OS getOs() {
		return os;
	}

	@Override
	public void create() {
		WelcomeStage stage = new WelcomeStage();
		stage.addLoadOverListener(new LoadOverListener() {

			@Override
			public void onLoadOver() {
				runInMainThread(new Runnable() {
					public void run() {
						changeToGameStage();
					}
				});
			}
		});
		setStage(stage);
	}

	private void changeToGameStage() {
		GameStage stage = new GameStage();
		setStage(stage);
	}

	public static App getApp() {
		if (app == null) {
			app = new App();
		}
		return app;
	}

	@Override
	public void setStage(IStage stageWillChange) {
		super.setStage(stageWillChange);
	}

	public static MessageBox getMessageBox() {
		IStage stage = app.getStage();
		return stage.getMessageBox();
	}

	public void restart() {
		App.getApp().setStage(new GameStage());
		Events.dispatch(new RestartEvent());
		App.getLogger().onCountEvent("restart");
	}

	public static Logger getLogger() {
		return os.getLogger();
	}

	public static GameAssets getAssets() {
		if (gameAssets == null)
			gameAssets = new GameAssets();
		return gameAssets;
	}

	public static Configs getConfigs() {
		return os.getConfigs();
	}

	public static Share getShare() {
		return os.getShare();
	}

	public static IPreferences getPreferences() {
		return os.getPreferences();
	}

	public static StaticData getStaticData() {
		if (staticData == null) {
			staticData = new XmlDataImpl(Assets.getDefaultLoader());
		}
		return staticData;
	}

}
