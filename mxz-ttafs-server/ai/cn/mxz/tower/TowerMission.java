package cn.mxz.tower;

import cn.mxz.user.mission.Mission;


/**
 * 爬塔副本
 * @author 林岑
 *
 */

public interface TowerMission extends Mission {

	/**
	 * 裂缝
	 *
	 * @return 如果没有裂缝, 那么返回null
	 *
	 */
	TowerBug getBug();

	/**
	 * 保存爬塔奖励
	 * @param towerFightingReward
	 */
	void save(TowerFightingReward towerFightingReward);

	/**
	 * 爬塔积分奖励
	 * @return
	 */
	TowerFightingReward getTowerFightingReward();

	/**
	 * 放入一个裂缝
	 * @param bug
	 */
	void setBug(TowerBug bug);


}
