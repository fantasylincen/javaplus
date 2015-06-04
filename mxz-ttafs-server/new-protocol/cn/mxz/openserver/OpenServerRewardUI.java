package cn.mxz.openserver;

/**
 * 开服礼包奖励
 * @author 林岑
 *
 */
public interface OpenServerRewardUI {

	/**
	 * 是否领取过了
	 * @return
	 */
	boolean getHasReceive();

	/**
	 * 是否可以领取
	 * @return
	 */
	boolean getCanReceive();

	/**
	 * 这个奖励是第几天的奖励
	 * @return
	 */
	int getDay();
}
