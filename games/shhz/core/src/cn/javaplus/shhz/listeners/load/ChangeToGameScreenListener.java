package cn.javaplus.shhz.listeners.load;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.App;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.loading.ProgressBarEndEvent;
import cn.javaplus.shhz.events.screen.CreateGameScreenEvent;
import cn.javaplus.shhz.screen.GameScreen;

public class ChangeToGameScreenListener implements
		Listener<ProgressBarEndEvent> {

	@Override
	public void onEvent(ProgressBarEndEvent e) {
		App game = e.getGame();
		GameScreen screen = new GameScreen(game);
		game.setScreen(screen);
		Events.dispatch(new CreateGameScreenEvent(screen, game));
	}

}
