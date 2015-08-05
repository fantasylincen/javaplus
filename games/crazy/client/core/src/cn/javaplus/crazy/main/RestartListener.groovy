package cn.javaplus.crazy.main;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.Protocols;
import cn.javaplus.crazy.Protocols.PlayHandler;

public class RestartListener extends ButtonListener {

	@Override
	protected void onClick() {
		Protocols client = AppContext.getClient();
		PlayHandler handler = client.getPlayHandler();
		handler.restartGame(null);
	}

}
