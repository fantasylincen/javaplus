package cn.mxz.listeners;

import cn.mxz.events.DbCommitThreadEndEvent;
import cn.mxz.events.DbCommitThreadsAllEndEvent;
import cn.mxz.events.Events;
import cn.mxz.events.Listener;

public class DispatchDbThreadsEndEvent implements Listener<DbCommitThreadEndEvent> {

	@Override
	public void onEvent(DbCommitThreadEndEvent event) {
		
		int count = event.getLiveThreadCount();
		if(count == 0) {
			Events.getInstance().dispatch(new DbCommitThreadsAllEndEvent());
		}
	}
}
