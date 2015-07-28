package cn.javaplus.shhz.listeners.building;

import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.CreateGameStageEvent;
import cn.javaplus.shhz.listeners.stage.RootMenu;
import cn.javaplus.shhz.screen.ControllerStage;

public class CreateBuildingCreator implements Listener<CreateGameStageEvent> {

	@Override
	public void onEvent(CreateGameStageEvent e) {
		ControllerStage s = e.getControllerStage();
		s.changeMenu(new RootMenu());
	}

}
