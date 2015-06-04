package cn.mxz.vipcard;

public interface VipCardPlayer {

	/**
	 * VIP月卡类型
	 * @return 0:什么都没有买 1:白银月卡 2:黄金月卡 3:至尊月卡
	 */
	int getVipCardType();

	int getRemainDay();

	void buy();

	int getLevel();

	/**
	 * 是否领取了当前月卡奖励
	 * @return
	 */
	boolean hasReceived();

}
