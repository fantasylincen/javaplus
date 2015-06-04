package cn.javaplus.task;

import java.util.concurrent.ConcurrentLinkedQueue;

import cn.javaplus.util.Util;

/**
 * 该组件用于处理事务队列, push进该组件的事务, 会在一个新的线程里面执行
 * @author 林岑
 *
 */
public class TaskQueue implements Runnable {

	private boolean isRunning;
	private ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<Runnable>();

	public boolean isRunning() {
		return isRunning;
	}

	public void start() {
		isRunning = true;
		new Thread(this).start();
	}

	public void push(Runnable runnable) {
		this.tasks.add(runnable);
	}

	@Override
	public void run() {
		while (true) {
			Runnable poll = tasks.poll();
			if (poll == null) {
				Util.Thread.sleep(1000);
				continue;
			}
			try {
				poll.run();
			} catch (Exception e) {
				e.printStackTrace();
				Util.Thread.sleep(1000);
			}
		}
	}

}
