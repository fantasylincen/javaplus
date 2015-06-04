package cn.mxz.czfk;

public interface FeedBackItem {
	
	/**
	 * excel id
	 * @return
	 */
	int getId();
	
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
	 * 累计充值
	 * @return
	 */
	int getRechargeNeed();
	
	/**
	 * 是否可以领取
	 * @return
	 */
	boolean getCanReceive();
	
	/**
	 * 是否已经领取了
	 * @return
	 */
	boolean getHasReceive();
}
