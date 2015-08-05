package cn.javaplus.shhz.listeners.start;

import cn.javaplus.game.shhz.AssetsLoader;
import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.App;
import cn.javaplus.shhz.events.GameStartEvent;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.screen.CreateLoadingScreenEvent;
import cn.javaplus.shhz.screen.LoadingScreen;

public class CreateScreen implements Listener<GameStartEvent> {

	@Override
	public void onEvent(GameStartEvent e) {
		App game = e.getGame();
		LoadingScreen screen = new LoadingScreen(game);
		game.setScreen(screen);
		Events.dispatch(new CreateLoadingScreenEvent(screen, game));
		AssetsLoader.loadStart();
		// Assets.finishLoading();
		// Events.dispatch(new FinishLoadingEvent(screen, game));
	}

}
