package cn.javaplus.cache;

/**
 * 缓存时间计时器
 * @author 林岑
 *
 */
public class CacheTimer {

	private int		miles;
	private long	time;

	/**
	 * 缓存时间计时器
	 *
	 * @param miles	超时时间
	 */
	public CacheTimer(int miles) {
		this.miles = miles;
		update();
	}

	/**
	 * 是否过期了
	 *
	 * @return
	 */
	public boolean isTimeUp() {
		return System.currentTimeMillis() - time >= miles;
	}

	/**
	 * 更新最后一次缓存更新时间
	 */
	public void update() {
		this.time = System.currentTimeMillis();
	}

}
