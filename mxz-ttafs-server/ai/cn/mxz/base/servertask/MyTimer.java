package cn.mxz.base.servertask;

import java.util.Date;
import java.util.Timer;

public class MyTimer {

	private Timer timer = new Timer();

	public void schedule(TaskSafetyLogToFile task, Date date) {
		timer.schedule(task, date);
	}

}
