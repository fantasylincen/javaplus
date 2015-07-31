package org.hhhhhh.fqzs.core;

import org.hhhhhh.fqzs.config.GameConfig;
import org.hhhhhh.fqzs.event.Events;
import org.hhhhhh.fqzs.event.RestartEvent;
import org.hhhhhh.fqzs.http.FqzsClient;
import org.hhhhhh.fqzs.http.FqzsClientAdaptor;
import org.hhhhhh.fqzs.login.AppProperties;
import org.hhhhhh.fqzs.login.LoginEvent;
import org.hhhhhh.fqzs.login.LoginListener;
import org.hhhhhh.fqzs.login.LoginUI;
import org.hhhhhh.fqzs.login.RoleSelectedEvent;
import org.hhhhhh.fqzs.login.RoleUI;
import org.hhhhhh.fqzs.login.RoleUIListener;
import org.hhhhhh.fqzs.message.MessageUI;
import org.hhhhhh.fqzs.result.RoleData;
import org.hhhhhh.fqzs.welcome.LoadOverEvent;
import org.hhhhhh.fqzs.welcome.LoadOverListener;
import org.hhhhhh.fqzs.welcome.WelcomeStage;
import org.javaplus.clickscreen.excel.StaticData;
import org.javaplus.clickscreen.excel.XmlDataImpl;
import org.javaplus.game.common.Game;
import org.javaplus.game.common.GameAssets;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.http.HttpAsyncClient;
import org.javaplus.game.common.messagebox.MessageBox;
import org.javaplus.game.common.stage.IStage;

public class App extends Game {

	static App app;

	private static GameAssets gameAssets;

	private static XmlDataImpl staticData;

	private LoginUI loginUI;

	private MessageUI messageUI;

	static FqzsClient http;

	private static GameConfig config;

	private static AppProperties gateConfig;

	private static UserData userData;

	private RoleUI roleUI;

	private static RoleData roleData;

	private static Translator translator;

	public static FqzsClient getHttp() {
		if (http == null)
			http = new FqzsClientAdaptor(new HttpAsyncClient(5000));
		return http;
	}

	@Override
	public GameStage getStage() {
		return (GameStage) super.getStage();
	}

	@Override
	public void create() {
		WelcomeStage stage = new WelcomeStage();
		stage.addLoadOverListener(new LoadOverListener() {

			@Override
			public void onLoadOver(LoadOverEvent e) {
				runInMainThread(new Runnable() {
					public void run() {
						showLoginUi();
					}
				});
			}
		});
		setStage(stage);
	}

	private void showLoginUi() {
		loginUI.addListener(new LoginListener() {

			@Override
			public void onLoginSuccess(final LoginEvent e) {
				runInMainThread(new Runnable() {
					public void run() {
						showRoleUi(e.getId(), e.getToken());
					}

				});
			}

		});
		loginUI.show();
	}

	private void showRoleUi(String id, String token) {

		roleUI.addListener(new RoleUIListener() {

			@Override
			public void onRoleSelected(RoleSelectedEvent e) {
				runInMainThread(new Runnable() {
					public void run() {
						changeToGameStage();
					}

				});
			}

		});
		roleUI.show(id, token);
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
	}

	public static GameAssets getAssets() {
		if (gameAssets == null)
			gameAssets = new GameAssets();
		return gameAssets;
	}

	public static StaticData getStaticData() {
		if (staticData == null) {
			staticData = new XmlDataImpl(Assets.getDefaultLoader());
		}
		return staticData;
	}

	public void setLoginUI(LoginUI loginUI) {
		this.loginUI = loginUI;
	}

	public void setMessageUI(MessageUI messageUI) {
		this.messageUI = messageUI;
	}

	public static void setConfig(GameConfig config) {
		App.config = config;
	}

	public static GameConfig getConfig() {
		return config;
	}

	public static AppProperties getProperties() {
		if (gateConfig == null)
			gateConfig = new AppProperties();
		return gateConfig;
	}

	public void setRoleUI(RoleUI roleUI) {
		this.roleUI = roleUI;
	}

	public RoleUI getRoleUI() {
		return roleUI;
	}

	public static UserData getUserData() {
		if (userData == null)
			userData = new UserData();
		return userData;
	}

	public static void setRoleData(RoleData roleData) {
		App.roleData = roleData;

	}

	public static RoleData getRoleData() {
		return roleData;
	}

	public static Translator getTranslator() {
		if(translator == null)
			translator = new Translator();
		return translator;
	}
}
