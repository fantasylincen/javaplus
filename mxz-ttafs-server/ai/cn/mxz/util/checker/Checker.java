package cn.mxz.util.checker;

import cn.mxz.city.PlayerProperty;


/**
 * 玩家检查器
 * @author 林岑
 */
public interface Checker {

	/**
	 * 检查道具是否足够
	 * @param need
	 */
	void checkProp(int propId, int need);

	/**
	 * 检查玩家携带神将数量是否到达上限
	 */

	void checkFighterMax();

	/**
	 * 检查指定神将是否存在与队伍中或者仓库中

	 * @param fighterId
	 */
	void checkFighterExist(int... fighterId);

	/**
	 * 检查玩家是否存在于世界中
	 * @param userId
	 */
	void checkPlayerExist(String userId);

	/**
	 * 检查是否为空, 如果为空, 抛出空指针异常
	 * @param o
	 */
	void checkNull(Object o);

	/**
	 * 检查用户某个属性是否足够, 如果是 GOLD 或者 GIFT GOLD 那么, 系统将会把两种GOLD给加起来, 同时检查够不
	 * @param playerProperty 来自#PlayerProperty
	 * @param reduce
	 */
	void checkPlayerProperty(PlayerProperty playerProperty, int reduce);

	/**
	 * 根据识别ID检查道具够不够
	 * @param spotId
	 * @param count
	 */
	void checkPropBySpotId(int spotId, int count);

	/**
	 * 检查元宝够不够
	 * @param count
	 */
	void checkGold(int count);

	/**
	 * 检查金锭+元宝 总数 够不够
	 * @param price
	 */
	void checkGoldOrJinDing(int price);
}
