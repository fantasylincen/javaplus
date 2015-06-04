package cn.mxz.user.builder;

public interface PlayerBase {

	/**
	 * 等级
	 * @return
	 */
	int getLevel();

	/**
	 * 昵称
	 * @return
	 */
	String getNick();

	/**
	 * 是否是男性
	 * @return
	 */
	boolean isMan();

	/**
	 * 玩家帐号
	 * @return
	 */
	String getId();

	/**
	 * VIP等级
	 * @return
	 */
	int getVipLevel();

	/**
	 * 段位ID
	 * @return
	 */
	int getDanId();

	/**
	 * 累计充值元宝数
	 * @return
	 */
	int getTotalRechargeGold();

	/**
	 * 主角战士类型ID
	 * @return
	 */
	int getMainFighterTypeId();

	/**
	 * 玩家的身价
	 * @return
	 */
	int getShenJia();

	/**
	 * 更新身价缓存
	 */
	void updateShenJia();

}
