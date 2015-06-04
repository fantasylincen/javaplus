package cn.mxz.invite;

public interface InviteBoxUI {
	
	/**
	 * id
	 * @return
	 */
	int getId();
	
	/**
	 * 奖励内容
	 * @return
	 */
	String getContent();
	/**
	 * 说明
	 * @return
	 */
	String getDescription();
	
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
