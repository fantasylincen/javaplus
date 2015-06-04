package cn.javaplus.time.coolingtimer;

/**
 * 冷却时间
 * @author 张腾
 *
 */
public interface ICoolingTimer {
	
	/**
	 * 冷却时间是否结束
	 * @return
	 */
	public boolean isCDOver();
	
	/**
	 * 重置冷却时间
	 * @return 
	 */
	public ICoolingTimer update();
	
	/**
	 * 设置冷却时间
	 * @param cd
	 */
	public void setCd(long cd);
	
	/**
	 * 获得剩余CD时间.(毫秒)
	 * @return
	 */
	public long getRemainingTime();

	/**
	 * 获得剩余CD时间.(秒)
	 * @return
	 */
	public int getRemainingSec();
	
	/**
	 * 清除CD时间
	 */
	void clearCD();

	/**
	 * 获得CD时间
	 * @return
	 */
	public long getCD();

	/**
	 * 设置CD结束时间
	 * @param l
	 */
	public void setCDEndTime(long l);

	/**
	 * 获得CD结束时间
	 * @return
	 */
	public long getCDEndTime();

	/**
	 * 剩余分， 不足1分的按1分计算
	 * @return
	 */
	public int getRemainingMin();
}
