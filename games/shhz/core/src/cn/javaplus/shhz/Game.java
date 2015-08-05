package cn.javaplus.shhz;

import cn.javaplus.shhz.input.GameInputProcessor;
import cn.javaplus.shhz.screen.ControllerStage;
import cn.javaplus.shhz.screen.GameScreen;
import cn.javaplus.shhz.stage.GameStage;
import cn.javaplus.shhz.user.User;
import cn.javaplus.shhz.util.MessageBox;

import com.badlogic.gdx.Screen;

public class Game {

	private static User user;
	private static MessageBox messageBox;
	private static App app;
	
	public static User getUser() {
		return user;
	}

	public static MessageBox getMessageBox() {
		return messageBox;
	}

	public static GameStage getGameStage() {
		Screen screen = app.getScreen();
		if (screen instanceof GameScreen) {
			GameScreen s = (GameScreen) screen;
			return s.getGameStage();
		}
		return null;
	}

	public static ControllerStage getControllerStage() {
		Screen screen = app.getScreen();
		if (screen instanceof GameScreen) {
			GameScreen s = (GameScreen) screen;
			return s.getControllerStage();
		}
		return null;
	}

	public static void onCreate(App app) {
		Game.app = app;
		messageBox = new MessageBox();		
		user = new User();
	}

	public static GameInputProcessor getProcessor() {
		return app.getProcessor();
	}
}
