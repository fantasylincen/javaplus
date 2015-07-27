package cn.javaplus.jigsaw;

import org.javaplus.game.common.Ads;
import org.javaplus.game.common.Configs;
import org.javaplus.game.common.Game;
import org.javaplus.game.common.GameAssets;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.Logger;
import org.javaplus.game.common.OS;
import org.javaplus.game.common.Share;
import org.javaplus.game.common.channel.Channel;
import org.javaplus.game.common.messagebox.MessageBox;
import org.javaplus.game.common.stage.IStage;

import cn.javaplus.jigsaw.channel.DefaultChannel;
import cn.javaplus.jigsaw.events.Events;
import cn.javaplus.jigsaw.game.GameStage;
import cn.javaplus.jigsaw.game.GameStartEvent;
import cn.javaplus.jigsaw.game.RestartEvent;

public class App extends Game {

	private static OS os;

	static App app;

	private static GameAssets gameAssets;

	private static Channel channel;

	public static void setOs(OS os) {
		App.os = os;
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

	public static void setChannel(Channel channel) {
		App.channel = channel;
	}

	public static Channel getChannel() {
		if (channel == null) {
			channel = new DefaultChannel();
		}
		return channel;
	}
}
