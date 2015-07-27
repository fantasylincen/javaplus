package cn.javaplus.crazy;

import cn.javaplus.crazy.base.GameClient
import cn.javaplus.crazy.config.Node
import cn.javaplus.crazy.config.NodeArray
import cn.javaplus.crazy.config.ServerJson
import cn.javaplus.crazy.events.Events
import cn.javaplus.crazy.game.GameStartEvent
import cn.javaplus.crazy.http.HttpAsyncClient
import cn.javaplus.crazy.log.Log
import cn.javaplus.crazy.login.LoginStage
import cn.javaplus.crazy.login.MessageBox
import cn.javaplus.crazy.stage.IStage

public class App extends Game {

	public void create() {
		AppContext.setApp(this);
		setStage(new LoginStage());
		Events.dispatch(new GameStartEvent(this));
	}

	public static class AppContext {

		static HttpAsyncClient http;
		static Protocols client;
		static App app;

		public static void ensureInitGameClient() {
			if (client != null) {
				return;
			}

			Node node = ServerJson.getRoot();
			NodeArray array = node.getArray("servers");
			node = array.find("id", 10001);

			int p = node.getInt("port");
			String host = node.getString("ip");
			Log.d("Game.ensureInitGameClient ", host, p);
			GameClient client = new GameClient(host, p);
			client.connect();
			AppContext.client = client.getMessageSender();
		}

		public static MessageBox getMessageBox() {
			IStage stage = app.getStage();
			return stage.getMessageBox();
		}

		public static HttpAsyncClient getHttpClient() {
			return http;
		}

		public static Protocols getClient() {
			return client;
		}

		public static App getApp() {
			return app;
		}

		public static IStage getStage() {
			return app.getStage();
		}

		public static void setStage(IStage stage) {
			app.setStage(stage);
		}
	}

	public int getW() {
		return (int) (D.STAGE_W *0.6);
	}

	public int getH() {
		return (int) (D.STAGE_H *0.6);
	}
}
