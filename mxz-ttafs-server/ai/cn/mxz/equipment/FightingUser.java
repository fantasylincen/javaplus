package cn.mxz.equipment;

import cn.mxz.bag.BagAuto;
import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.builder.PlayerBase;

public interface FightingUser {

	String getId();

	PlayerCamp getCamp();

	boolean isRobot();

	/**
	 * 段位ID
	 * @return
	 */
	int getDanId();

	/**
	 * 玩家
	 * @return
	 */
	PlayerBase getPlayer();

	/**
	 * 背包
	 * @return
	 */
	BagAuto getBagAuto();

	/**
	 * 夺宝成功率
	 * @param city 
	 */
	float getProbability(City city, int stuffTempletId);

}
