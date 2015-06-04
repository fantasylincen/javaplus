package cn.mxz.listeners;

import cn.mxz.activity.heishi.HeishiActivity;
import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;

public class InitHeishiActivity implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		HeishiActivity.INSTANCE.init();		
	}

}
