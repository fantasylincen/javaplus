package cn.javaplus.crazy.hall;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.login.EnterGameEvent;

public class ChangeToHallStage implements Listener<EnterGameEvent> {

	@Override
	public void onEvent(EnterGameEvent e) {
		HallStage stage = new HallStage();
		AppContext.setStage(stage);
	}

}
