package cn.mxz.czfk2;

public interface FeedBack2Item {

	/**
	 * excel id
	 * 
	 * @return
	 */
	int getId();

	/**
	 * 是否可以领取
	 */
	boolean getCanReceive();

	/**
	 * 是否已经领取了所有
	 */
	boolean getHasReceiveAll();

	/**
	 * 已经领取的次数
	 */
	int getReceiveTimes();

	/**
	 * 总共可以领取的次数(如果可以无限领取, 该值为-1)
	 */
	int getTimes();

	/**
	 * 領取條件 （累積充值元寶數）
	 */
	int getMoney();

	/**
	 * 奖励内容
	 */
	String getAward();
	
	/**
	 * excel bagId
	 * @return
	 */
	int getBagId();
	
	/**
	 * 名字
	 * @return
	 */
	String getName();
	
	/**
	 * 累计所需
	 * @return
	 */
	int getRechargeNeed();
	
	/**
	 * 是否已经领取了
	 * @return
	 */
	boolean getHasReceive();
	
	/**
	 * 剩余可以领取多少次
	 * @return
	 */
	int getRemainTimes();
}
