package cn.javaplus.chatrobot;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.javaplus.util.Util;
import cn.mxz.events.Events;

import com.google.common.collect.Maps;

public class RobotDispatcher implements Runnable {

	private Map<Long, ChatRobot> robots;

	public RobotDispatcher() {
		robots = Maps.newConcurrentMap();
		Events.getInstance().loadListeners("cn.javaplus.chatrobot.listeners");
		Thread thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	public ChatRobot getRobot(long qq) {
		ChatRobot r = robots.get(qq);
		if (r == null) {
			r = new ChatRobot();
			robots.put(qq, r);
		}
		return r;
	}

	public void run() {
		while(true) {
			removeRobotsWantToExit();
			Util.Thread.sleep(5000);
		}
	}

	private void removeRobotsWantToExit() {
		Set<Entry<Long, ChatRobot>> sets = robots.entrySet();
		Iterator<Entry<Long, ChatRobot>> it = sets.iterator();
		while (it.hasNext()) {
			Entry<Long, ChatRobot> next = it.next();
			if(next.getValue().wantToExit()) {
				it.remove();
			}
		}
	}

}
