package cn.mxz.base.prize;

import cn.mxz.city.City;
import cn.mxz.user.Player;

/**
 * 可奖励的
 * @author 林岑
 *
 */
public interface AwardAble {

	/**
	 * 发放这个奖励
	 */
	void award(Player player);
	void award(City city);
}