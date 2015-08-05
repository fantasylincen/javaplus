package cn.javaplus.shhz.listeners.building;

import cn.javaplus.game.shhz.message.S;
import cn.javaplus.shhz.Game;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.building.NotEmptySpaceForBuildingEvent;
import cn.javaplus.shhz.util.MessageBox;

public class ShowMessageNoSpace implements Listener<NotEmptySpaceForBuildingEvent> {

	@Override
	public void onEvent(NotEmptySpaceForBuildingEvent e) {
		MessageBox box = Game.getMessageBox();
		box.showMessage(S.S1);
	}

}
