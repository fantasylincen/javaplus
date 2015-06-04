package cn.mxz.hhdlb;

/**
 * 豪华大礼包UI
 * 
 * @author 林岑
 * 
 */
public interface HhdlbUI {

	/**
	 * 累计充值
	 */
	int getAll();

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
}
