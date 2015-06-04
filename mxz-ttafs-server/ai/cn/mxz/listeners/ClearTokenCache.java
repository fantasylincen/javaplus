package cn.mxz.listeners;

import cn.mxz.events.Listener;
import cn.mxz.events.ZeroClockEvent;
import cn.mxz.thirdpaty.TokenCacheManager;

public class ClearTokenCache implements Listener<ZeroClockEvent> {

	@Override
	public void onEvent(ZeroClockEvent e) {
		TokenCacheManager.clear();		
	}

}
