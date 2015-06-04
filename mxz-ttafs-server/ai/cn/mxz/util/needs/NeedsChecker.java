package cn.mxz.util.needs;

import cn.mxz.city.City;
import cn.mxz.user.Player;

/**
 * 各种需求检查器(金币,点券,道具.....)
 * @author 林岑
 *
 */
public interface NeedsChecker extends Deductor {

	/**
	 * 检查够不够
	 */
	void check(Player player);

	/**
	 * 打折
	 * @param discount ( 0.0f - 1.0f )
	 */

	void discount(float discount);

	/**
	 * 当前可扣除次数
	 * @param city
	 * @return
	 */

	int getDeductTimesMax(City city);

	/**
	 * 检查够不够
	 * @param city
	 */
	void check(City city);

}
