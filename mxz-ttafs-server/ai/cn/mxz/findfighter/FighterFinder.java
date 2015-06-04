package cn.mxz.findfighter;

import java.util.List;

import cn.mxz.user.team.god.Hero;

/**
 * 寻仙器
 * @author 林岑
 *
 */

interface FighterFinder {

	List<Hero> find(int times);

	int getId();

	int getRemainSec();

	/**
	 * 该寻仙模式是否已经过期
	 * @return
	 */
	boolean isDeprecated();

	/**
	 * 当前拥有的道具
	 * @return
	 */
	int getFindTimesMax();

	/**
	 * 可选寻仙次数列表(长度最大为2)
	 * @return
	 */
	List<Integer> getTimesSelectedAble();

	void check(int times);

	void reduce(int times);

	/**
	 * 是否是单次寻仙方式
	 * @return
	 */
	boolean isSingle();

	/**
	 * 寻仙块 下方显示的数值
	 * @return
	 */
	int getCount();

	/**
	 * 显示给客户端的寻仙需要数量
	 * @return
	 */
	int getItemUnderCount();

	/**
	 * 今日免费次数
	 * @return
	 */
	int getFreeTimesToday();

}
