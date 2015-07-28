package cn.javaplus.crazy.user;

import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.handler.ChannelDisconnectedEvent;
import cn.javaplus.log.Log;

public class ExitWorld implements Listener<ChannelDisconnectedEvent> {

	@Override
	public void onEvent(ChannelDisconnectedEvent e) {
		int clientId = e.getContext().getChannel().getId();
		World world = Game.getWorld();
		User user = world.onExit(clientId);
		if (user != null) {
			Log.d("ExitWorld.onEvent ", user);
			Events.dispatch(new UserExitEvent(user));
		}
	}

}
