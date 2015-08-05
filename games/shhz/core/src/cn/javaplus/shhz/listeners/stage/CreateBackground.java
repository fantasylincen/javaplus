package cn.javaplus.shhz.listeners.stage;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.components.Background;
import cn.javaplus.shhz.events.CreateBackgroundEvent;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.CreateGameStageEvent;
import cn.javaplus.shhz.stage.GameStage;

public class CreateBackground implements Listener<CreateGameStageEvent> {

	@Override
	public void onEvent(CreateGameStageEvent e) {
		GameStage s = e.getStage();
		Background c = new Background(s);
		CreateBackgroundEvent evt = new CreateBackgroundEvent(c, e);
		Events.dispatch(evt);
		s.setBackground(c);
		c.toBack();
	}

}
