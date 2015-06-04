package cn.mxz.listeners;

import cn.mxz.base.config.KeyValueDefine;
import cn.mxz.base.config.KeyValueManager;
import cn.mxz.base.config.KeyValueManagerImpl;
import cn.mxz.events.Listener;
import cn.mxz.events.ZeroClockEvent;

// 清空在线人数
public class ClearOnlineTodayListener implements Listener<ZeroClockEvent> {

	@Override
	public void onEvent(ZeroClockEvent e) {
		KeyValueManager k = new KeyValueManagerImpl();
		k.put(KeyValueDefine.ONLINE_SIZE_MAX_TODAY, 0);
	}

}
