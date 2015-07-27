package cn.javaplus.shhz.listeners.start;

import cn.javaplus.shhz.Game;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.CreateGameStageEvent;
import cn.javaplus.shhz.input.GameInputProcessor;

public class AddController implements Listener<CreateGameStageEvent> {

	@Override
	public void onEvent(CreateGameStageEvent e) {
		GameInputProcessor processor = Game.getProcessor();
		processor.add(e.getControllerStage());
		processor.add(e.getStage());
	}

}
