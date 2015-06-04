package cn.javaplus.time.taskutil;


/**
 *
 * 这是一个定时器中心, 你可以在这里方便的添加一项定时任务来执行,
 *
 *
 * @author
 *
 */
public interface ITaskCenter {

	/**
	 * 添加一项任务 , 在接下来的start时间开始, 间隔miles毫秒执行一次task任务
	 *
	 * 比如 start 为 "21:00", 当前时间为 20:30, 间隔时间为 20 x 60 x 1000 (20 分钟)
	 *
	 * 那么 这项任务就将在今天的  21:00 , 21:20, 21:40......执行.
	 *
	 * 又比如 start 为 "03:00", 当前时间为 20:30, 间隔时间为 20 x 60 x 1000 (20 分钟)
	 * 那么这项人物就将在明天的 03:00, 03:20, 03:40.....执行.
	 *
	 * @param task  任务
	 * @param start 开始时间  格式为 "00:00"
	 * @param period 间隔毫秒数
	 */
	void loopFrom(String start, long period, TaskSafety task);

	/**
	 * 添加一项规定的任务, 在每天的同一时间执行.
	 *
	 * 支持格式:
	 *     18:00
	 *
	 * @param task
	 * @param timeAtEveryDay
	 */
	void loopEveryDay(String timeAtEveryDay, TaskSafety task);

	/**
	 * 从下一个整点开始, 每过指定间隔时间, 就执行一次任务.
	 * @param task		任务
	 * @param period	指定间隔时间, 单位毫秒
	 */
	void loopNextClock(long period, TaskSafety task);

	/**
	 * 指定时间定期执行某任务
	 * @param period	任务执行周期, 毫秒
	 * @param delay		过delay毫秒开始启动任务
	 */
	void loop(long period, long delay, TaskSafety task);
}
