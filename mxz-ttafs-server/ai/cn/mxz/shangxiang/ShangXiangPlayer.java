package cn.mxz.shangxiang;

public interface ShangXiangPlayer {

	/**
	 * 领取上香奖励
	 */
	void receive();

	/**
	 * 如果已经在上香时间段, 返回剩余多少秒结束
	 * 如果没在这个时间段, 返回剩余多少秒开始
	 * @return
	 */
	int getRemainSec();

	boolean isNight();

	boolean isNoon();

	/**
	 * 晚上上香是否完成
	 * @return
	 */
	boolean hasReceiveNight();

	/**
	 * 中午上香是否完成
	 * @return
	 */
	boolean hasReceiveNoon();
}
