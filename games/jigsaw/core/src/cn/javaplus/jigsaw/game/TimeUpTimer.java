package cn.javaplus.jigsaw.game;

/**
 * 超时计数器
 * @author 林岑
 *
 */
public class TimeUpTimer {

	private long	timeUpMilis;
	private long	lastRestartTime;

	/**
	 * @param timeUpMilis 超时时长  毫秒
	 */
	public TimeUpTimer(long timeUpMilis) {
		this.timeUpMilis = timeUpMilis;
	}

	/**
	 * 是否超时了
	 * @return
	 */
	public boolean isTimeUp() {
		return System.currentTimeMillis() - lastRestartTime >= timeUpMilis;
	}

	/**
	 * 从当前时间开始重新计时
	 */
	public void restart() {
		lastRestartTime = System.currentTimeMillis();
	}

}
