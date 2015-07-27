package cn.javaplus.shhz.listeners.stage;

import cn.javaplus.shhz.components.info.InfomationPanel;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.CreateGameStageEvent;
import cn.javaplus.shhz.stage.GameStage;

public class CreateHead implements Listener<CreateGameStageEvent> {

	@Override
	public void onEvent(CreateGameStageEvent e) {
		GameStage s = e.getStage();
		InfomationPanel c = new InfomationPanel(s);
		s.setInfomationPanel(c);
	}

}
