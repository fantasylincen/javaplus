package cn.javaplus.shhz.listeners.stage;

import cn.javaplus.shhz.Game;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.OnSelectEvent;
import cn.javaplus.shhz.stage.MapObject;

public class OnSelectChangeMenu implements Listener<OnSelectEvent> {

	@Override
	public void onEvent(OnSelectEvent e) {
		MapObject selected = e.getSelected();
		if(selected != null) {
			Game.getControllerStage().changeMenu(selected.createMenu());
		}
	}

}
