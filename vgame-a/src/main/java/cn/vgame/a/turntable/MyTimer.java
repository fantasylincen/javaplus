package cn.vgame.a.turntable;

import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.time.taskutil.TaskSafety;
import cn.javaplus.util.Util;

public class MyTimer {

	public class MyTask implements Runnable{

		private long lastProcessBaseTaskTime;
		private TaskSafety task;
		private long space;

		public long getSpace() {
			return space;
		}

		public void run() {
			markProcessed();
			task.run();
		}

		/**
		 * 标记已经执行过了
		 */
		private void markProcessed() {
			lastProcessBaseTaskTime = processBaseTaskTime;
		}

		/**
		 * 是否已经执行过了
		 * @return
		 */
		public boolean hasProcessd() {
			return lastProcessBaseTaskTime == processBaseTaskTime;
		}

		public void setTask(TaskSafety task) {
			this.task = task;
		}

		public void setSpace(long space) {
			this.space = space;
		}

	}
	/**
	 * 主任务最后执行的时间, 如果从来没有执行过, 该值为 0
	 */
	private long lastBaseTaskProcessTime;

	public class MyThread extends Thread {

		private boolean isStop;
		

		@Override
		public void run() {
			while (!isStop) {
				runSafty();
				Util.Thread.sleep(100);
			}
		}

		private void runSafty() {
			if (baseTask == null)
				return;

			runBaseTask();
			runAppendsTasks();
		}

		private void runAppendsTasks() {
			for (MyTask t : tasks) {
				runAppendTask(t);
			}
		}

		private void runAppendTask(MyTask t) {
			long processTime = getProcessTime(t);
			long now = System.currentTimeMillis();
			if (!t.hasProcessd() && now >= processTime) {
				t.run();
			}
		}

		private long getProcessTime(MyTask t) {
			long space = t.getSpace();
			
			if(space > 0) {
				return getLastBaseTaskProcessTime() + space;
			} else {
				return processBaseTaskTime + space;
			}
		}

		private void runBaseTask() {
			long now = System.currentTimeMillis();
			if (now >= processBaseTaskTime) {
				baseTask.run();
				setLastBaseTaskProcessTime(now);
				processBaseTaskTime = now + period;
			}
		}
	}

	private TaskSafety baseTask;
	private MyThread thread;
	private List<MyTask> tasks = Lists.newArrayList();
	private long period;
	private long processBaseTaskTime;
	
	

	public MyTimer() {
		thread = new MyThread();
	}

	/**
	 * 暂停, 毫秒
	 * 
	 * @param pauseMillis
	 */
	public void pause(long pauseMillis) {
		processBaseTaskTime += pauseMillis;
	}

	/**
	 * 终止该计时器
	 */
	public void stop() {
		thread.isStop = true;
	}

	/**
	 * 设置基础任务
	 * 
	 * @param task
	 * @param delay
	 *            延时多少毫秒执行
	 * @param period
	 *            执行周期
	 */
	public void setBaseTask(TaskSafety task, long delay, long period) {
		this.baseTask = task;
		this.period = period;
		processBaseTaskTime = delay + System.currentTimeMillis();
	}

	/**
	 * 假设基础任务执行时间为T
	 * 
	 * 在基础任务上追加一个任务, 这个任务将在 T + space 执行
	 * 
	 * @param task
	 * @param space
	 *            主任务和该任务执行时间的时间差(毫秒)
	 */
	public void appendTask(TaskSafety task, long space) {
		MyTask t = new MyTask();
		t.setTask(task);
		t.setSpace(space);
		tasks.add(t);
	}

	/**
	 * 获取主任务 还有多少毫秒开始执行
	 * 
	 * @return
	 */
	public long getBaseTaskCd() {
		long l = processBaseTaskTime - System.currentTimeMillis();
		if(l < 0)
			l = 0;
		return l;
	}

	/**
	 * 开始执行
	 */
	public void start() {
		thread.start();
	}

	public long getLastBaseTaskProcessTime() {
		return lastBaseTaskProcessTime;
	}

	public void setLastBaseTaskProcessTime(long lastBaseTaskProcessTime) {
		this.lastBaseTaskProcessTime = lastBaseTaskProcessTime;
	}

	// private Timer timer;
	// private List<TurntableTask> tasks = Lists.newArrayList();
	//
	// public MyTimer() {
	// timer = new Timer(true);
	// }
	//
	// public void cancel() {
	// timer.cancel();
	// tasks.clear();
	// }
	//
	// boolean isPause;
	//
	//
	// public boolean isPause() {
	// return isPause;
	// }
	//
	// @Override
	// public void pause(long pauseMillis) {
	// if (isPause)
	// throw new RuntimeException("is pause now! can't pause agaim!");
	//
	// isPause = true;
	//
	// Log.d("timer pause");
	//
	// final List<TurntableTask> all = Lists.newArrayList(tasks);
	// cancel();
	//
	// Timer t = new Timer();
	// t.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// timer = new Timer();
	// for (TurntableTask t : all) {
	// scheduleAtFixedRate(t.copy());
	// }
	// Log.d("reset tasks");
	// isPause = false;
	// }
	// }, pauseMillis);
	//
	// }
	//
	// @Override
	// public void scheduleAtFixedRate(TurntableTask task) {
	// timer.scheduleAtFixedRate(task, task.getDelay(), task.getPeriod());
	// tasks.add(task);
	// }

}
