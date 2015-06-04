package cn.mxz.activity.boss;

import cn.mxz.city.City;

import com.lemon.commons.user.IUser;

/**
 * Boss挑战者
 * 
 * @author 林岑
 * 
 */
interface BossChallenger extends IUser {

	/**
	 * 对Boss造成的伤害值
	 * 
	 * @param boss
	 * @return
	 */
	int getDamage(Boss boss);

	/**
	 * 是不是击杀者
	 * 
	 * @param boss
	 *            是否是这个Boss的击杀者
	 * @return
	 */
	boolean isKiller(Boss boss);

	/**
	 * 击杀排名
	 * 
	 * @param boss
	 *            获得伤害值排名
	 * @return
	 */
	int getRank(Boss boss);

	/**
	 * 击杀者的城池
	 * 
	 * @return
	 */
	City getCity();

}
