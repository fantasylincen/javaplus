package cn.mxz.friend;

import cn.mxz.city.City;

/**
 * 好友
 * @author 林岑
 *
 */
public interface Friend {

	/**
	 * 好友的账号
	 * @return
	 */
	String getFriendId();

	/**
	 * 好友的城池
	 * @return
	 */
	City getFriendCity();

	/**
	 * 我的ID
	 * @return
	 */
	String getMyId();
}
