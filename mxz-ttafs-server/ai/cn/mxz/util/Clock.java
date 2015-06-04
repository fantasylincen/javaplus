package cn.mxz.util;

/**
 * 时钟
 * @author 林岑
 *
 */
public interface Clock {

	/**
	 * 开始计时
	 */
	void start();

	/**
	 * 停止计时
	 */
	void stop();

	/**
	 * 获得时钟运行总时长(纳秒)
	 * @return
	 */
	long getTime();

	/**
	 * 清空计时器
	 */

	void clear();

	/**
	 * 计时器是否正在运行
	 * @return
	 */
	boolean isStart();

	/**
	 * 持续时间, 毫秒
	 * @return
	 */
	long getMillis();

}
