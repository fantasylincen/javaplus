package cn.mxz.util.needs;

import cn.mxz.city.City;
import cn.mxz.user.Player;

public interface INeeds {

	void checkEnouph(City user);
	void checkEnouph(Player user);

	void deduct(City user);
	void deduct(Player user);

	/**
	 * 打折
	 * @param discount 0.0f - 1.0f
	 */
	void discount(float discount);

	/**
	 * 当前可deduct次数
	 * @param city
	 * @return
	 */
	int getDeductTimesMax(City city);

	/**
	 * 当前拥有数量
	 * @param player
	 * @return
	 */
	int getHaveNow(City user);

	/**
	 * 需要扣除的数量
	 * @param player
	 * @return
	 */
	int getNeed(City user);

	int getId();

	int getCount();
	int getHaveNow(Player player);
	int getNeed(Player player);
}
