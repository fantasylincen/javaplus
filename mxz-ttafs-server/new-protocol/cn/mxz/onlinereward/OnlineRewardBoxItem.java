package cn.mxz.onlinereward;

/**
 * 单个奖励
 * 
 * @author 林岑
 */
public interface OnlineRewardBoxItem extends Comparable<OnlineRewardBoxItem>{
	/**
	 * 是否可以领取
	 * 
	 * @return
	 */
	boolean getCanReceive();

	/**
	 * 是否已经领取了
	 * 
	 * @return
	 */
	boolean getHasReceive();

	/**
	 * @return 99:99:99 后可以领取 秒
	 */
	int getRemainSec();

	/**
	 * @return 奖励列表
	 */
	String getReward();
	
	/**
	 * id
	 * @return
	 */
	int getId();
	
	/**
	 * 是否显示倒计时
	 * @return
	 */
	boolean isShowCd();
}
