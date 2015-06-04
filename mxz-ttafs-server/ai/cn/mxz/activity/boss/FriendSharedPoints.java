package cn.mxz.activity.boss;

import java.util.List;

/**
 * Boss 好友分享积分信息
 * 
 * @author 林岑
 * 
 */
interface FriendSharedPoints {

	/**
	 * 所有好友分享的积分信息
	 * 
	 * @return
	 */
	List<SharePoint> getAll();

	/**
	 * 积分总和
	 * 
	 * @return
	 */
	int getAllPoint();

	/**
	 * 移除这个分享的积分信息
	 * 
	 * @param sharePoint
	 */
	
	void remove(SharePoint sharePoint);

}
