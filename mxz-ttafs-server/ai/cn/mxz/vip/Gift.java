package cn.mxz.vip;

public interface Gift {

	/**
	 * vip等级
	 * @return
	 */
	int getLevel();

	/**
	 * 是否已经购买了
	 * @return
	 */
	boolean getHasBought();

	/**
	 * 道具ID
	 * @return
	 */
	int getPropId();

}
