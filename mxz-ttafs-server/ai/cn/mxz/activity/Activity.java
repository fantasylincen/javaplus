package cn.mxz.activity;

/**
 * 活动
 * @author 林岑
 *
 */
public interface Activity {

	/**
	 * 活动是否开启
	 * @return
	 */
	boolean isStart();

	/**
	 * 开启活动
	 */
	void start();

	/**
	 * 停止活动
	 */
	void stop();
}
