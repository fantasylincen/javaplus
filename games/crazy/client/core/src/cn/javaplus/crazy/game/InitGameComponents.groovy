package cn.javaplus.crazy.game;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.D;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.http.HttpAsyncClient;

public class InitGameComponents implements Listener<GameStartEvent> {

	@Override
	public void onEvent(GameStartEvent e) {
		AppContext.setHttp(new HttpAsyncClient(new GdxNet(), D.HTTP_TIME_OUT));
	}

}
