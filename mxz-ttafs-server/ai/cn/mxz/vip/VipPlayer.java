package cn.mxz.vip;

import cn.javaplus.math.Fraction;

public interface VipPlayer {

	/**
	 * 购买指定等级的物品
	 * @param vipLevel
	 */
	void buy(int vipLevel);

	/**
	 * VIP 礼包购买情况
	 * @return
	 */
	VipGiftStatus getGiftStatus();

	/**
	 * VIP 等级
	 * @return
	 */
	int getLevel();

	Fraction getGrowth();

	/**
	 * 设置vip等级
	 * @param level
	 */
	void setLevel(int level);
}
