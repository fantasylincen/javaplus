package cn.javaplus.crazy.rate;

/**
 * 周期事件
 * 
 * @author 林岑
 * 
 */
public class RateEvent {

	private int sec;

	/**
	 * 每一小时的秒
	 */
	public static final int HOUR_SEC = 60 * 60;

	/**
	 * 每一分钟的秒
	 */
	public static final int MIN_SEC = 60;

	public RateEvent(int sec) {
		this.sec = sec;
	}

	/**
	 * 周期 (秒)
	 * 
	 * @return
	 */
	public int getSec() {
		return sec;
	}
}
