package cn.javaplus.twolegs;

import org.javaplus.game.common.Ads;
import org.javaplus.game.common.Configs;
import org.javaplus.game.common.Game;
import org.javaplus.game.common.GameAssets;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.Logger;
import org.javaplus.game.common.OS;
import org.javaplus.game.common.Share;
import org.javaplus.game.common.cache.RemoteResources;
import org.javaplus.game.common.channel.Channel;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.HttpClient;
import org.javaplus.game.common.messagebox.MessageBox;
import org.javaplus.game.common.stage.IStage;

import cn.javaplus.twolegs.define.Events;
import cn.javaplus.twolegs.game.GameStage;
import cn.javaplus.twolegs.game.GameStartEvent;
import cn.javaplus.twolegs.game.RestartEvent;

public class App extends Game {

	private static OS os;

	static App app;

	private static GameAssets gameAssets;

	public static void setOs(OS os) {
		App.os = os;
	}

	public static TwoLegsHttpClient getHttp() {
		return os.getHttp();
	}

	public static Ads getAds() {
		return os.getAds();
	}

	public static OS getOs() {
		return os;
	}

	@Override
	public void create() {
		GameStage stage = new GameStage();
		setStage(stage);
		Events.dispatch(new GameStartEvent(this));
		getOs().create();
//		getCache().update();
	}

	public static App getApp() {
		if (app == null) {
			app = new App();
		}
		return app;
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

	public static Channel getChannel() {
		return os.getChannel();
	}

	public static RemoteResources getCache() {
		return os.getCache();
	}

//	public static ScriptManagerAsync getScriptManagerAsync() {
//		return os.getScriptMangerAsync();
//	}
//	public static ScriptManager getScriptManager() {
//		return os.getScriptManger();
//	}

	public static HttpClient getHttpBase() {
		return os.getHttpBase();
	}

}
