package cn.javaplus.time.taskutil;

import java.util.Calendar;
import java.util.Timer;

import cn.javaplus.time.Time;



/**
 * 任务中心, 这里存放了各种各样的任务, 可以通过各种addTask方法向这个任务中心的实例中添加任务
 *
 * @author 张腾
 *
 */
public class TaskCenter implements ITaskCenter {

	/**
	 * @param name			线程名字
	 * @param isDaemon	是否为守护线程
	 */
	public TaskCenter(String name, boolean isDaemon){
		timer = new Timer(name, isDaemon);
	}

	public TaskCenter(){
		timer = new Timer();
	}

	private Timer timer;

	@Override
	public void loopFrom( String start, long period, TaskSafety task ) {
		timer.scheduleAtFixedRate(task, getCurrentTimeTo(start), period);
	}

	@Override
	public void loopEveryDay(String timeAtEveryDay, TaskSafety task) {
		timer.scheduleAtFixedRate(task, getCurrentTimeTo(timeAtEveryDay), Time.MILES_ONE_DAY);
	}

	@Override
	public void loopNextClock(long period, TaskSafety task) {
		timer.scheduleAtFixedRate(task, nextHour(), period);
	}

	/**
	 * 获得当前时间下个小时整点时间
	 * @return
	 */
	public static final long nextHour(){
		Calendar c = Calendar.getInstance();
		long min = (60 - c.get(Calendar.MINUTE - 1)) * Time.MILES_ONE_MIN;
		long sec = (60 - c.get(Calendar.SECOND)) * 1000;
		return min + sec;
	}

	
	
	public void cancel() {
		timer.cancel();
	}

	/**
	 *
	 * 返回当前时间到指定时间的剩余毫秒数
	 *
	 * 如当前时间是9:00 传入时间是9:01,   那么返回1*60*1000 = 60000
	 *
	 * @param string
	 * @return
	 */
	public static final long getCurrentTimeTo(String string) {

		// 拿到传入字符串的小时和分钟
		String[] split = string.split(":");
		long nextHour = Long.parseLong(split[0]) * Time.MILES_ONE_HOUR;
		long nextMin = Long.parseLong(split[1]) * Time.MILES_ONE_MIN;

		// 当前小时和分钟
		Calendar c = Calendar.getInstance();
		long nowHour = c.get(Calendar.HOUR_OF_DAY) * Time.MILES_ONE_HOUR;
		long nowMin = c.get(Calendar.MINUTE) * Time.MILES_ONE_MIN;
		long nowSec = c.get(Calendar.SECOND) * 1000;

		// 传入的时间减去当前时间
		long time = nextHour - nowHour + nextMin - nowMin - nowSec;

		if (time < 0) {
			time += Time.MILES_ONE_DAY;// 加一天时间
		}

		return time;
	}

	@Override
	public void loop(long period, long delay, TaskSafety task) {
		timer.scheduleAtFixedRate(task, delay, period);
	}


}
