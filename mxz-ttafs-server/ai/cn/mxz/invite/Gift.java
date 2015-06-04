package cn.mxz.invite;

public interface Gift {

	/**
	 * 是否领取了该奖励
	 * @return
	 */
	boolean hasReceive();

	/**
	 * 礼物领取所需要邀请的好友人数
	 * @return
	 */
	int getCount();

	/**
	 * 领取这个奖励
	 */
	void receive();

}
