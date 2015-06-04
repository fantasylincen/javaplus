package cn.mxz.listeners;

import cn.mxz.events.DbCommitThreadsAllEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.util.debuger.Debuger;

public class StopServerListener implements Listener<DbCommitThreadsAllEndEvent> {

	@Override
	public void onEvent(DbCommitThreadsAllEndEvent e) {
		Debuger.debug("StopServerListener.onEvent() 正常停止服务器");
		System.exit(0);
	}

}
