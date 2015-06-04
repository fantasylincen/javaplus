package cn.mxz.activity.boss;

import cn.mxz.city.City;

/**
 * 好友分享的积分
 * 
 * @author 林岑
 * 
 */
interface SharePoint {

	/**
	 * 好友Boss总积分
	 * 
	 * @return
	 */
	int getAll();

	/**
	 * 好友击杀排名
	 * 
	 * @return
	 */
	int getRank();

	/**
	 * 分享给我的积分
	 * 
	 * @return
	 */
	int getShareToMe();

	/**
	 * 好友的城池
	 * 
	 * @return
	 */
	City getCity();
}
