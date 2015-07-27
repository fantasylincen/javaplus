package cn.javaplus.shhz.listeners.stage;

import cn.javaplus.shhz.Game;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.UnSelectEvent;
import cn.javaplus.shhz.stage.GameStage;
import cn.javaplus.shhz.stage.MapObject;

public class UnSelectChangeRootMenu implements Listener<UnSelectEvent> {

	@Override
	public void onEvent(UnSelectEvent e) {
//		GameStage s = Game.getGameStage();
//		MapObject selected = s.getSelected();
//		if(selected == null) {
			Game.getControllerStage().changeMenu(new RootMenu());
//		}
	}

}
