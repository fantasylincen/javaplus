package cn.mxz.mission;

/**
 * 章节奖励 宝箱UI
 * @author 林岑
 *
 */
public interface BoxUI {

	/**
	 * 是否领取过
	 * @return
	 */
	boolean getHasReceived();

	/**
	 * 是否可以领取
	 * @return
	 */
	boolean getCanReceived();
}
