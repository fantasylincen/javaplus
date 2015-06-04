package cn.mxz.listeners;

import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;

//数据库批量提交线程启动
public class StartCommitor implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		new CommitDBThread().start();
		new CommitDBThread().start();
		new CommitDBThread().start();
		new CommitDBThread().start();
		new CommitDBThread().start();
		new CommitDBThread().start();
		new CommitDBThread().start();
		new CommitDBThread().start();
	}

}
