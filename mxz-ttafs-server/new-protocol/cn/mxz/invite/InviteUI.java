package cn.mxz.invite;

import java.util.List;

public interface InviteUI {

	/**
	 * 我邀请了多少个人
	 * 
	 * @return
	 */
	int getNumber();

	/**
	 * 我的邀请码
	 * 
	 * @return
	 */
	String getMyCode();

	/**
	 * 是否已经被邀请过(是否已经输入了一次激活码了)
	 * 
	 * @return
	 */
	boolean getHasBeenInvited();

	/**
	 * 是否领取了 奖励
	 */
	boolean getHasReceive();

	/**
	 * 礼包列表
	 * 
	 * @return
	 */
	List<InviteBoxUI> getBoxes();
}
