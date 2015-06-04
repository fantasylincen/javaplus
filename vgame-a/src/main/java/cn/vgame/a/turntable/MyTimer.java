package cn.vgame.a.turntable;

import java.util.Timer;

import cn.javaplus.time.taskutil.TaskSafety;

public class MyTimer implements ITimer {

	private Timer timer;

	public MyTimer() {
		timer = new Timer(true);
	}

	public void scheduleAtFixedRate(TaskSafety task, long delay, long period) {
		timer.scheduleAtFixedRate(task, delay, period);
	}

	public void cancel() {
		timer.cancel();
	}

}
