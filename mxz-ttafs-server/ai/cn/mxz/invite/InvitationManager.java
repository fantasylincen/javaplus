package cn.mxz.invite;

/**
 * 邀请有礼
 * @author 林岑
 *
 */
public interface InvitationManager {

	/**
	 * 新增一个被我邀请的玩家
	 * @param id
	 */
	void add(String id);

	/**
	 * 获得指定的邀请有礼奖励
	 * @param number
	 * @return
	 */
	Gift getGift(int number);

	/**
	 * 邀请 的人数
	 * @return
	 */
	int getCount();

}
