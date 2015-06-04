package cn.mxz.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import cn.javaplus.util.Util;
import cn.mxz.events.DbCommitThreadEndEvent;
import cn.mxz.events.Events;
import cn.mxz.system.GameSystem;
import cn.mxz.util.debuger.Debuger;
import db.dao.impl.DaoFactory;

public class CommitDBThread extends Thread {
	
	private static AtomicInteger liveThreadCount = new AtomicInteger();
	
	@Override
	public void run() {
		liveThreadCount.addAndGet(1);
		while(GameSystem.getInstance().getServer().isRunning()) {
			DaoFactory.commitAllSafety();
			Util.Thread.sleep(2000);
		}
		Debuger.debug("CommitDBThread.run() 数据库批量提交线程:" + Thread.currentThread().getId() + " 正常结束!");
		liveThreadCount.addAndGet(-1);
		Events.getInstance().dispatch(new DbCommitThreadEndEvent(liveThreadCount.get()));
	}
}