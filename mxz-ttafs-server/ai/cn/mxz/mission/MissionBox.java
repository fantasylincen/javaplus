package cn.mxz.mission;

/**
 * 章节奖励宝箱
 *
 * @author 林岑
 *
 */
public interface MissionBox {
	/**
	 * 是否领取过
	 *
	 * @return
	 */
	boolean getHasReceived();

	/**
	 * 是否可以领取
	 *
	 * @return
	 */
	boolean getCanReceived();

	/**
	 * 领取这个宝箱
	 */
	void receive();

}
